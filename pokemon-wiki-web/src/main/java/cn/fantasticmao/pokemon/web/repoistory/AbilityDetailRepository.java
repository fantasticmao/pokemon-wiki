package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.AbilityDetail;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * AbilityDetailRepository
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
public interface AbilityDetailRepository extends PagingAndSortingRepository<AbilityDetail, Integer> {

    @Query("SELECT * FROM t_ability_detail WHERE id IN (:ids)")
    List<AbilityDetail> findByIdIn(@Param("ids") Collection<Integer> ids);
}
