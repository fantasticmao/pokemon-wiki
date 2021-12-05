package cn.fantasticmao.pokemon.web.service.impl;

import cn.fantasticmao.pokemon.web.bean.PokemonBean;
import cn.fantasticmao.pokemon.web.domain.*;
import cn.fantasticmao.pokemon.web.repoistory.*;
import cn.fantasticmao.pokemon.web.service.PokemonService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
            Optional<Pokemon> pokemonOptional = pokemonRepository.findByIndex(index);
            pokemonList = pokemonOptional.map(Collections::singletonList).orElse(Collections.emptyList());
        } else { // 按中文名称查找
            pokemonList = pokemonRepository.findByNameZh(nameZh);
        }
        if (CollectionUtils.isEmpty(pokemonList)) return Collections.emptyList();

        final List<Integer> pokemonIndexList = pokemonList.stream().map(Pokemon::getIndex).collect(Collectors.toList());

        Map<Integer, PokemonDetail> pokemonDetailMap = pokemonDetailRepository.findByIndexIn(pokemonIndexList).stream()
            .collect(Collectors.toMap(PokemonDetail::getIndex, Function.identity(), (ability1, ability2) -> ability1));

        Map<Integer, List<PokemonDetailLearnSetByLevelingUp>> pokemonDetailLearnSetByLevelingUpMap
            = pokemonDetailLearnSetByLevelingUpRepository.findByIndexIn(pokemonIndexList).stream()
            .collect(Collectors.groupingBy(PokemonDetailLearnSetByLevelingUp::getIndex));

        Map<Integer, List<PokemonDetailLearnSetByTechnicalMachine>> pokemonDetailLearnSetByTechnicalMachineMap
            = pokemonDetailLearnSetByTechnicalMachineRepository.findByIndexIn(pokemonIndexList).stream()
            .collect(Collectors.groupingBy(PokemonDetailLearnSetByTechnicalMachine::getIndex));

        Map<Integer, List<PokemonDetailLearnSetByBreeding>> pokemonDetailLearnSetByBreedingMap
            = pokemonDetailLearnSetByBreedingRepository.findByIndexIn(pokemonIndexList).stream()
            .collect(Collectors.groupingBy(PokemonDetailLearnSetByBreeding::getIndex));

        Map<Integer, PokemonAbility> pokemonAbilityMap = pokemonAbilityRepository.findByIndexIn(pokemonIndexList).stream()
            .collect(Collectors.toMap(PokemonAbility::getIndex, Function.identity(), (ability1, ability2) -> ability1));

        Map<Integer, PokemonDetailBaseStat> pokemonDetailBaseStatMap = pokemonDetailBaseStatRepository.findByIndexIn(pokemonIndexList).stream()
            .collect(Collectors.toMap(PokemonDetailBaseStat::getIndex, Function.identity(), (baseStat1, baseStat2) -> baseStat1));

        return pokemonList.stream()
            .map(pokemon -> {
                PokemonDetail pokemonDetail = pokemonDetailMap.get(pokemon.getIndex());
                List<PokemonDetailLearnSetByLevelingUp> pokemonDetailLearnSetByLevelingUpList
                    = pokemonDetailLearnSetByLevelingUpMap.get(pokemon.getIndex());
                List<PokemonDetailLearnSetByTechnicalMachine> pokemonDetailLearnSetByTechnicalMachineList
                    = pokemonDetailLearnSetByTechnicalMachineMap.get(pokemon.getIndex());
                List<PokemonDetailLearnSetByBreeding> pokemonDetailLearnSetByBreedingList
                    = pokemonDetailLearnSetByBreedingMap.get(pokemon.getIndex());
                PokemonAbility pokemonAbility = pokemonAbilityMap.get(pokemon.getIndex());
                PokemonDetailBaseStat pokemonBaseStat = pokemonDetailBaseStatMap.get(pokemon.getIndex());
                return new PokemonBean(pokemon, pokemonAbility, pokemonBaseStat, pokemonDetail,
                    pokemonDetailLearnSetByLevelingUpList, pokemonDetailLearnSetByTechnicalMachineList,
                    pokemonDetailLearnSetByBreedingList);
            })
            .sorted()
            .collect(Collectors.toList());
    }

    @Override
    public List<PokemonBean> listByGenerationAndEggGroup(int generation, String eggGroup) {
        List<Pokemon> pokemonList = generation == 0 ? pokemonRepository.findAll() : pokemonRepository.findByGeneration(generation);
        if (CollectionUtils.isEmpty(pokemonList)) return Collections.emptyList();

        final List<Integer> pokemonIndexList = pokemonList.stream().map(Pokemon::getIndex).collect(Collectors.toList());

        Map<Integer, PokemonDetail> pokemonDetailMap = pokemonDetailRepository.findByIndexIn(pokemonIndexList).stream()
            .collect(Collectors.toMap(PokemonDetail::getIndex, Function.identity(), (ability1, ability2) -> ability1));

        Map<Integer, PokemonAbility> pokemonAbilityMap = pokemonAbilityRepository.findByIndexIn(pokemonIndexList).stream()
            .collect(Collectors.toMap(PokemonAbility::getIndex, Function.identity(), (ability1, ability2) -> ability1));

        return pokemonList.stream()
            .filter(pokemon -> {
                if (StringUtils.isNotEmpty(eggGroup)) {
                    PokemonDetail pokemonDetail = pokemonDetailMap.get(pokemon.getIndex());
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
