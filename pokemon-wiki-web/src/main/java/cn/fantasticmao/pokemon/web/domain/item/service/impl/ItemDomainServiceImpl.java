package cn.fantasticmao.pokemon.web.domain.item.service.impl;

import cn.fantasticmao.pokemon.web.domain.item.model.Item;
import cn.fantasticmao.pokemon.web.domain.item.repoistory.ItemRepository;
import cn.fantasticmao.pokemon.web.domain.item.service.ItemDomainService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

/**
 * ItemDomainServiceImpl
 *
 * @author fantasticmao
 * @since 2019-03-23
 */
@Service
public class ItemDomainServiceImpl implements ItemDomainService {
    @Resource
    private ItemRepository itemRepository;

    @Override
    public List<Item> listByName(@Nullable String nameZh, @Nullable String nameEn) {
        if (StringUtils.isAllEmpty(nameZh, nameEn)) {
            return Collections.emptyList();
        }
        return itemRepository.listByName(nameZh, nameEn);
    }

    @Override
    public List<Item> list(int page, int size) {
        if (page < 0 || size < 1) {
            return Collections.emptyList();
        }
        return itemRepository.list(page, size);
    }
}
