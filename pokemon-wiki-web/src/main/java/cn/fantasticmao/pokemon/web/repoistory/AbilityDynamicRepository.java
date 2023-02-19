package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.domain.Ability;

import javax.annotation.Nullable;
import java.util.List;

/**
 * AbilityDynamicRepository
 *
 * @author fantasticmao
 * @since 2023-02-19
 */
public interface AbilityDynamicRepository {

    List<Ability> findByName(@Nullable String nameZh, @Nullable String nameEn);
}
