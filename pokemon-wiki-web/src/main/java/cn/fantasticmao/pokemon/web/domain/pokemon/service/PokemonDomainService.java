package cn.fantasticmao.pokemon.web.domain.pokemon.service;

import cn.fantasticmao.pokemon.web.domain.pokemon.model.Pokemon;

import javax.annotation.Nullable;
import java.util.List;

/**
 * PokemonDomainService
 *
 * @author fantasticmao
 * @since 2018/7/29
 */
public interface PokemonDomainService {

    List<Pokemon> listByIndexOrName(@Nullable Integer index, @Nullable String nameZh,
                                    @Nullable String nameEn, @Nullable String form);

    List<Pokemon> listByGenerationAndEggGroup(Integer generation, @Nullable String eggGroup,
                                              @Nullable Integer page, int size);

}
