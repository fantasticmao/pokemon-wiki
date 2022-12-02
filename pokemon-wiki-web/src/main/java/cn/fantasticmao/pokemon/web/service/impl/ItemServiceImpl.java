package cn.fantasticmao.pokemon.web.service.impl;

import cn.fantasticmao.mundo.core.util.PageUtil;
import cn.fantasticmao.pokemon.web.bean.ItemBean;
import cn.fantasticmao.pokemon.web.domain.Item;
import cn.fantasticmao.pokemon.web.repoistory.ItemRepository;
import cn.fantasticmao.pokemon.web.service.ItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ItemServiceImpl
 *
 * @author fantasticmao
 * @since 2019-03-23
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Resource
    private ItemRepository itemRepository;

    @Override
    public List<ItemBean> listByNameZh(@Nonnull String nameZh) {
        List<Item> itemList = itemRepository.findByNameZh(nameZh);
        return itemList.stream()
            .map(ItemBean::ofDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<ItemBean> list(int page, int size) {
        if (size < 1) {
            return Collections.emptyList();
        }
        // FIXME page < 0
        List<Item> itemList = page < 0
            ? itemRepository.findAll()
            : itemRepository.find(PageUtil.offset(page, size), PageUtil.limit(size));
        return itemList.stream()
            .map(ItemBean::ofDomain)
            .collect(Collectors.toList());
    }
}
