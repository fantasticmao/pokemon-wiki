package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.wiki.domain.PokemonBaseStat;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * PokemonBaseStatRepository
 *
 * @author maodh
 * @since 2018/8/5
 */
public interface PokemonBaseStatRepository extends PagingAndSortingRepository<PokemonBaseStat, Integer> {
}
