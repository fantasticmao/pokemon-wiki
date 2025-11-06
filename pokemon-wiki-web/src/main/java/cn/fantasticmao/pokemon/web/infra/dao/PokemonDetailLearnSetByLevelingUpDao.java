package cn.fantasticmao.pokemon.web.infra.dao;

import cn.fantasticmao.pokemon.web.infra.model.PokemonDetailLearnSetByLevelingUpPo;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * PokemonDetailLearnSetByLevelingUpDao
 *
 * @author fantasticmao
 * @since 2019-08-20
 */
public interface PokemonDetailLearnSetByLevelingUpDao extends PagingAndSortingRepository<PokemonDetailLearnSetByLevelingUpPo, Integer> {

    @Query("SELECT * FROM t_pokemon_detail_learn_set_by_leveling_up WHERE idx IN (:ids)")
    List<PokemonDetailLearnSetByLevelingUpPo> findByIndexIn(@Param("ids") Collection<Integer> ids);
}
