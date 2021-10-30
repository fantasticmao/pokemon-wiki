package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.PokemonDetailLearnSetByLevelingUp;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * PokemonDetailLearnSetByLevelingUpRepository
 *
 * @author maomao
 * @since 2019-08-20
 */
public interface PokemonDetailLearnSetByLevelingUpRepository extends PagingAndSortingRepository<PokemonDetailLearnSetByLevelingUp, Integer> {

    @Query(value = "SELECT * FROM pw_pokemon_detail_learn_set_by_leveling_up WHERE `index` IN ?1", nativeQuery = true)
    List<PokemonDetailLearnSetByLevelingUp> findByIndexIn(List<Integer> idList);
}
