package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.Pokemon;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * PokemonRepository
 *
 * @author fantasticmao
 * @since 2018/7/29
 */
public interface PokemonRepository extends PagingAndSortingRepository<Pokemon, Integer>,
    PokemonDynamicQueryRepository {

    @Query("SELECT * FROM t_pokemon WHERE name_zh LIKE '%' || :nameZh || '%'")
    List<Pokemon> findByNameZh(@Param("nameZh") String nameZh);

    @Query("SELECT * FROM t_pokemon WHERE idx = :index")
    List<Pokemon> findByIndex(@Param("index") Integer index);

}
