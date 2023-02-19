package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.Item;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * ItemRepository
 *
 * @author fantasticmao
 * @since 2019-03-23
 */
public interface ItemRepository extends PagingAndSortingRepository<Item, Integer>,
    ItemDynamicRepository {

    @Nonnull
    @Query("SELECT * FROM t_item")
    List<Item> findAll();

    @Query("SELECT * FROM t_item LIMIT :limit OFFSET :offset")
    List<Item> find(@Param("limit") int limit, @Param("offset") int offset);

}
