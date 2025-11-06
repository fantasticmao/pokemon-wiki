package cn.fantasticmao.pokemon.web.domain.common.service;

import cn.fantasticmao.mundo.web.support.wechat.WeChatServerConfig;

import javax.annotation.Nullable;

/**
 * WechatDomainService
 *
 * @author fantasticmao
 * @since 2021/6/22
 */
public interface WechatDomainService extends WeChatServerConfig.TokenProvider {

    String searchPokemonInfosByName(@Nullable String nameZh);

}
