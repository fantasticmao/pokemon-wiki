package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.mundo.data.jdbc.NativeQuery;
import cn.fantasticmao.pokemon.web.domain.Item;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * ItemRepository
 *
 * @author fantasticmao
 * @since 2019-03-23
 */
public interface ItemRepository extends PagingAndSortingRepository<Item, Integer> {

    @Nonnull
    @NativeQuery("SELECT * FROM pw_item")
    List<Item> findAll();

    @NativeQuery("SELECT * FROM pw_item WHERE name_zh LIKE '%' || ?1 || '%'")
    List<Item> findByNameZh(String nameZh);

    @NativeQuery("SELECT * FROM pw_item LIMIT ?2 OFFSET ?1")
    List<Item> find(int offset, int limit);
}
