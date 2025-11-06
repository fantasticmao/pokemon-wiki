package cn.fantasticmao.pokemon.web.domain.ability.service;

import cn.fantasticmao.pokemon.web.domain.ability.model.Ability;

import javax.annotation.Nullable;
import java.util.List;

/**
 * AbilityDomainService
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
public interface AbilityDomainService {

    List<Ability> listByName(@Nullable String nameZh, @Nullable String nameEn);

    List<Ability> list(int page, int size);
}
