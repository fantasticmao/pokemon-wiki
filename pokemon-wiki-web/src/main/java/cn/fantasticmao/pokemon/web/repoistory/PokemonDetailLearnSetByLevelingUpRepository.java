package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.PokemonDetailLearnSetByLevelingUp;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * PokemonDetailLearnSetByLevelingUpRepository
 *
 * @author fantasticmao
 * @since 2019-08-20
 */
public interface PokemonDetailLearnSetByLevelingUpRepository extends PagingAndSortingRepository<PokemonDetailLearnSetByLevelingUp, Integer> {

    @Query("SELECT * FROM t_pokemon_detail_learn_set_by_leveling_up WHERE idx IN (:ids)")
    List<PokemonDetailLearnSetByLevelingUp> findByIndexIn(@Param("ids") Collection<Integer> ids);
}
