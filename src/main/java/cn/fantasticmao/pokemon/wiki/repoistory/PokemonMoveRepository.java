package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.wiki.domain.PokemonMove;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * PokemonMoveRepository
 *
 * @author maodh
 * @since 2018/8/5
 */
public interface PokemonMoveRepository extends PagingAndSortingRepository<PokemonMove, Integer> {

    @Query(value = "SELECT * FROM pw_pokemon_move", nativeQuery = true)
    List<PokemonMove> findAll();
}
