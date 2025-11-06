package cn.fantasticmao.pokemon.web.domain.pokemon.service.impl;

import cn.fantasticmao.pokemon.web.domain.pokemon.model.Pokemon;
import cn.fantasticmao.pokemon.web.domain.pokemon.repoistory.PokemonRepository;
import cn.fantasticmao.pokemon.web.domain.pokemon.service.PokemonDomainService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

/**
 * PokemonDomainServiceImpl
 *
 * @author fantasticmao
 * @since 2018/7/29
 */
@Service
public class PokemonDomainServiceImpl implements PokemonDomainService {
    @Resource
    private PokemonRepository pokemonRepository;

    @Override
    public List<Pokemon> listByIndexOrName(@Nullable Integer index, @Nullable String nameZh,
                                           @Nullable String nameEn, @Nullable String form) {
        if ((index == null || index <= 0) && StringUtils.isAllEmpty(nameZh, nameEn)) {
            return Collections.emptyList();
        }

        return (index != null && index > 0)
            ? pokemonRepository.findByIndexAndForm(index, form) // 按全国图鉴编号查找
            : pokemonRepository.findByNameAndForm(nameZh, nameEn, form); // 按名称查找
    }

    @Override
    public List<Pokemon> listByGenerationAndEggGroup(Integer generation, @Nullable String eggGroup,
                                                     @Nullable Integer page, int size) {
        return pokemonRepository.findByGenerationAndEggGroup(generation, eggGroup, page, size);
    }
}
