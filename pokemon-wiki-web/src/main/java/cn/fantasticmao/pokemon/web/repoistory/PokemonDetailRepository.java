package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.PokemonDetail;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * PokemonDetailRepository
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
public interface PokemonDetailRepository extends PagingAndSortingRepository<PokemonDetail, Integer> {

    @Query("SELECT * FROM t_pokemon_detail WHERE idx IN (:ids)")
    List<PokemonDetail> findByIndexIn(@Param("ids") Collection<Integer> ids);
}
