package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.Pokemon;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * PokemonRepository
 *
 * @author fantasticmao
 * @since 2018/7/29
 */
public interface PokemonRepository extends PagingAndSortingRepository<Pokemon, Integer>,
    PokemonDynamicRepository {

}
