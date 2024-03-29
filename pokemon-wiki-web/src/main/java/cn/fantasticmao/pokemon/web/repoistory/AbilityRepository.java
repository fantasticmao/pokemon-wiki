package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.Ability;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * AbilityRepository
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
public interface AbilityRepository extends PagingAndSortingRepository<Ability, Integer>,
    AbilityDynamicRepository {

    @Query("SELECT * FROM t_ability LIMIT :limit OFFSET :offset")
    List<Ability> find(@Param("limit") int limit, @Param("offset") int offset);
}
