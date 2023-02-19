package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.Move;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * MoveRepository
 *
 * @author fantasticmao
 * @since 2018/8/5
 */
public interface MoveRepository extends PagingAndSortingRepository<Move, Integer>,
    MoveDynamicRepository {

    @Query("SELECT * FROM t_move LIMIT :limit OFFSET :offset")
    List<Move> find(@Param("limit") int limit, @Param("offset") int offset);
}
