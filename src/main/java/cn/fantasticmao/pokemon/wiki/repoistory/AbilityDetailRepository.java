package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.wiki.domain.AbilityDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * AbilityDetailRepository
 *
 * @author maodh
 * @since 2018/8/29
 */
public interface AbilityDetailRepository extends PagingAndSortingRepository<AbilityDetail, Integer> {

    @Query(value = "SELECT * FROM pw_ability_detail", nativeQuery = true)
    List<AbilityDetail> findAll();
}
