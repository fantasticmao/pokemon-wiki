package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.Pokemon;
import org.springframework.data.domain.Pageable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * PokemonComplexRepository
 *
 * @author fantasticmao
 * @since 2022-12-12
 */
public interface PokemonComplexRepository {

    List<Pokemon> findByGeneration(@Nullable Integer generation, @Nonnull Pageable pageable);

}
