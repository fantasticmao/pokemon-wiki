package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.wiki.domain.Pokemon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * PokemonRepository
 *
 * @author maodh
 * @since 2018/7/29
 */
public interface PokemonRepository extends PagingAndSortingRepository<Pokemon, Integer> {

    @Query(value = "SELECT * FROM pw_pokemon", nativeQuery = true)
    List<Pokemon> findAll();

    @Query(value = "SELECT * FROM pw_pokemon WHERE nameZh LIKE CONCAT('%', ?1, '%')", nativeQuery = true)
    List<Pokemon> findByName(String name);

    @Query(value = "SELECT * FROM pw_pokemon WHERE type1 = ?1 UNION ALL SELECT * FROM pw_pokemon WHERE type2 = ?1", nativeQuery = true)
    List<Pokemon> findByType(String type);

    @Query(value = "SELECT * FROM pw_pokemon WHERE generation = ?1", nativeQuery = true)
    List<Pokemon> findByGeneration(int generation);
}
