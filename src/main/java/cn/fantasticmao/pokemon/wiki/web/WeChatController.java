package cn.fantasticmao.pokemon.wiki.web;

import cn.fantasticmao.mundo.web.mvc.WeChatConfigController;
import cn.fantasticmao.mundo.web.support.wechat.WeChatMessage;
import cn.fantasticmao.mundo.web.support.wechat.WeChatMessageFactory;
import cn.fantasticmao.mundo.web.support.wechat.WeChatMessageType;
import cn.fantasticmao.mundo.web.support.wechat.WeChatTextMessage;
import cn.fantasticmao.pokemon.wiki.service.WechatService;
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
 * @author maodh
 * @since 2018/12/5
 */
@Slf4j
@RestController
public class WeChatController extends WeChatConfigController {
    @Resource
    private WechatService wechatService;

    @GetMapping("/wechat")
    @Override
    public String configServer(HttpServletRequest request) {
        return super.configServer(request);
    }

    @PostMapping(value = "/wechat", produces = MediaType.TEXT_XML_VALUE)
    public String message(HttpServletRequest request, @RequestBody String xml) {
        if (log.isDebugEnabled()) {
            log.debug("接收微信消息 xml={}", xml);
        }
        WeChatMessage weChatMessage = WeChatMessageFactory.newMessage(xml);
        if (weChatMessage.getMsgType().equals(WeChatMessageType.TEXT)) {
            WeChatTextMessage weChatTextMessage = WeChatMessageFactory.newTextMessage(xml);
            final String content = weChatTextMessage.getContent();
            if (log.isDebugEnabled()) {
                log.debug("接收用户发送的普通消息 content={}", content);
            }

            final String replyContent = wechatService.searchPokemonInfosByName(content);
            final String replyContentXml = WeChatMessageFactory.newXmlByTextMessage(
                weChatTextMessage.getFromUserName(), weChatTextMessage.getToUserName(), replyContent);
            if (log.isDebugEnabled()) {
                log.debug("被动回复微信消息 xml={}", replyContentXml);
            }
            return replyContentXml;
        } else {
            return "success";
        }
    }
}
