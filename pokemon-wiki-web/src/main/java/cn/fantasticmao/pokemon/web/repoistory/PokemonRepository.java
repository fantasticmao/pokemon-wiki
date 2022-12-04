package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.mundo.data.jdbc.NativeQuery;
import cn.fantasticmao.pokemon.web.domain.Pokemon;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * PokemonRepository
 *
 * @author fantasticmao
 * @since 2018/7/29
 */
public interface PokemonRepository extends PagingAndSortingRepository<Pokemon, Integer> {

    @Nonnull
    @NativeQuery("SELECT * FROM pw_pokemon")
    List<Pokemon> findAll();

    @NativeQuery("SELECT * FROM pw_pokemon WHERE name_zh LIKE '%' || ?1 || '%'")
    List<Pokemon> findByNameZh(String nameZh);

    @NativeQuery("SELECT * FROM pw_pokemon WHERE idx = ?1")
    List<Pokemon> findByIndex(Integer index);

    @NativeQuery("SELECT * FROM pw_pokemon WHERE generation = ?1")
    List<Pokemon> findByGeneration(int generation);
}
