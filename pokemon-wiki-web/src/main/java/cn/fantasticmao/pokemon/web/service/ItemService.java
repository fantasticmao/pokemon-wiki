package cn.fantasticmao.pokemon.web.service;

import cn.fantasticmao.pokemon.web.bean.ItemBean;

import java.util.List;

/**
 * ItemService
 *
 * @author fantasticmao
 * @since 2019-03-23
 */
public interface ItemService {

    List<ItemBean> listByNameZh(String nameZh);

    List<ItemBean> list(int page, int size);
}
