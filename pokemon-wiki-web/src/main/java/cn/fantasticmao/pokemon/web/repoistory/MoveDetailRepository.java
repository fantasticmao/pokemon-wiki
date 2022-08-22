package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.mundo.data.jdbc.NativeQuery;
import cn.fantasticmao.pokemon.web.domain.MoveDetail;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * MoveDetailRepository
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
public interface MoveDetailRepository extends PagingAndSortingRepository<MoveDetail, Integer> {

    @NativeQuery("SELECT * FROM pw_move_detail WHERE id IN ?1")
    List<MoveDetail> findByIdIn(List<Integer> idList);
}
