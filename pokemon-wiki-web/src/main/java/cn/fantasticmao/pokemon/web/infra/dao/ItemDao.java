package cn.fantasticmao.pokemon.web.infra.dao;

import cn.fantasticmao.pokemon.web.infra.model.ItemPo;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * ItemDao
 *
 * @author fantasticmao
 * @since 2019-03-23
 */
public interface ItemDao extends PagingAndSortingRepository<ItemPo, Integer>,
    ItemDynamicDao {

    @Query("SELECT * FROM t_item LIMIT :limit OFFSET :offset")
    List<ItemPo> find(@Param("limit") int limit, @Param("offset") int offset);

}
