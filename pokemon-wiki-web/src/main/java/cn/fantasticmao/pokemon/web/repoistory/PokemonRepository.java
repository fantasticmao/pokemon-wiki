package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.Pokemon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

/**
 * PokemonRepository
 *
 * @author fantasticmao
 * @since 2018/7/29
 */
public interface PokemonRepository extends PagingAndSortingRepository<Pokemon, Integer> {

    @Query(value = "SELECT * FROM pw_pokemon", nativeQuery = true)
    List<Pokemon> findAll();

    @Query(value = "SELECT * FROM pw_pokemon WHERE nameZh LIKE '%' || ?1 || '%'", nativeQuery = true)
    List<Pokemon> findByNameZh(String nameZh);

    @Query(value = "SELECT * FROM pw_pokemon WHERE `index` = ?1", nativeQuery = true)
    Optional<Pokemon> findByIndex(Integer index);

    @Query(value = "SELECT * FROM pw_pokemon WHERE generation = ?1", nativeQuery = true)
    List<Pokemon> findByGeneration(int generation);
}
