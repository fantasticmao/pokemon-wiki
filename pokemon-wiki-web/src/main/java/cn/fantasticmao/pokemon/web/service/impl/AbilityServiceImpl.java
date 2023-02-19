package cn.fantasticmao.pokemon.web.service.impl;

import cn.fantasticmao.mundo.core.util.PageUtil;
import cn.fantasticmao.pokemon.web.bean.AbilityBean;
import cn.fantasticmao.pokemon.web.domain.Ability;
import cn.fantasticmao.pokemon.web.domain.AbilityDetail;
import cn.fantasticmao.pokemon.web.repoistory.AbilityDetailRepository;
import cn.fantasticmao.pokemon.web.repoistory.AbilityRepository;
import cn.fantasticmao.pokemon.web.service.AbilityService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * AbilityServiceImpl
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
@Service
public class AbilityServiceImpl implements AbilityService {
    @Resource
    private AbilityRepository abilityRepository;
    @Resource
    private AbilityDetailRepository abilityDetailRepository;

    @Override
    public List<AbilityBean> listByName(@Nullable String nameZh, @Nullable String nameEn) {
        if (StringUtils.isAllEmpty(nameZh, nameEn)) {
            return Collections.emptyList();
        }

        List<Ability> abilityList = abilityRepository.findByName(nameZh, nameEn);
        if (CollectionUtils.isEmpty(abilityList)) {
            return Collections.emptyList();
        }

        Set<Integer> abilityIdSet = abilityList.stream()
            .map(Ability::getId)
            .collect(Collectors.toSet());
        Map<Integer, AbilityDetail> abilityDetailMap = abilityDetailRepository.findByIdIn(abilityIdSet).stream()
            .collect(Collectors.toMap(AbilityDetail::getId, Function.identity()));

        return abilityList.stream()
            .map(ability -> {
                AbilityDetail abilityDetail = abilityDetailMap.get(ability.getId());
                return AbilityBean.ofDomain(ability, abilityDetail);
            })
            .sorted()
            .collect(Collectors.toList());
    }

    @Override
    public List<AbilityBean> list(int page, int size) {
        if (page < 0 || size < 1) {
            return Collections.emptyList();
        }
        List<Ability> abilityList = abilityRepository.find(PageUtil.limit(size), PageUtil.offset(page, size));
        return abilityList.stream()
            .map(ability -> AbilityBean.ofDomain(ability, null))
            .collect(Collectors.toList());
    }
}
