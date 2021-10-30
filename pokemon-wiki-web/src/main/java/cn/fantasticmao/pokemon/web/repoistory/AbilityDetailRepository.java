package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.AbilityDetail;
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

    @Query(value = "SELECT * FROM pw_ability_detail WHERE id IN ?1", nativeQuery = true)
    List<AbilityDetail> findByIdIn(List<Integer> idList);
}
