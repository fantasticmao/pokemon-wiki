package cn.fantasticmao.pokemon.web.domain.item.repoistory;

import cn.fantasticmao.pokemon.web.domain.item.model.Item;

import javax.annotation.Nullable;
import java.util.List;

/**
 * ItemRepository
 *
 * @author fantasticmao
 * @since 2025-11-06
 */
public interface ItemRepository {

    List<Item> listByName(@Nullable String nameZh, @Nullable String nameEn);

    List<Item> list(int page, int size);

}
