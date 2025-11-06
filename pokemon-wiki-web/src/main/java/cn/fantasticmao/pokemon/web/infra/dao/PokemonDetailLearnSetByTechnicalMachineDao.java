package cn.fantasticmao.pokemon.web.infra.dao;

import cn.fantasticmao.pokemon.web.infra.model.PokemonDetailLearnSetByTechnicalMachinePo;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * PokemonDetailLearnSetByTechnicalMachineDao
 *
 * @author fantasticmao
 * @since 2019-08-20
 */
public interface PokemonDetailLearnSetByTechnicalMachineDao extends PagingAndSortingRepository<PokemonDetailLearnSetByTechnicalMachinePo, Integer> {

    @Query("SELECT * FROM t_pokemon_detail_learn_set_by_technical_machine WHERE idx IN (:ids)")
    List<PokemonDetailLearnSetByTechnicalMachinePo> findByIndexIn(@Param("ids") Collection<Integer> ids);
}
