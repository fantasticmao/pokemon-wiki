package cn.fantasticmao.pokemon.web.web;

import cn.fantasticmao.mundo.web.support.wechat.*;
import cn.fantasticmao.pokemon.web.service.WechatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * WeChatController
 *
 * @author fantasticmao
 * @since 2018/12/5
 */
@Slf4j
@RestController
public class WeChatController extends WeChatServerConfig {
    @Resource
    private WechatService wechatService;

    @GetMapping("/wechat")
    @Override
    public String config(HttpServletRequest request) {
        return super.config(request);
    }

    @PostMapping(value = "/wechat", produces = MediaType.TEXT_XML_VALUE)
    public String message(HttpServletRequest request, @RequestBody String xml) {
        log.debug("接收微信消息 xml={}", xml);
        WeChatMessage weChatMessage = WeChatMessageFactory.newMessage(xml);
        if (weChatMessage.getMsgType().equals(WeChatMessageType.TEXT)) {
            WeChatTextMessage weChatTextMessage = WeChatMessageFactory.newTextMessage(xml);
            final String content = weChatTextMessage.getContent();
            log.debug("接收用户发送的普通消息 content={}", content);

            final String replyContent = wechatService.searchPokemonInfosByName(content);
            final String replyContentXml = WeChatMessageFactory.newXmlByTextMessage(
                weChatTextMessage.getFromUserName(), weChatTextMessage.getToUserName(), replyContent);
            log.debug("被动回复微信消息 xml={}", replyContentXml);
            return replyContentXml;
        } else {
            return "success";
        }
    }
}
