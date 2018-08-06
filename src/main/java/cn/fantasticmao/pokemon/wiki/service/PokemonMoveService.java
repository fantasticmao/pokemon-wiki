package cn.fantasticmao.pokemon.wiki.service;

import cn.fantasticmao.pokemon.wiki.domain.PokemonMove;

import java.util.List;

/**
 * PokemonMoveService
 *
 * @author maodh
 * @since 2018/8/6
 */
public interface PokemonMoveService {

    List<PokemonMove> listAll();
}
