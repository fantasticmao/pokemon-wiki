package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.MoveDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * MoveDetailRepository
 *
 * @author maodh
 * @since 2018/8/29
 */
public interface MoveDetailRepository extends PagingAndSortingRepository<MoveDetail, Integer> {

    @Query(value = "SELECT * FROM pw_move_detail WHERE id IN ?1", nativeQuery = true)
    List<MoveDetail> findByIdIn(List<Integer> idList);
}
