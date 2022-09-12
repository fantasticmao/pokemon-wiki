package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.mundo.data.jdbc.NativeQuery;
import cn.fantasticmao.pokemon.web.domain.PokemonDetailLearnSetByLevelingUp;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * PokemonDetailLearnSetByLevelingUpRepository
 *
 * @author fantasticmao
 * @since 2019-08-20
 */
public interface PokemonDetailLearnSetByLevelingUpRepository extends PagingAndSortingRepository<PokemonDetailLearnSetByLevelingUp, Integer> {

    @NativeQuery("SELECT * FROM pw_pokemon_detail_learn_set_by_leveling_up WHERE idx IN ?1")
    List<PokemonDetailLearnSetByLevelingUp> findByIndexIn(List<Integer> idList);
}
