package cn.fantasticmao.pokemon.web.domain.ability.repoistory;

import cn.fantasticmao.pokemon.web.domain.ability.model.Ability;

import javax.annotation.Nullable;
import java.util.List;

/**
 * AbilityRepository
 *
 * @author maodaohe
 * @since 2025-11-06
 */
public interface AbilityRepository {

    List<Ability> listByName(@Nullable String nameZh, @Nullable String nameEn);

    List<Ability> list(int page, int size);

}
