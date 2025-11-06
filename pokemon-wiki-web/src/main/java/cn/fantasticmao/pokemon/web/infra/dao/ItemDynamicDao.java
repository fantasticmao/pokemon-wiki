package cn.fantasticmao.pokemon.web.infra.dao;

import cn.fantasticmao.pokemon.web.infra.model.ItemPo;

import javax.annotation.Nullable;
import java.util.List;

/**
 * ItemDynamicDao
 *
 * @author fantasticmao
 * @since 2023-02-20
 */
public interface ItemDynamicDao {

    List<ItemPo> findByName(@Nullable String nameZh, @Nullable String nameEn);
}
