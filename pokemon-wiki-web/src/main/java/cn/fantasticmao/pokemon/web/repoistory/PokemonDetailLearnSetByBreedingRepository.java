package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.PokemonDetailLearnSetByBreeding;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * PokemonDetailLearnSetByBreedingRepository
 *
 * @author fantasticmao
 * @since 2019-08-20
 */
public interface PokemonDetailLearnSetByBreedingRepository extends PagingAndSortingRepository<PokemonDetailLearnSetByBreeding, Integer> {

    @Query(value = "SELECT * FROM pw_pokemon_detail_learn_set_by_breeding WHERE `index` IN ?1", nativeQuery = true)
    List<PokemonDetailLearnSetByBreeding> findByIndexIn(List<Integer> idList);
}
