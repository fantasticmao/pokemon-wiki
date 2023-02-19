package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.Move;

import javax.annotation.Nullable;
import java.util.List;

/**
 * MoveDynamicRepository
 *
 * @author fantasticmao
 * @since 2023-02-19
 */
public interface MoveDynamicRepository {

    List<Move> findByName(@Nullable String nameZh, @Nullable String nameEn);
}
