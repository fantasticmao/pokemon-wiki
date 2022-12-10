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
public interface MoveRepository extends PagingAndSortingRepository<Move, Integer> {

    @Query("SELECT * FROM t_move WHERE name_zh LIKE '%' || :nameZh || '%'")
    List<Move> findByNameZh(@Param("nameZh") String nameZh);

    @Query("SELECT * FROM t_move LIMIT :limit OFFSET :offset")
    List<Move> find(@Param("offset") int offset, @Param("limit") int limit);
}
