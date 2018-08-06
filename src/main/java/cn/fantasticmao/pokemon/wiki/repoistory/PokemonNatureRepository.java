package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.wiki.domain.PokemonNature;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * PokemonNatureRepository
 *
 * @author maodh
 * @since 2018/8/5
 */
public interface PokemonNatureRepository extends PagingAndSortingRepository<PokemonNature, Integer> {

    @Query(value = "SELECT * FROM pw_pokemon_nature", nativeQuery = true)
    List<PokemonNature> findAll();
}
