package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.PokemonDetailLearnSetByTechnicalMachine;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * PokemonDetailLearnSetByTechnicalMachineRepository
 *
 * @author fantasticmao
 * @since 2019-08-20
 */
public interface PokemonDetailLearnSetByTechnicalMachineRepository extends PagingAndSortingRepository<PokemonDetailLearnSetByTechnicalMachine, Integer> {

    @Query("SELECT * FROM t_pokemon_detail_learn_set_by_technical_machine WHERE idx IN (:ids)")
    List<PokemonDetailLearnSetByTechnicalMachine> findByIndexIn(@Param("ids") Collection<Integer> ids);
}
