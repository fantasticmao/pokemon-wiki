package cn.fantasticmao.pokemon.web.service;

import cn.fantasticmao.pokemon.web.bean.PokemonBean;

import javax.annotation.Nullable;
import java.util.List;

/**
 * PokemonService
 *
 * @author fantasticmao
 * @since 2018/7/29
 */
public interface PokemonService {

    List<PokemonBean> listByIndexOrName(@Nullable Integer index, @Nullable String nameZh,
                                        @Nullable String nameEn, @Nullable String form);

    List<PokemonBean> listByGenerationAndEggGroup(@Nullable Integer generation, @Nullable String eggGroup,
                                                  @Nullable Integer page, int size);
}
