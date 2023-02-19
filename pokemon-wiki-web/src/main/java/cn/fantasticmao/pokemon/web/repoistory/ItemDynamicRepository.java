package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.Item;

import javax.annotation.Nullable;
import java.util.List;

/**
 * ItemDynamicRepository
 *
 * @author fantasticmao
 * @since 2023-02-20
 */
public interface ItemDynamicRepository {

    List<Item> findByName(@Nullable String nameZh, @Nullable String nameEn);
}
