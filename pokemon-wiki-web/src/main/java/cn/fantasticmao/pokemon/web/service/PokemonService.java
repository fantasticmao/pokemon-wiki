package cn.fantasticmao.pokemon.web.service;

import cn.fantasticmao.pokemon.web.bean.PokemonBean;

import java.util.List;

/**
 * PokemonService
 *
 * @author fantasticmao
 * @since 2018/7/29
 */
public interface PokemonService {

    List<PokemonBean> listByIndexOrNameZh(Integer index, String nameZh);

    List<PokemonBean> listByGenerationAndEggGroup(int generation, String eggGroup, int page, int size);
}
