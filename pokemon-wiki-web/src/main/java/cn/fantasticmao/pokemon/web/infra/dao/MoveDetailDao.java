package cn.fantasticmao.pokemon.web.infra.dao;

import cn.fantasticmao.pokemon.web.infra.model.MoveDetailPo;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * MoveDetailDao
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
public interface MoveDetailDao extends PagingAndSortingRepository<MoveDetailPo, Integer> {

    @Query("SELECT * FROM t_move_detail WHERE id IN (:ids)")
    List<MoveDetailPo> findByIdIn(@Param("ids") Collection<Integer> ids);
}
