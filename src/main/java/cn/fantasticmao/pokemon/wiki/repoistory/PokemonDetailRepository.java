package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.wiki.domain.PokemonDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * PokemonDetailRepository
 *
 * @author maodh
 * @since 2018/8/29
 */
public interface PokemonDetailRepository extends PagingAndSortingRepository<PokemonDetail, Integer> {

    @Query(value = "SELECT * FROM pw_pokemon_detail WHERE `index` IN ?1", nativeQuery = true)
    List<PokemonDetail> findByIndexIn(List<Integer> idList);
}
