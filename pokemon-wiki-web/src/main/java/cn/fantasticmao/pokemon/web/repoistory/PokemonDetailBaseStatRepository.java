package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.mundo.data.jdbc.NativeQuery;
import cn.fantasticmao.pokemon.web.domain.PokemonDetailBaseStat;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * PokemonDetailBaseStatRepository
 *
 * @author fantasticmao
 * @since 2018/8/5
 */
public interface PokemonDetailBaseStatRepository extends PagingAndSortingRepository<PokemonDetailBaseStat, Integer> {

    @NativeQuery("SELECT * FROM pw_pokemon_detail_base_stat WHERE `index` IN ?1")
    List<PokemonDetailBaseStat> findByIndexIn(List<Integer> idList);
}
