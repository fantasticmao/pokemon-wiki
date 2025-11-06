package cn.fantasticmao.pokemon.web.infra.repoistory.impl;

import cn.fantasticmao.mundo.core.util.PageUtil;
import cn.fantasticmao.pokemon.web.domain.ability.model.Ability;
import cn.fantasticmao.pokemon.web.domain.ability.model.AbilityDetail;
import cn.fantasticmao.pokemon.web.domain.ability.repoistory.AbilityRepository;
import cn.fantasticmao.pokemon.web.infra.converter.AbilityConverter;
import cn.fantasticmao.pokemon.web.infra.dao.AbilityDao;
import cn.fantasticmao.pokemon.web.infra.dao.AbilityDetailDao;
import cn.fantasticmao.pokemon.web.infra.model.AbilityDetailPo;
import cn.fantasticmao.pokemon.web.infra.model.AbilityPo;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * AbilityRepositoryImpl
 *
 * @author maodaohe
 * @since 2025-11-06
 */
@Repository
public class AbilityRepositoryImpl implements AbilityRepository {
    @Resource
    private AbilityDao dao;
    @Resource
    private AbilityDetailDao detailDao;
    @Resource
    private AbilityConverter converter;

    @Override
    public List<Ability> listByName(@Nullable String nameZh, @Nullable String nameEn) {
        if (StringUtils.isAllEmpty(nameZh, nameEn)) {
            return Collections.emptyList();
        }

        List<AbilityPo> abilityPoList = dao.findByName(nameZh, nameEn);
        if (CollectionUtils.isEmpty(abilityPoList)) {
            return Collections.emptyList();
        }

        Set<Integer> abilityIdSet = abilityPoList.stream()
            .map(AbilityPo::getId)
            .collect(Collectors.toSet());
        Map<Integer, AbilityDetailPo> detailPoMap = detailDao.findByIdIn(abilityIdSet).stream()
            .collect(Collectors.toMap(AbilityDetailPo::getId, Function.identity()));

        return abilityPoList.stream()
            .map(abilityPo -> {
                Ability ability = converter.toAbility(abilityPo);

                AbilityDetail detail = converter.toAbilityDetail(
                    detailPoMap.get(ability.getId())
                );
                ability.setDetail(detail);
                return ability;
            })
            .sorted()
            .collect(Collectors.toList());
    }

    @Override
    public List<Ability> list(int page, int size) {
        if (page < 0 || size < 1) {
            return Collections.emptyList();
        }
        List<AbilityPo> abilityPoList = dao.find(PageUtil.limit(size), PageUtil.offset(page, size));
        return abilityPoList.stream()
            .map(converter::toAbility)
            .collect(Collectors.toList());
    }

}
