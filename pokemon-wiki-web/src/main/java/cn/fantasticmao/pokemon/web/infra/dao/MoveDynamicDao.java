package cn.fantasticmao.pokemon.web.infra.dao;

import cn.fantasticmao.pokemon.web.infra.model.MovePo;

import javax.annotation.Nullable;
import java.util.List;

/**
 * MoveDynamicDao
 *
 * @author fantasticmao
 * @since 2023-02-19
 */
public interface MoveDynamicDao {

    List<MovePo> findByName(@Nullable String nameZh, @Nullable String nameEn);
}
