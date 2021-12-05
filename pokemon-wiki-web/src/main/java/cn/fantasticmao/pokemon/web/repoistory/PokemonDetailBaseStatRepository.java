package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.PokemonDetailBaseStat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * PokemonDetailBaseStatRepository
 *
 * @author fantasticmao
 * @since 2018/8/5
 */
public interface PokemonDetailBaseStatRepository extends PagingAndSortingRepository<PokemonDetailBaseStat, Integer> {

    @Query(value = "SELECT * FROM pw_pokemon_detail_base_stat WHERE `index` IN ?1", nativeQuery = true)
    List<PokemonDetailBaseStat> findByIndexIn(List<Integer> idList);
}
