package cn.fantasticmao.pokemon.wiki.web;

import cn.fantasticmao.mundo.web.mvc.WeChatConfigController;
import cn.fantasticmao.mundo.web.support.wechat.WeChatMessage;
import cn.fantasticmao.mundo.web.support.wechat.WeChatMessageFactory;
import cn.fantasticmao.mundo.web.support.wechat.WeChatMessageType;
import cn.fantasticmao.mundo.web.support.wechat.WeChatTextMessage;
import cn.fantasticmao.pokemon.wiki.bean.PokemonBean;
import cn.fantasticmao.pokemon.wiki.service.PokemonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * WeChatController
 *
 * @author maodh
 * @since 2018/12/5
 */
@Slf4j
@ApiIgnore
@RestController
public class WeChatController extends WeChatConfigController {
    @Resource
    private PokemonService pokemonService;

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

            List<PokemonBean> pokemonBeanList = pokemonService.listByNameZh(content);
            final String replyContent;
            if (CollectionUtils.isNotEmpty(pokemonBeanList)) {
                replyContent = pokemonBeanList.stream()
                    .map(pokemon -> {
                        String typeJoin = pokemon.getType1();
                        if (StringUtils.isNotBlank(pokemon.getType2())) {
                            typeJoin += "、" + pokemon.getType2();
                        }
                        String abilityJoin = pokemon.getAbility1();
                        if (StringUtils.isNotBlank(pokemon.getAbility2())) {
                            abilityJoin += "、" + pokemon.getAbility2();
                        }
                        return String.format("#%d %s，第 %d 世代宝可梦，英文名称：%s，日文名称：%s，属性：%s，特性：%s，隐藏特性：%s。",
                            pokemon.getIndex(), pokemon.getNameZh(), pokemon.getGeneration(), pokemon.getNameEn(), pokemon.getNameJa(),
                            typeJoin, abilityJoin, pokemon.getAbilityHide());
                    })
                    .collect(Collectors.joining(System.lineSeparator()));
            } else {
                replyContent = "未找到相关宝可梦";
            }
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
