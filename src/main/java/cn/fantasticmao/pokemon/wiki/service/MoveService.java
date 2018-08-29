package cn.fantasticmao.pokemon.wiki.service;

import cn.fantasticmao.pokemon.wiki.bean.MoveBean;

import java.util.List;

/**
 * MoveService
 *
 * @author maodh
 * @since 2018/8/6
 */
public interface MoveService {

    List<MoveBean> listByNameZh(String nameZh);
}
