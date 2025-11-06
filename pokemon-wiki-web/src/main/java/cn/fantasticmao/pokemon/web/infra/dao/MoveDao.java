package cn.fantasticmao.pokemon.web.infra.dao;

import cn.fantasticmao.pokemon.web.infra.model.MovePo;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * MoveDao
 *
 * @author fantasticmao
 * @since 2018/8/5
 */
public interface MoveDao extends PagingAndSortingRepository<MovePo, Integer>,
    MoveDynamicDao {

    @Query("SELECT * FROM t_move LIMIT :limit OFFSET :offset")
    List<MovePo> find(@Param("limit") int limit, @Param("offset") int offset);
}
