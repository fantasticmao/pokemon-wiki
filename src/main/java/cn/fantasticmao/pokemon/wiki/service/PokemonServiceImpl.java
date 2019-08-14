package cn.fantasticmao.pokemon.wiki.service;

import cn.fantasticmao.pokemon.wiki.bean.PokemonBean;
import cn.fantasticmao.pokemon.wiki.domain.Pokemon;
import cn.fantasticmao.pokemon.wiki.domain.PokemonAbility;
import cn.fantasticmao.pokemon.wiki.domain.PokemonBaseStat;
import cn.fantasticmao.pokemon.wiki.domain.PokemonDetail;
import cn.fantasticmao.pokemon.wiki.repoistory.PokemonAbilityRepository;
import cn.fantasticmao.pokemon.wiki.repoistory.PokemonBaseStatRepository;
import cn.fantasticmao.pokemon.wiki.repoistory.PokemonDetailRepository;
import cn.fantasticmao.pokemon.wiki.repoistory.PokemonRepository;
import com.mundo.core.util.CollectionUtil;
import com.mundo.core.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * PokemonServiceImpl
 *
 * @author maodh
 * @since 2018/7/29
 */
@Service
public class PokemonServiceImpl implements PokemonService {
    @Resource
    private PokemonRepository pokemonRepository;
    @Resource
    private PokemonAbilityRepository pokemonAbilityRepository;
    @Resource
    private PokemonBaseStatRepository pokemonBaseStatRepository;
    @Resource
    private PokemonDetailRepository pokemonDetailRepository;

    @Override
    public PokemonBean listByIndex(Integer index) {
        if (index <= 0) {
            return null;
        }
        Optional<Pokemon> pokemonOptional = pokemonRepository.findByIndex(index);
        return null;
    }

    @Override
    public List<PokemonBean> listByNameZh(String nameZh) {
        if (StringUtil.isEmpty(nameZh)) {
            return Collections.emptyList();
        }

        List<Pokemon> pokemonList = pokemonRepository.findByNameZh(nameZh);
        if (CollectionUtil.isEmpty(pokemonList)) return Collections.emptyList();

        Map<String, PokemonAbility> pokemonAbilityMap = pokemonAbilityRepository.findByNameZh(nameZh).stream()
                .collect(Collectors.toMap(PokemonAbility::getNameZh, Function.identity(), (ability1, ability2) -> ability1));

        Map<String, PokemonBaseStat> pokemonBaseStatMap = pokemonBaseStatRepository.findByNameZh(nameZh).stream()
                .collect(Collectors.toMap(PokemonBaseStat::getNameZh, Function.identity(), (baseStat1, baseStat2) -> baseStat1));

        Map<String, PokemonDetail> pokemonDetailMap = pokemonDetailRepository.findByNameZh(nameZh).stream()
                .collect(Collectors.toMap(PokemonDetail::getNameZh, Function.identity(), (detail1, detail2) -> detail1));

        return pokemonList.stream()
                .map(pokemon -> {
                    PokemonAbility pokemonAbility = pokemonAbilityMap.get(pokemon.getNameZh());
                    PokemonBaseStat pokemonBaseStat = pokemonBaseStatMap.get(pokemon.getNameZh());
                    PokemonDetail pokemonDetail = pokemonDetailMap.get(pokemon.getNameZh());
                    return new PokemonBean(pokemon, pokemonAbility, pokemonDetail, pokemonBaseStat);
                })
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<PokemonBean> listByGenerationAndEggGroup(int generation, String eggGroup) {
        List<Pokemon> pokemonList = generation == 0 ? pokemonRepository.findAll() : pokemonRepository.findByGeneration(generation);
        if (CollectionUtil.isEmpty(pokemonList)) return Collections.emptyList();

        List<Integer> pokemonIdList = pokemonList.stream().map(Pokemon::getId).collect(Collectors.toList());
        List<Integer> pokemonIndexList = pokemonList.stream().map(Pokemon::getIndex).collect(Collectors.toList());

        Map<Integer, PokemonDetail> pokemonDetailMap = pokemonDetailRepository.findByIdIn(pokemonIdList).stream()
                .collect(Collectors.toMap(PokemonDetail::getId, Function.identity(), (ability1, ability2) -> ability1));

        Map<Integer, PokemonAbility> pokemonAbilityMap = pokemonAbilityRepository.findByIndexIn(pokemonIndexList).stream()
                .collect(Collectors.toMap(PokemonAbility::getIndex, Function.identity(), (ability1, ability2) -> ability1));

        return pokemonList.stream()
                .filter(pokemon -> {
                    if (StringUtil.isNotEmpty(eggGroup)) {
                        PokemonDetail pokemonDetail = pokemonDetailMap.get(pokemon.getId());
                        return pokemonDetail != null
                                && (eggGroup.equals(pokemonDetail.getEggGroup1()) || eggGroup.equals(pokemonDetail.getEggGroup2()));
                    } else {
                        return true;
                    }
                })
                .map(pokemon -> {
                    PokemonAbility pokemonAbility = pokemonAbilityMap.get(pokemon.getIndex());
                    return new PokemonBean(pokemon, pokemonAbility);
                })
                .sorted()
                .collect(Collectors.toList());
    }
}
