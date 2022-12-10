package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.Nature;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * NatureRepository
 *
 * @author fantasticmao
 * @since 2018/8/5
 */
public interface NatureRepository extends PagingAndSortingRepository<Nature, Integer> {

    @Nonnull
    @Query("SELECT * FROM t_nature")
    List<Nature> findAll();
}
