package cn.fantasticmao.pokemon.web.infra.dao;

import cn.fantasticmao.pokemon.web.infra.model.AbilityDetailPo;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * AbilityDetailDao
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
public interface AbilityDetailDao extends PagingAndSortingRepository<AbilityDetailPo, Integer> {

    @Query("SELECT * FROM t_ability_detail WHERE id IN (:ids)")
    List<AbilityDetailPo> findByIdIn(@Param("ids") Collection<Integer> ids);
}
