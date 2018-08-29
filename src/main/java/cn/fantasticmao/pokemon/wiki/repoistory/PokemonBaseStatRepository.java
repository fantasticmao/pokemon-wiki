package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.wiki.domain.PokemonBaseStat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * PokemonBaseStatRepository
 *
 * @author maodh
 * @since 2018/8/5
 */
public interface PokemonBaseStatRepository extends PagingAndSortingRepository<PokemonBaseStat, Integer> {

    @Query(value = "SELECT * FROM pw_pokemon_base_stat WHERE nameZh LIKE CONCAT('%', ?1, '%')", nativeQuery = true)
    List<PokemonBaseStat> findByNameZh(String nameZh);
}
