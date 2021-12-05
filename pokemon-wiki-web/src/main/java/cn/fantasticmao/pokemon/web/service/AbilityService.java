package cn.fantasticmao.pokemon.web.service;

import cn.fantasticmao.pokemon.web.bean.AbilityBean;

import java.util.List;

/**
 * AbilityService
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
public interface AbilityService {

    List<AbilityBean> listByNameZh(String nameZh);
}
