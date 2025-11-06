package cn.fantasticmao.pokemon.web.infra.dao;

import cn.fantasticmao.pokemon.web.infra.model.PokemonPo;

import javax.annotation.Nullable;
import java.util.List;

/**
 * PokemonDynamicDao
 *
 * @author fantasticmao
 * @since 2022-12-12
 */
public interface PokemonDynamicDao {

    List<PokemonPo> findByIndexAndForm(Integer index, @Nullable String form);

    List<PokemonPo> findByNameAndForm(@Nullable String nameZh, @Nullable String nameEn,
                                      @Nullable String form);

    List<PokemonPo> findByGenerationAndEggGroup(@Nullable Integer generation, @Nullable String eggGroup,
                                                @Nullable Integer page, int size);

}
