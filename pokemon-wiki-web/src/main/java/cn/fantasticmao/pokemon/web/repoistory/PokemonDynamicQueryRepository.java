package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.Pokemon;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * PokemonDynamicQueryRepository
 *
 * @author fantasticmao
 * @since 2022-12-12
 */
public interface PokemonDynamicQueryRepository {

    List<Pokemon> findByIndexAndForm(@Nonnull Integer index, @Nullable String form);

    List<Pokemon> findByNameAndForm(@Nullable String nameZh, @Nullable String nameEn,
                                    @Nullable String form);

    List<Pokemon> findByGenerationAndEggGroup(@Nullable Integer generation, @Nullable String eggGroup,
                                              @Nullable Integer page, int size);

}
