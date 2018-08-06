package cn.fantasticmao.pokemon.wiki.service;

import cn.fantasticmao.pokemon.wiki.domain.PokemonAbility;
import cn.fantasticmao.pokemon.wiki.repoistory.PokemonAbilityRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * PokemonAbilityServiceImpl
 *
 * @author maodh
 * @since 2018/8/6
 */
@Service
public class PokemonAbilityServiceImpl implements PokemonAbilityService {
    @Resource
    private PokemonAbilityRepository pokemonAbilityRepository;

    @Override
    public List<PokemonAbility> listAll() {
        return pokemonAbilityRepository.findAll();
    }
}
