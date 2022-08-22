package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.mundo.data.jdbc.NativeQuery;
import cn.fantasticmao.pokemon.web.domain.PokemonDetailLearnSetByTechnicalMachine;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * PokemonDetailLearnSetByTechnicalMachineRepository
 *
 * @author fantasticmao
 * @since 2019-08-20
 */
public interface PokemonDetailLearnSetByTechnicalMachineRepository extends PagingAndSortingRepository<PokemonDetailLearnSetByTechnicalMachine, Integer> {

    @NativeQuery("SELECT * FROM pw_pokemon_detail_learn_set_by_technical_machine WHERE `index` IN ?1")
    List<PokemonDetailLearnSetByTechnicalMachine> findByIndexIn(List<Integer> idList);
}
