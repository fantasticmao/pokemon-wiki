package cn.fantasticmao.pokemon.web.service.impl;

import cn.fantasticmao.pokemon.web.bean.ItemBean;
import cn.fantasticmao.pokemon.web.repoistory.ItemRepository;
import cn.fantasticmao.pokemon.web.service.ItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public List<ItemBean> listAll() {
        return itemRepository.findAll().stream()
            .map(ItemBean::ofDomain)
            .collect(Collectors.toList());
    }
}
