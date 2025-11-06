package cn.fantasticmao.pokemon.web.domain.item.service;

import cn.fantasticmao.pokemon.web.domain.item.model.Item;

import javax.annotation.Nullable;
import java.util.List;

/**
 * ItemDomainService
 *
 * @author fantasticmao
 * @since 2019-03-23
 */
public interface ItemDomainService {

    List<Item> listByName(@Nullable String nameZh, @Nullable String nameEn);

    List<Item> list(int page, int size);
}
