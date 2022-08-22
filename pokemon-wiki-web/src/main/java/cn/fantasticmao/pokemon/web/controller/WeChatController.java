package cn.fantasticmao.pokemon.web.controller;

import cn.fantasticmao.mundo.web.support.wechat.*;
import cn.fantasticmao.pokemon.web.service.WechatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * WeChatController
 *
 * @author fantasticmao
 * @since 2018/12/5
 */
@Controller
public class WeChatController extends WeChatServerConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatController.class);
    @Resource
    private WechatService wechatService;

    @GetMapping("/wechat")
    public ResponseEntity<String> signature(HttpServletRequest request) {
        return ResponseEntity.ok(super.config(request));
    }

    @PostMapping(value = "/wechat", produces = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<String> message(HttpServletRequest request, @RequestBody String xml) {
        LOGGER.debug("接收微信消息 xml={}", xml);
        WeChatMessage weChatMessage = WeChatMessageFactory.newMessage(xml);
        if (weChatMessage.getMsgType().equals(WeChatMessageType.TEXT)) {
            WeChatTextMessage weChatTextMessage = WeChatMessageFactory.newTextMessage(xml);
            final String content = weChatTextMessage.getContent();
            LOGGER.debug("接收用户发送的普通消息 content={}", content);

            final String replyContent = wechatService.searchPokemonInfosByName(content);
            final String replyContentXml = WeChatMessageFactory.newXmlByTextMessage(
                weChatTextMessage.getFromUserName(), weChatTextMessage.getToUserName(), replyContent);
            LOGGER.debug("被动回复微信消息 xml={}", replyContentXml);
            return ResponseEntity.ok(replyContentXml);
        } else {
            return ResponseEntity.ok("success");
        }
    }
}
