package cn.fantasticmao.pokemon.web.infra.dao;

import cn.fantasticmao.pokemon.web.infra.model.AbilityPo;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * AbilityDao
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
public interface AbilityDao extends PagingAndSortingRepository<AbilityPo, Integer>,
    AbilityDynamicDao {

    @Query("SELECT * FROM t_ability LIMIT :limit OFFSET :offset")
    List<AbilityPo> find(@Param("limit") int limit, @Param("offset") int offset);
}
