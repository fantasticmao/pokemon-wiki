package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.Ability;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * AbilityRepository
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
public interface AbilityRepository extends PagingAndSortingRepository<Ability, Integer> {

    @Query(value = "SELECT * FROM pw_ability WHERE nameZh LIKE '%' || ?1 || '%'", nativeQuery = true)
    List<Ability> findByNameZh(String nameZh);

    @Query(value = "SELECT * FROM pw_ability LIMIT ?2 OFFSET ?1", nativeQuery = true)
    List<Ability> find(int offset, int limit);
}
