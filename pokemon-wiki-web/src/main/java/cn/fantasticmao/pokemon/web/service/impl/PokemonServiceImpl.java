package cn.fantasticmao.pokemon.web.service.impl;

import cn.fantasticmao.pokemon.web.bean.PokemonBean;
import cn.fantasticmao.pokemon.web.domain.*;
import cn.fantasticmao.pokemon.web.repoistory.*;
import cn.fantasticmao.pokemon.web.service.PokemonService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * PokemonServiceImpl
 *
 * @author fantasticmao
 * @since 2018/7/29
 */
@Service
public class PokemonServiceImpl implements PokemonService {
    @Resource
    private PokemonRepository pokemonRepository;
    @Resource
    private PokemonAbilityRepository pokemonAbilityRepository;
    @Resource
    private PokemonDetailBaseStatRepository pokemonDetailBaseStatRepository;
    @Resource
    private PokemonDetailRepository pokemonDetailRepository;
    @Resource
    private PokemonDetailLearnSetByLevelingUpRepository pokemonDetailLearnSetByLevelingUpRepository;
    @Resource
    private PokemonDetailLearnSetByTechnicalMachineRepository pokemonDetailLearnSetByTechnicalMachineRepository;
    @Resource
    private PokemonDetailLearnSetByBreedingRepository pokemonDetailLearnSetByBreedingRepository;

    @Override
    public List<PokemonBean> listByIndexOrNameZh(Integer index, String nameZh) {
        if ((index == null || index <= 0) && StringUtils.isEmpty(nameZh)) {
            return Collections.emptyList();
        }

        final List<Pokemon> pokemonList;
        if (index != null && index > 0) { // 按全国图鉴编号查找
            pokemonList = pokemonRepository.findByIndex(index);
        } else { // 按中文名称查找
            pokemonList = pokemonRepository.findByNameZh(nameZh);
        }
        if (CollectionUtils.isEmpty(pokemonList)) {
            return Collections.emptyList();
        }

        final List<Integer> pokemonIndexList = pokemonList.stream().map(Pokemon::getIdx).collect(Collectors.toList());

        Map<Integer, PokemonDetail> pokemonDetailMap = pokemonDetailRepository.findByIndexIn(pokemonIndexList).stream()
            .collect(Collectors.toMap(PokemonDetail::getIdx, Function.identity(), (ability1, ability2) -> ability1));

        Map<Integer, List<PokemonDetailLearnSetByLevelingUp>> pokemonDetailLearnSetByLevelingUpMap
            = pokemonDetailLearnSetByLevelingUpRepository.findByIndexIn(pokemonIndexList).stream()
            .collect(Collectors.groupingBy(PokemonDetailLearnSetByLevelingUp::getIdx));

        Map<Integer, List<PokemonDetailLearnSetByTechnicalMachine>> pokemonDetailLearnSetByTechnicalMachineMap
            = pokemonDetailLearnSetByTechnicalMachineRepository.findByIndexIn(pokemonIndexList).stream()
            .collect(Collectors.groupingBy(PokemonDetailLearnSetByTechnicalMachine::getIdx));

        Map<Integer, List<PokemonDetailLearnSetByBreeding>> pokemonDetailLearnSetByBreedingMap
            = pokemonDetailLearnSetByBreedingRepository.findByIndexIn(pokemonIndexList).stream()
            .collect(Collectors.groupingBy(PokemonDetailLearnSetByBreeding::getIdx));

        Map<Integer, PokemonAbility> pokemonAbilityMap = pokemonAbilityRepository.findByIndexIn(pokemonIndexList).stream()
            .collect(Collectors.toMap(PokemonAbility::getIdx, Function.identity(), (ability1, ability2) -> ability1));

        Map<Integer, PokemonDetailBaseStat> pokemonDetailBaseStatMap = pokemonDetailBaseStatRepository.findByIndexIn(pokemonIndexList).stream()
            .collect(Collectors.toMap(PokemonDetailBaseStat::getIdx, Function.identity(), (baseStat1, baseStat2) -> baseStat1));

        return pokemonList.stream()
            .map(pokemon -> {
                PokemonAbility pokemonAbility = pokemonAbilityMap.get(pokemon.getIdx());
                PokemonDetailBaseStat pokemonBaseStat = pokemonDetailBaseStatMap.get(pokemon.getIdx());
                PokemonDetail pokemonDetail = pokemonDetailMap.get(pokemon.getIdx());
                List<PokemonDetailLearnSetByLevelingUp> pokemonDetailLearnSetByLevelingUpList
                    = pokemonDetailLearnSetByLevelingUpMap.getOrDefault(pokemon.getIdx(), Collections.emptyList());
                List<PokemonDetailLearnSetByTechnicalMachine> pokemonDetailLearnSetByTechnicalMachineList
                    = pokemonDetailLearnSetByTechnicalMachineMap.getOrDefault(pokemon.getIdx(), Collections.emptyList());
                List<PokemonDetailLearnSetByBreeding> pokemonDetailLearnSetByBreedingList
                    = pokemonDetailLearnSetByBreedingMap.getOrDefault(pokemon.getIdx(), Collections.emptyList());
                return new PokemonBean(pokemon, pokemonAbility, pokemonBaseStat, pokemonDetail,
                    pokemonDetailLearnSetByLevelingUpList, pokemonDetailLearnSetByTechnicalMachineList,
                    pokemonDetailLearnSetByBreedingList);
            })
            .sorted()
            .collect(Collectors.toList());
    }

    @Override
    public List<PokemonBean> listByGenerationAndEggGroup(int generation, @Nullable String eggGroup,
                                                         int page, int size) {
        // TODO pageable
        List<Pokemon> pokemonList = generation == 0
            ? pokemonRepository.findAll()
            : pokemonRepository.findByGeneration(generation);
        if (CollectionUtils.isEmpty(pokemonList)) {
            return Collections.emptyList();
        }

        final List<Integer> pokemonIndexList = pokemonList.stream()
            .map(Pokemon::getIdx)
            .collect(Collectors.toList());

        Map<Integer, PokemonDetail> pokemonDetailMap = pokemonDetailRepository.findByIndexIn(pokemonIndexList).stream()
            .collect(Collectors.toMap(PokemonDetail::getIdx, Function.identity(), (ability1, ability2) -> ability1));

        Map<Integer, PokemonAbility> pokemonAbilityMap = pokemonAbilityRepository.findByIndexIn(pokemonIndexList).stream()
            .collect(Collectors.toMap(PokemonAbility::getIdx, Function.identity(), (ability1, ability2) -> ability1));

        return pokemonList.stream()
            .filter(pokemon -> {
                if (StringUtils.isNotEmpty(eggGroup)) {
                    PokemonDetail pokemonDetail = pokemonDetailMap.get(pokemon.getIdx());
                    return pokemonDetail != null
                        && (Objects.equals(eggGroup, pokemonDetail.getEggGroup1()) || Objects.equals(eggGroup, pokemonDetail.getEggGroup2()));
                } else {
                    return true;
                }
            })
            .map(pokemon -> {
                PokemonAbility pokemonAbility = pokemonAbilityMap.get(pokemon.getIdx());
                return new PokemonBean(pokemon, pokemonAbility);
            })
            .sorted()
            .collect(Collectors.toList());
    }
}
