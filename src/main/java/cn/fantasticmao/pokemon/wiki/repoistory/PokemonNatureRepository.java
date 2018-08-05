package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.wiki.domain.PokemonNature;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * PokemonNatureRepository
 *
 * @author maodh
 * @since 2018/8/5
 */
public interface PokemonNatureRepository extends PagingAndSortingRepository<PokemonNature, Integer> {
}
