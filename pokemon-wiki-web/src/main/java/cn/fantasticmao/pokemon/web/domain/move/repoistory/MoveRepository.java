package cn.fantasticmao.pokemon.web.domain.move.repoistory;

import cn.fantasticmao.pokemon.web.domain.move.model.Move;

import javax.annotation.Nullable;
import java.util.List;

/**
 * MoveRepository
 *
 * @author maodaohe
 * @since 2025-11-06
 */
public interface MoveRepository {

    List<Move> listByName(@Nullable String nameZh, @Nullable String nameEn);

    List<Move> list(int page, int size);

}
