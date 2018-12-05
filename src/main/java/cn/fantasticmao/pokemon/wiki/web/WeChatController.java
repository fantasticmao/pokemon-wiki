package cn.fantasticmao.pokemon.wiki.web;

import cn.fantasticmao.pokemon.wiki.bean.PokemonBean;
import cn.fantasticmao.pokemon.wiki.service.PokemonService;
import com.mundo.core.util.CollectionUtil;
import com.mundo.core.util.JsonUtil;
import com.mundo.web.mvc.WeChatConfigController;
import com.mundo.web.support.wechat.WeChatMessage;
import com.mundo.web.support.wechat.WeChatMessageFactory;
import com.mundo.web.support.wechat.WeChatMessageType;
import com.mundo.web.support.wechat.WeChatTextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    private PokemonService pokemonService;

    @GetMapping("/wechat")
    @Override
    public String configServer(HttpServletRequest request) {
        return super.configServer(request);
    }

    @PostMapping("/wechat")
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

            List<PokemonBean> pokemonBeanList = pokemonService.listByNameZh(content);
            final String replyContent = CollectionUtil.isNotEmpty(pokemonBeanList)
                    ? JsonUtil.toJson(pokemonBeanList) : "未找到相关宝可梦";
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
