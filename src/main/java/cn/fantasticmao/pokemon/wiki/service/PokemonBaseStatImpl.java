package cn.fantasticmao.pokemon.wiki.service;

import cn.fantasticmao.pokemon.wiki.domain.PokemonBaseStat;
import cn.fantasticmao.pokemon.wiki.repoistory.PokemonBaseStatRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * PokemonBaseStatImpl
 *
 * @author maodh
 * @since 2018/8/6
 */
@Service
public class PokemonBaseStatImpl implements PokemonBaseStatService {
    @Resource
    private PokemonBaseStatRepository pokemonBaseStatRepository;

    @Override
    public List<PokemonBaseStat> listAll() {
        return pokemonBaseStatRepository.findAll();
    }
}
