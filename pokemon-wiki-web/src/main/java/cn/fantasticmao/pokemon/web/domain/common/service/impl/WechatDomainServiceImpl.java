package cn.fantasticmao.pokemon.web.domain.common.service.impl;

import cn.fantasticmao.pokemon.web.domain.common.service.WechatDomainService;
import cn.fantasticmao.pokemon.web.domain.pokemon.model.Pokemon;
import cn.fantasticmao.pokemon.web.domain.pokemon.repoistory.PokemonRepository;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.List;
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
    private PokemonRepository pokemonRepository;

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

        final List<Pokemon> pokemonList = pokemonRepository.findByNameAndForm(nameZh, null, null);
        if (CollectionUtils.isEmpty(pokemonList)) {
            return "未找到相关宝可梦";
        }

        final String replyContent = pokemonList.stream()
            .map(pokemon -> {
                String typeJoin = StringUtils.isNotEmpty(pokemon.getAbility().getType2())
                    ? pokemon.getAbility().getType1() + "、" + pokemon.getAbility().getType2()
                    : pokemon.getAbility().getType1();
                String abilityJoin = StringUtils.isNotEmpty(pokemon.getAbility().getAbility2())
                    ? pokemon.getAbility().getAbility1() + "、" + pokemon.getAbility().getAbility2()
                    : pokemon.getAbility().getAbility1();
                String abilityHide = pokemon.getAbility().getAbilityHide();
                return String.format("No.%d %s，第 %d 世代宝可梦，英文名称：%s，日文名称：%s，属性：%s，特性：%s，隐藏特性：%s。",
                    pokemon.getIndex(), pokemon.getNameZh(), pokemon.getGeneration(), pokemon.getNameEn(), pokemon.getNameJa(),
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
