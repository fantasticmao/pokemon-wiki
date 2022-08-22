package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.mundo.data.jdbc.NativeQuery;
import cn.fantasticmao.pokemon.web.domain.Nature;
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
    @NativeQuery("SELECT * FROM pw_nature")
    List<Nature> findAll();
}
