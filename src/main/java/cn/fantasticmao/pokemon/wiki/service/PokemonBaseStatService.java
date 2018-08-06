package cn.fantasticmao.pokemon.wiki.service;

import cn.fantasticmao.pokemon.wiki.domain.PokemonBaseStat;

import java.util.List;

/**
 * PokemonBaseStatService
 *
 * @author maodh
 * @since 2018/8/6
 */
public interface PokemonBaseStatService {

    List<PokemonBaseStat> listAll();
}
