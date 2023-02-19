package cn.fantasticmao.pokemon.web.service;

import cn.fantasticmao.pokemon.web.bean.ItemBean;

import javax.annotation.Nullable;
import java.util.List;

/**
 * ItemService
 *
 * @author fantasticmao
 * @since 2019-03-23
 */
public interface ItemService {

    List<ItemBean> listByName(@Nullable String nameZh, @Nullable String nameEn);

    List<ItemBean> list(@Nullable Integer page, int size);
}
