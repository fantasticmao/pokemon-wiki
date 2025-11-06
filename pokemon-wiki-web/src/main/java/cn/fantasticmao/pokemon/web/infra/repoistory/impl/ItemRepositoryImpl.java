package cn.fantasticmao.pokemon.web.infra.repoistory.impl;

import cn.fantasticmao.mundo.core.util.PageUtil;
import cn.fantasticmao.pokemon.web.domain.item.model.Item;
import cn.fantasticmao.pokemon.web.domain.item.repoistory.ItemRepository;
import cn.fantasticmao.pokemon.web.infra.converter.ItemConverter;
import cn.fantasticmao.pokemon.web.infra.dao.ItemDao;
import cn.fantasticmao.pokemon.web.infra.model.ItemPo;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ItemRepositoryImpl
 *
 * @author maodaohe
 * @since 2025-11-06
 */
@Repository
public class ItemRepositoryImpl implements ItemRepository {
    @Resource
    private ItemDao dao;
    @Resource
    private ItemConverter converter;

    @Override
    public List<Item> listByName(@Nullable String nameZh, @Nullable String nameEn) {
        if (StringUtils.isAllEmpty(nameZh, nameEn)) {
            return Collections.emptyList();
        }

        List<ItemPo> itemPoList = dao.findByName(nameZh, nameEn);
        return itemPoList.stream()
            .map(converter::toItem)
            .collect(Collectors.toList());
    }

    @Override
    public List<Item> list(int page, int size) {
        if (page < 0 || size < 1) {
            return Collections.emptyList();
        }

        List<ItemPo> itemPoList = dao.find(PageUtil.limit(size), PageUtil.offset(page, size));
        return itemPoList.stream()
            .map(converter::toItem)
            .collect(Collectors.toList());
    }

}
