package cn.fantasticmao.pokemon.wiki.service;

import cn.fantasticmao.pokemon.wiki.domain.PokemonNature;

import java.util.List;

/**
 * PokemonNatureService
 *
 * @author maodh
 * @since 2018/8/6
 */
public interface PokemonNatureService {

    List<PokemonNature> listAll();
}
