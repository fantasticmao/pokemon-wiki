package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.Nature;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * NatureRepository
 *
 * @author maodh
 * @since 2018/8/5
 */
public interface NatureRepository extends PagingAndSortingRepository<Nature, Integer> {

    @Query(value = "SELECT * FROM pw_nature", nativeQuery = true)
    List<Nature> findAll();
}
