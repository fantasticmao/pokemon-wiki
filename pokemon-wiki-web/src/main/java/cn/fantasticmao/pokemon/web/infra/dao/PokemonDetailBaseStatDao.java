package cn.fantasticmao.pokemon.web.infra.dao;

import cn.fantasticmao.pokemon.web.infra.model.PokemonDetailBaseStatPo;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * PokemonDetailBaseStatDao
 *
 * @author fantasticmao
 * @since 2018/8/5
 */
public interface PokemonDetailBaseStatDao extends PagingAndSortingRepository<PokemonDetailBaseStatPo, Integer> {

    @Query("SELECT * FROM t_pokemon_detail_base_stat WHERE idx IN (:ids)")
    List<PokemonDetailBaseStatPo> findByIndexIn(@Param("ids") Collection<Integer> ids);
}
