package cn.fantasticmao.pokemon.wiki.service;

import cn.fantasticmao.pokemon.wiki.bean.PokemonBean;

import java.util.List;

/**
 * PokemonService
 *
 * @author maodh
 * @since 2018/7/29
 */
public interface PokemonService {

    List<PokemonBean> listByNameZh(String nameZh);

    List<PokemonBean> listByGeneration(int generation);
}
