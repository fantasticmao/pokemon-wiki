package cn.fantasticmao.pokemon.web.service;

import cn.fantasticmao.pokemon.web.bean.MoveBean;

import java.util.List;

/**
 * MoveService
 *
 * @author fantasticmao
 * @since 2018/8/6
 */
public interface MoveService {

    List<MoveBean> listByNameZh(String nameZh);

    List<MoveBean> list(int page, int size);
}
