package cn.fantasticmao.pokemon.web.service;

import cn.fantasticmao.mundo.web.support.wechat.WeChatServerConfig;

/**
 * WechatService
 *
 * @author maomao
 * @since 2021/6/22
 */
public interface WechatService extends WeChatServerConfig.TokenProvider {

    String searchPokemonInfosByName(String nameZh);
}
