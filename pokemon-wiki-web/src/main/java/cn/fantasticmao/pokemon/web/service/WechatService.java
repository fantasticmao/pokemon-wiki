package cn.fantasticmao.pokemon.web.service;

import cn.fantasticmao.mundo.web.support.wechat.WeChatServerConfig;

import javax.annotation.Nullable;

/**
 * WechatService
 *
 * @author fantasticmao
 * @since 2021/6/22
 */
public interface WechatService extends WeChatServerConfig.TokenProvider {

    String searchPokemonInfosByName(@Nullable String nameZh);

}
