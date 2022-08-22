package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.mundo.data.jdbc.NativeQuery;
import cn.fantasticmao.pokemon.web.domain.AbilityDetail;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * AbilityDetailRepository
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
public interface AbilityDetailRepository extends PagingAndSortingRepository<AbilityDetail, Integer> {

    @NativeQuery("SELECT * FROM pw_ability_detail WHERE id IN ?1")
    List<AbilityDetail> findByIdIn(List<Integer> idList);
}
