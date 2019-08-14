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

    default List<PokemonBean> listByNameZh(String nameZh) {
        return listByIndexOrNameZh(null, nameZh);
    }

    List<PokemonBean> listByIndexOrNameZh(Integer index, String nameZh);

    List<PokemonBean> listByGenerationAndEggGroup(int generation, String eggGroup);
}
