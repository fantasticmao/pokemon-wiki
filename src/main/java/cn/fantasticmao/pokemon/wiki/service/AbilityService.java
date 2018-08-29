package cn.fantasticmao.pokemon.wiki.service;

import cn.fantasticmao.pokemon.wiki.bean.AbilityBean;

import java.util.List;

/**
 * AbilityService
 *
 * @author maodh
 * @since 2018/8/29
 */
public interface AbilityService {

    List<AbilityBean> listByNameZh(String nameZh);
}
