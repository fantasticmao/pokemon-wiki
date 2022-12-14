package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.MoveDetail;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * MoveDetailRepository
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
public interface MoveDetailRepository extends PagingAndSortingRepository<MoveDetail, Integer> {

    @Query("SELECT * FROM t_move_detail WHERE id IN (:ids)")
    List<MoveDetail> findByIdIn(@Param("ids") Collection<Integer> ids);
}
