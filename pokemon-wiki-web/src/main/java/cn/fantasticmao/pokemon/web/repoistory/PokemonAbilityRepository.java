package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.mundo.data.jdbc.NativeQuery;
import cn.fantasticmao.pokemon.web.domain.PokemonAbility;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * PokemonAbilityRepository
 *
 * @author fantasticmao
 * @since 2018/8/5
 */
public interface PokemonAbilityRepository extends PagingAndSortingRepository<PokemonAbility, Integer> {

    @NativeQuery("SELECT * FROM pw_pokemon_ability WHERE idx IN ?1")
    List<PokemonAbility> findByIndexIn(List<Integer> idList);
}
