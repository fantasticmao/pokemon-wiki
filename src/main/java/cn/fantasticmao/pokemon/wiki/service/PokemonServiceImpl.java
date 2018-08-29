package cn.fantasticmao.pokemon.wiki.service;

import cn.fantasticmao.pokemon.wiki.domain.Pokemon;
import cn.fantasticmao.pokemon.wiki.repoistory.PokemonRepository;
import com.mundo.core.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * PokemonServiceImpl
 *
 * @author maodh
 * @since 2018/7/29
 */
@Service
public class PokemonServiceImpl implements PokemonService {
    @Resource
    private PokemonRepository pokemonRepository;

    @Override
    public List<Pokemon> listAll() {
        return pokemonRepository.findAll();
    }

    @Override
    public List<Pokemon> listByNameZh(String name) {
        if (StringUtil.isEmpty(name)) return Collections.emptyList();
        List<Pokemon> pokemonList = pokemonRepository.findByNameZh(name);
        Collections.sort(pokemonList);
        return pokemonList;
    }

    @Override
    public List<Pokemon> listByType(String type) {
        List<Pokemon> pokemonList = pokemonRepository.findByType(type);
        Collections.sort(pokemonList);
        return pokemonList;
    }

    @Override
    public List<Pokemon> listByGeneration(int generation) {
        return generation == 0 ? pokemonRepository.findAll() : pokemonRepository.findByGeneration(generation);
    }
}
