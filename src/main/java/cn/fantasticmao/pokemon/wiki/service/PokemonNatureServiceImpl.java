package cn.fantasticmao.pokemon.wiki.service;

import cn.fantasticmao.pokemon.wiki.domain.PokemonNature;
import cn.fantasticmao.pokemon.wiki.repoistory.PokemonNatureRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * PokemonNatureServiceImpl
 *
 * @author maodh
 * @since 2018/8/6
 */
@Service
public class PokemonNatureServiceImpl implements PokemonNatureService {
    @Resource
    private PokemonNatureRepository pokemonNatureRepository;

    @Override
    public List<PokemonNature> listAll() {
        return pokemonNatureRepository.findAll();
    }
}
