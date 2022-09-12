package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.mundo.data.jdbc.NativeQuery;
import cn.fantasticmao.pokemon.web.domain.PokemonDetail;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * PokemonDetailRepository
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
public interface PokemonDetailRepository extends PagingAndSortingRepository<PokemonDetail, Integer> {

    @NativeQuery("SELECT * FROM pw_pokemon_detail WHERE idx IN ?1")
    List<PokemonDetail> findByIndexIn(List<Integer> idList);
}
