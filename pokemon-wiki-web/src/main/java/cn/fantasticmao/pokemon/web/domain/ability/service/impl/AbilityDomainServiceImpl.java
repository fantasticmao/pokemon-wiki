package cn.fantasticmao.pokemon.web.domain.ability.service.impl;

import cn.fantasticmao.pokemon.web.domain.ability.model.Ability;
import cn.fantasticmao.pokemon.web.domain.ability.repoistory.AbilityRepository;
import cn.fantasticmao.pokemon.web.domain.ability.service.AbilityDomainService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

/**
 * AbilityDomainServiceImpl
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
@Service
public class AbilityDomainServiceImpl implements AbilityDomainService {
    @Resource
    private AbilityRepository abilityRepository;

    @Override
    public List<Ability> listByName(@Nullable String nameZh, @Nullable String nameEn) {
        if (StringUtils.isAllEmpty(nameZh, nameEn)) {
            return Collections.emptyList();
        }
        return abilityRepository.listByName(nameZh, nameEn);
    }

    @Override
    public List<Ability> list(int page, int size) {
        if (page < 0 || size < 1) {
            return Collections.emptyList();
        }
        return abilityRepository.list(page, size);
    }
}
