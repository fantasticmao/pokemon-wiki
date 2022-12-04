package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.mundo.data.jdbc.NativeQuery;
import cn.fantasticmao.pokemon.web.domain.PokemonDetailLearnSetByBreeding;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.List;

/**
 * PokemonDetailLearnSetByBreedingRepository
 *
 * @author fantasticmao
 * @since 2019-08-20
 */
public interface PokemonDetailLearnSetByBreedingRepository extends PagingAndSortingRepository<PokemonDetailLearnSetByBreeding, Integer> {

    @NativeQuery("SELECT * FROM pw_pokemon_detail_learn_set_by_breeding WHERE idx IN ?1")
    List<PokemonDetailLearnSetByBreeding> findByIndexIn(Collection<Integer> ids);
}
