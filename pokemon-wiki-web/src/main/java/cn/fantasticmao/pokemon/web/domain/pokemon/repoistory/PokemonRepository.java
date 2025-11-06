package cn.fantasticmao.pokemon.web.domain.pokemon.repoistory;

import cn.fantasticmao.pokemon.web.domain.pokemon.model.Pokemon;

import javax.annotation.Nullable;
import java.util.List;

/**
 * PokemonDao
 *
 * @author maodaohe
 * @since 2025-11-05
 */
public interface PokemonRepository {

    List<Pokemon> findByIndexAndForm(Integer index, @Nullable String form);

    List<Pokemon> findByNameAndForm(@Nullable String nameZh, @Nullable String nameEn,
                                    @Nullable String form);

    List<Pokemon> findByGenerationAndEggGroup(@Nullable Integer generation, @Nullable String eggGroup,
                                              @Nullable Integer page, int size);

}
