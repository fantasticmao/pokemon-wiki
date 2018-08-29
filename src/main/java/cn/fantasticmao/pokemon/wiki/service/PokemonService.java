package cn.fantasticmao.pokemon.wiki.service;

import cn.fantasticmao.pokemon.wiki.domain.Pokemon;

import java.util.List;

/**
 * PokemonService
 *
 * @author maodh
 * @since 2018/7/29
 */
public interface PokemonService {

    List<Pokemon> listAll();

    List<Pokemon> listByNameZh(String name);

    List<Pokemon> listByType(String type);

    List<Pokemon> listByGeneration(int generation);
}
