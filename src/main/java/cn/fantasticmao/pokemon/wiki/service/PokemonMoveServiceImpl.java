package cn.fantasticmao.pokemon.wiki.service;

import cn.fantasticmao.pokemon.wiki.domain.PokemonMove;
import cn.fantasticmao.pokemon.wiki.repoistory.PokemonMoveRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * PokemonMoveServiceImpl
 *
 * @author maodh
 * @since 2018/8/6
 */
@Service
public class PokemonMoveServiceImpl implements PokemonMoveService {
    @Resource
    private PokemonMoveRepository pokemonMoveRepository;

    @Override
    public List<PokemonMove> listAll() {
        return pokemonMoveRepository.findAll();
    }
}
