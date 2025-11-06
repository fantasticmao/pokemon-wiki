package cn.fantasticmao.pokemon.web.domain.common.service.impl;

import cn.fantasticmao.pokemon.web.domain.common.service.WechatDomainService;
import cn.fantasticmao.pokemon.web.infra.dao.PokemonAbilityDao;
import cn.fantasticmao.pokemon.web.infra.dao.PokemonDao;
import cn.fantasticmao.pokemon.web.infra.model.PokemonAbilityPo;
import cn.fantasticmao.pokemon.web.infra.model.PokemonPo;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * WechatDomainServiceImpl
 *
 * @author fantasticmao
 * @since 2021/6/22
 */
@Service
public class WechatDomainServiceImpl implements WechatDomainService {
    @Resource
    private PokemonDao pokemonDao;
    @Resource
    private PokemonAbilityDao pokemonAbilityDao;

    private static final int MAX_CONTENT_LENGTH = 1500;

    @Override
    public String token() {
        return System.getProperty("app.wechat.token", "I_Love_Pokemon");
    }

    @Override
    public String searchPokemonInfosByName(@Nullable String nameZh) {
        if (StringUtils.isEmpty(nameZh)) {
            return "未找到相关宝可梦";
        }

        final List<PokemonPo> pokemonPoList = pokemonDao.findByNameAndForm(nameZh, null, null);
        if (CollectionUtils.isEmpty(pokemonPoList)) {
            return "未找到相关宝可梦";
        }

        final List<Integer> pokemonIndexList = pokemonPoList.stream().map(PokemonPo::getIdx).collect(Collectors.toList());
        Map<Integer, PokemonAbilityPo> pokemonAbilityMap = pokemonAbilityDao.findByIndexIn(pokemonIndexList).stream()
            .collect(Collectors.toMap(PokemonAbilityPo::getIdx, Function.identity(), (ability1, ability2) -> ability1));
        final String replyContent = pokemonPoList.stream()
            .map(pokemon -> {
                final PokemonAbilityPo pokemonAbilityPo = pokemonAbilityMap.get(pokemon.getIdx());
                final String typeJoin, abilityJoin, abilityHide;
                if (Objects.isNull(pokemonAbilityPo)) {
                    typeJoin = "未知";
                    abilityJoin = "未知";
                    abilityHide = "未知";
                } else {
                    typeJoin = StringUtils.isNotEmpty(pokemonAbilityPo.getType2())
                        ? pokemonAbilityPo.getType1() + "、" + pokemonAbilityPo.getType2()
                        : pokemonAbilityPo.getType1();
                    abilityJoin = StringUtils.isNotEmpty(pokemonAbilityPo.getAbility2())
                        ? pokemonAbilityPo.getAbility1() + "、" + pokemonAbilityPo.getAbility2()
                        : pokemonAbilityPo.getAbility1();
                    abilityHide = pokemonAbilityPo.getAbilityHide();
                }
                return String.format("No.%d %s，第 %d 世代宝可梦，英文名称：%s，日文名称：%s，属性：%s，特性：%s，隐藏特性：%s。",
                    pokemon.getIdx(), pokemon.getNameZh(), pokemon.getGeneration(), pokemon.getNameEn(), pokemon.getNameJa(),
                    typeJoin, abilityJoin, abilityHide);
            })
            .collect(Collectors.joining(System.lineSeparator()));
        return this.briefContent(replyContent);
    }

    private String briefContent(String replyContent) {
        if (replyContent.length() > MAX_CONTENT_LENGTH) {
            return replyContent.substring(0, MAX_CONTENT_LENGTH) + "......";
        }
        return replyContent;
    }
}
