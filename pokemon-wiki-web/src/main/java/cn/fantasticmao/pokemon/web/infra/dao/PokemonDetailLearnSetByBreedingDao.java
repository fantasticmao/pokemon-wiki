package cn.fantasticmao.pokemon.web.infra.dao;

import cn.fantasticmao.pokemon.web.infra.model.PokemonDetailLearnSetByBreedingPo;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * PokemonDetailLearnSetByBreedingDao
 *
 * @author fantasticmao
 * @since 2019-08-20
 */
public interface PokemonDetailLearnSetByBreedingDao extends PagingAndSortingRepository<PokemonDetailLearnSetByBreedingPo, Integer> {

    @Query("SELECT * FROM t_pokemon_detail_learn_set_by_breeding WHERE idx IN (:ids)")
    List<PokemonDetailLearnSetByBreedingPo> findByIndexIn(@Param("ids") Collection<Integer> ids);
}
