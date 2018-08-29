package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.wiki.domain.Ability;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * AbilityRepository
 *
 * @author maodh
 * @since 2018/8/29
 */
public interface AbilityRepository extends PagingAndSortingRepository<Ability, Integer> {

    @Query(value = "SELECT * FROM pw_ability WHERE nameZh LIKE CONCAT('%', ?1, '%')", nativeQuery = true)
    List<Ability> findByNameZh(String nameZh);
}
