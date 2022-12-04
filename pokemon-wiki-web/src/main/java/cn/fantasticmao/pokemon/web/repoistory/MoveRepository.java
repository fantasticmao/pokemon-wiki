package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.mundo.data.jdbc.NativeQuery;
import cn.fantasticmao.pokemon.web.domain.Move;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * MoveRepository
 *
 * @author fantasticmao
 * @since 2018/8/5
 */
public interface MoveRepository extends PagingAndSortingRepository<Move, Integer> {

    @NativeQuery("SELECT * FROM pw_move WHERE name_zh LIKE '%' || ?1 || '%'")
    List<Move> findByNameZh(String nameZh);

    @NativeQuery("SELECT * FROM pw_move LIMIT ?2 OFFSET ?1")
    List<Move> find(int offset, int limit);
}
