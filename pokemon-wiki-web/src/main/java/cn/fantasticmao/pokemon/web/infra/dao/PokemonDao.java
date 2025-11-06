package cn.fantasticmao.pokemon.web.infra.dao;

import cn.fantasticmao.pokemon.web.infra.model.PokemonPo;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * PokemonDao
 *
 * @author fantasticmao
 * @since 2018/7/29
 */
public interface PokemonDao extends PagingAndSortingRepository<PokemonPo, Integer>,
    PokemonDynamicDao {

}
