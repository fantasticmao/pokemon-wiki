package cn.fantasticmao.pokemon.web.infra.dao;

import cn.fantasticmao.pokemon.web.infra.model.PokemonDetailPo;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * PokemonDetailDao
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
public interface PokemonDetailDao extends PagingAndSortingRepository<PokemonDetailPo, Integer> {

    @Query("SELECT * FROM t_pokemon_detail WHERE idx IN (:ids)")
    List<PokemonDetailPo> findByIndexIn(@Param("ids") Collection<Integer> ids);
}
