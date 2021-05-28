package cn.fantasticmao.pokemon.wiki.service;

import cn.fantasticmao.pokemon.wiki.bean.ItemBean;
import cn.fantasticmao.pokemon.wiki.repoistory.ItemRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ItemServiceImpl
 *
 * @author maomao
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
