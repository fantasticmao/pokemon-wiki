package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.wiki.domain.PokemonAbility;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * PokemonAbilityRepository
 *
 * @author maodh
 * @since 2018/8/5
 */
public interface PokemonAbilityRepository extends PagingAndSortingRepository<PokemonAbility, Integer> {
}
