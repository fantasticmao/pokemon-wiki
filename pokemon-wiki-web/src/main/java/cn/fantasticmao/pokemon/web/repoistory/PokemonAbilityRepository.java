package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.PokemonAbility;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * PokemonAbilityRepository
 *
 * @author fantasticmao
 * @since 2018/8/5
 */
public interface PokemonAbilityRepository extends PagingAndSortingRepository<PokemonAbility, Integer> {

    @Query("SELECT * FROM t_pokemon_ability WHERE idx IN (:ids)")
    List<PokemonAbility> findByIndexIn(@Param("ids") Collection<Integer> ids);
}
