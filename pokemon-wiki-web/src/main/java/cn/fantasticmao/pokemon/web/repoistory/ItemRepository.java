package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * ItemRepository
 *
 * @author maomao
 * @since 2019-03-23
 */
public interface ItemRepository extends PagingAndSortingRepository<Item, Integer> {

    @Query(value = "SELECT * FROM pw_item", nativeQuery = true)
    List<Item> findAll();
}
