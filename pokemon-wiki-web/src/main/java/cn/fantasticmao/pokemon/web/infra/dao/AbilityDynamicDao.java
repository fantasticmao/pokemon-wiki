package cn.fantasticmao.pokemon.web.infra.dao;

import cn.fantasticmao.pokemon.web.infra.model.AbilityPo;

import javax.annotation.Nullable;
import java.util.List;

/**
 * AbilityDynamicDao
 *
 * @author fantasticmao
 * @since 2023-02-19
 */
public interface AbilityDynamicDao {

    List<AbilityPo> findByName(@Nullable String nameZh, @Nullable String nameEn);
}
