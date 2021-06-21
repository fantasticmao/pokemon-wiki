package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.wiki.domain.Move;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * MoveRepository
 *
 * @author maodh
 * @since 2018/8/5
 */
public interface MoveRepository extends PagingAndSortingRepository<Move, Integer> {

    @Query(value = "SELECT * FROM pw_move WHERE nameZh LIKE '%' || ?1 || '%'", nativeQuery = true)
    List<Move> findByNameZh(String nameZh);
}
