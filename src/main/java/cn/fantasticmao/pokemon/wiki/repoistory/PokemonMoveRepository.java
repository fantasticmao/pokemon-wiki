package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.wiki.domain.PokemonMove;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * PokemonMoveRepository
 *
 * @author maodh
 * @since 2018/8/5
 */
public interface PokemonMoveRepository extends PagingAndSortingRepository<PokemonMove, Integer> {
}
