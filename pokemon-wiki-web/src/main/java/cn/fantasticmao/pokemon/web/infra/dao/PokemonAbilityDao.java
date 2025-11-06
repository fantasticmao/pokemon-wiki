package cn.fantasticmao.pokemon.web.infra.dao;

import cn.fantasticmao.pokemon.web.infra.model.PokemonAbilityPo;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * PokemonAbilityDao
 *
 * @author fantasticmao
 * @since 2018/8/5
 */
public interface PokemonAbilityDao extends PagingAndSortingRepository<PokemonAbilityPo, Integer> {

    @Query("SELECT * FROM t_pokemon_ability WHERE idx IN (:ids)")
    List<PokemonAbilityPo> findByIndexIn(@Param("ids") Collection<Integer> ids);
}
