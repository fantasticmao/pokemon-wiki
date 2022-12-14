package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.PokemonDetailLearnSetByBreeding;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * PokemonDetailLearnSetByBreedingRepository
 *
 * @author fantasticmao
 * @since 2019-08-20
 */
public interface PokemonDetailLearnSetByBreedingRepository extends PagingAndSortingRepository<PokemonDetailLearnSetByBreeding, Integer> {

    @Query("SELECT * FROM t_pokemon_detail_learn_set_by_breeding WHERE idx IN (:ids)")
    List<PokemonDetailLearnSetByBreeding> findByIndexIn(@Param("ids") Collection<Integer> ids);
}
