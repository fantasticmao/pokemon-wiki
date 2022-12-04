package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.mundo.data.jdbc.NativeQuery;
import cn.fantasticmao.pokemon.web.domain.Ability;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * AbilityRepository
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
public interface AbilityRepository extends PagingAndSortingRepository<Ability, Integer> {

    @NativeQuery("SELECT * FROM pw_ability WHERE name_zh LIKE '%' || ?1 || '%'")
    List<Ability> findByNameZh(String nameZh);

    @NativeQuery("SELECT * FROM pw_ability LIMIT ?2 OFFSET ?1")
    List<Ability> find(int offset, int limit);
}
