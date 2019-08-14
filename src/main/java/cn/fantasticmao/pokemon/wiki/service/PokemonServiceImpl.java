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
    public List<PokemonBean> listByIndexOrNameZh(Integer index, String nameZh) {
        if ((index == null || index <= 0) && StringUtil.isEmpty(nameZh)) {
            return Collections.emptyList();
        }

        final List<Pokemon> pokemonList;
        if (index != null && index > 0) { // 按全国图鉴编号查找
            Optional<Pokemon> pokemonOptional = pokemonRepository.findByIndex(index);
            pokemonList = pokemonOptional.map(Collections::singletonList).orElse(Collections.emptyList());
        } else { // 按中文名称查找
            pokemonList = pokemonRepository.findByNameZh(nameZh);
        }
        if (CollectionUtil.isEmpty(pokemonList)) return Collections.emptyList();

        List<Integer> pokemonIdList = pokemonList.stream().map(Pokemon::getId).collect(Collectors.toList());
        List<Integer> pokemonIndexList = pokemonList.stream().map(Pokemon::getIndex).collect(Collectors.toList());

        Map<Integer, PokemonDetail> pokemonDetailMap = pokemonDetailRepository.findByIdIn(pokemonIdList).stream()
                .collect(Collectors.toMap(PokemonDetail::getId, Function.identity(), (ability1, ability2) -> ability1));

        Map<Integer, PokemonAbility> pokemonAbilityMap = pokemonAbilityRepository.findByIndexIn(pokemonIndexList).stream()
                .collect(Collectors.toMap(PokemonAbility::getIndex, Function.identity(), (ability1, ability2) -> ability1));

        Map<Integer, PokemonBaseStat> pokemonBaseStatMap = pokemonBaseStatRepository.findByIndexIn(pokemonIndexList).stream()
                .collect(Collectors.toMap(PokemonBaseStat::getIndex, Function.identity(), (baseStat1, baseStat2) -> baseStat1));

        return pokemonList.stream()
                .map(pokemon -> {
                    PokemonAbility pokemonAbility = pokemonAbilityMap.get(pokemon.getIndex());
                    PokemonBaseStat pokemonBaseStat = pokemonBaseStatMap.get(pokemon.getIndex());
                    PokemonDetail pokemonDetail = pokemonDetailMap.get(pokemon.getId());
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
