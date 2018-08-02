package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.wiki.domain.Pokemon;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * PokemonRepository
 *
 * @author maodh
 * @since 2018/7/29
 */
public interface PokemonRepository extends PagingAndSortingRepository<Pokemon, Integer> {
}
