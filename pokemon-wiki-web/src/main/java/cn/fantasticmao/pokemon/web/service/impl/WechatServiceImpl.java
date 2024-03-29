package cn.fantasticmao.pokemon.web.service.impl;

import cn.fantasticmao.pokemon.web.domain.Pokemon;
import cn.fantasticmao.pokemon.web.domain.PokemonAbility;
import cn.fantasticmao.pokemon.web.repoistory.PokemonAbilityRepository;
import cn.fantasticmao.pokemon.web.repoistory.PokemonRepository;
import cn.fantasticmao.pokemon.web.service.WechatService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * WechatServiceImpl
 *
 * @author fantasticmao
 * @since 2021/6/22
 */
@Service
public class WechatServiceImpl implements WechatService {
    @Resource
    private PokemonRepository pokemonRepository;
    @Resource
    private PokemonAbilityRepository pokemonAbilityRepository;

    private static final int MAX_CONTENT_LENGTH = 1500;

    @Nonnull
    @Override
    public String token() {
        return System.getProperty("app.wechat.token", "I_Love_Pokemon");
    }

    @Override
    public String searchPokemonInfosByName(@Nullable String nameZh) {
        if (StringUtils.isEmpty(nameZh)) {
            return "未找到相关宝可梦";
        }

        final List<Pokemon> pokemonList = pokemonRepository.findByNameAndForm(nameZh, null, null);
        if (CollectionUtils.isEmpty(pokemonList)) {
            return "未找到相关宝可梦";
        }

        final List<Integer> pokemonIndexList = pokemonList.stream().map(Pokemon::getIdx).collect(Collectors.toList());
        Map<Integer, PokemonAbility> pokemonAbilityMap = pokemonAbilityRepository.findByIndexIn(pokemonIndexList).stream()
            .collect(Collectors.toMap(PokemonAbility::getIdx, Function.identity(), (ability1, ability2) -> ability1));
        final String replyContent = pokemonList.stream()
            .map(pokemon -> {
                final PokemonAbility pokemonAbility = pokemonAbilityMap.get(pokemon.getIdx());
                final String typeJoin, abilityJoin, abilityHide;
                if (Objects.isNull(pokemonAbility)) {
                    typeJoin = "未知";
                    abilityJoin = "未知";
                    abilityHide = "未知";
                } else {
                    typeJoin = StringUtils.isNotEmpty(pokemonAbility.getType2())
                        ? pokemonAbility.getType1() + "、" + pokemonAbility.getType2()
                        : pokemonAbility.getType1();
                    abilityJoin = StringUtils.isNotEmpty(pokemonAbility.getAbility2())
                        ? pokemonAbility.getAbility1() + "、" + pokemonAbility.getAbility2()
                        : pokemonAbility.getAbility1();
                    abilityHide = pokemonAbility.getAbilityHide();
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
