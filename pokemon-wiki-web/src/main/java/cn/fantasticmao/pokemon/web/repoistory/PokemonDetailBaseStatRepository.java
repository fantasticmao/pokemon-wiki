package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.PokemonDetailBaseStat;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * PokemonDetailBaseStatRepository
 *
 * @author fantasticmao
 * @since 2018/8/5
 */
public interface PokemonDetailBaseStatRepository extends PagingAndSortingRepository<PokemonDetailBaseStat, Integer> {

    @Query("SELECT * FROM t_pokemon_detail_base_stat WHERE idx IN (:ids)")
    List<PokemonDetailBaseStat> findByIndexIn(@Param("ids") Collection<Integer> ids);
}
