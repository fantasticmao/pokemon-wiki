package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * ItemRepository
 *
 * @author fantasticmao
 * @since 2019-03-23
 */
public interface ItemRepository extends PagingAndSortingRepository<Item, Integer> {

    @Query(value = "SELECT * FROM pw_item", nativeQuery = true)
    List<Item> findAll();

    @Query(value = "SELECT * FROM pw_item WHERE nameZh LIKE '%' || ?1 || '%'", nativeQuery = true)
    List<Item> findByNameZh(String nameZh);

    @Query(value = "SELECT * FROM pw_item LIMIT ?2 OFFSET ?1", nativeQuery = true)
    List<Item> find(int offset, int limit);
}
