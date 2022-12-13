package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.Pokemon;

import javax.annotation.Nullable;
import java.util.List;

/**
 * PokemonComplexRepository
 *
 * @author fantasticmao
 * @since 2022-12-12
 */
public interface PokemonComplexRepository {

    List<Pokemon> listByGenerationAndEggGroup(@Nullable Integer generation, @Nullable String eggGroup,
                                              @Nullable Integer page, int size);

}
