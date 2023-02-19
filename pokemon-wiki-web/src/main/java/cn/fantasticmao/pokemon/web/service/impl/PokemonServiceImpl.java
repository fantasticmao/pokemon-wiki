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
import java.util.Set;
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
    public List<PokemonBean> listByIndexOrName(@Nullable Integer index, @Nullable String nameZh,
                                               @Nullable String nameEn, @Nullable String form) {
        if ((index == null || index <= 0) && StringUtils.isAllEmpty(nameZh, nameEn)) {
            return Collections.emptyList();
        }

        final List<Pokemon> pokemonList = (index != null && index > 0)
            ? pokemonRepository.findByIndexAndForm(index, form) // 按全国图鉴编号查找
            : pokemonRepository.findByNameAndForm(nameZh, nameEn, form); // 按名称查找

        if (CollectionUtils.isEmpty(pokemonList)) {
            return Collections.emptyList();
        }

        final Set<Integer> pokemonIndexSet = pokemonList.stream()
            .map(Pokemon::getIdx)
            .collect(Collectors.toSet());

        Map<String, PokemonAbility> pokemonAbilityMap
            = pokemonAbilityRepository.findByIndexIn(pokemonIndexSet).stream()
            .collect(Collectors.toMap(
                ability -> ability.getIdx() + ability.getForm(),
                Function.identity(),
                (ability1, ability2) -> ability1)
            );

        Map<Integer, PokemonDetail> pokemonDetailMap
            = pokemonDetailRepository.findByIndexIn(pokemonIndexSet).stream()
            .collect(Collectors.toMap(
                PokemonDetail::getIdx,
                Function.identity(),
                (ability1, ability2) -> ability1)
            );

        Map<Integer, PokemonDetailBaseStat> pokemonDetailBaseStatMap
            = pokemonDetailBaseStatRepository.findByIndexIn(pokemonIndexSet).stream()
            .collect(Collectors.toMap(
                PokemonDetailBaseStat::getIdx,
                Function.identity(),
                (baseStat1, baseStat2) -> baseStat1)
            );

        Map<Integer, List<PokemonDetailLearnSetByLevelingUp>> pokemonDetailLearnSetByLevelingUpMap
            = pokemonDetailLearnSetByLevelingUpRepository.findByIndexIn(pokemonIndexSet).stream()
            .collect(Collectors.groupingBy(PokemonDetailLearnSetByLevelingUp::getIdx));

        Map<Integer, List<PokemonDetailLearnSetByTechnicalMachine>> pokemonDetailLearnSetByTechnicalMachineMap
            = pokemonDetailLearnSetByTechnicalMachineRepository.findByIndexIn(pokemonIndexSet).stream()
            .collect(Collectors.groupingBy(PokemonDetailLearnSetByTechnicalMachine::getIdx));

        Map<Integer, List<PokemonDetailLearnSetByBreeding>> pokemonDetailLearnSetByBreedingMap
            = pokemonDetailLearnSetByBreedingRepository.findByIndexIn(pokemonIndexSet).stream()
            .collect(Collectors.groupingBy(PokemonDetailLearnSetByBreeding::getIdx));

        return pokemonList.stream()
            .filter(pokemon ->
                pokemonAbilityMap.containsKey(pokemon.getIdx() + pokemon.getForm())
                    && pokemonDetailMap.containsKey(pokemon.getIdx())
                    && pokemonDetailBaseStatMap.containsKey(pokemon.getIdx())
            )
            .map(pokemon -> {
                PokemonAbility pokemonAbility = pokemonAbilityMap.get(pokemon.getIdx() + pokemon.getForm());
                PokemonDetail pokemonDetail = pokemonDetailMap.get(pokemon.getIdx());
                PokemonDetailBaseStat pokemonBaseStat = pokemonDetailBaseStatMap.get(pokemon.getIdx());
                List<PokemonDetailLearnSetByLevelingUp> pokemonDetailLearnSetByLevelingUpList
                    = pokemonDetailLearnSetByLevelingUpMap.getOrDefault(pokemon.getIdx(), Collections.emptyList());
                List<PokemonDetailLearnSetByTechnicalMachine> pokemonDetailLearnSetByTechnicalMachineList
                    = pokemonDetailLearnSetByTechnicalMachineMap.getOrDefault(pokemon.getIdx(), Collections.emptyList());
                List<PokemonDetailLearnSetByBreeding> pokemonDetailLearnSetByBreedingList
                    = pokemonDetailLearnSetByBreedingMap.getOrDefault(pokemon.getIdx(), Collections.emptyList());
                return new PokemonBean(pokemon, pokemonAbility, pokemonDetail, pokemonBaseStat,
                    pokemonDetailLearnSetByLevelingUpList, pokemonDetailLearnSetByTechnicalMachineList,
                    pokemonDetailLearnSetByBreedingList);
            })
            .sorted()
            .collect(Collectors.toList());
    }

    @Override
    public List<PokemonBean> listByGenerationAndEggGroup(@Nullable Integer generation, @Nullable String eggGroup,
                                                         @Nullable Integer page, int size) {
        List<Pokemon> pokemonList = pokemonRepository.findByGenerationAndEggGroup(generation, eggGroup, page, size);
        if (CollectionUtils.isEmpty(pokemonList)) {
            return Collections.emptyList();
        }

        final Set<Integer> pokemonIndexSet = pokemonList.stream()
            .map(Pokemon::getIdx)
            .collect(Collectors.toSet());

        Map<String, PokemonAbility> pokemonAbilityMap = pokemonAbilityRepository.findByIndexIn(pokemonIndexSet).stream()
            .collect(Collectors.toMap(
                ability -> ability.getIdx() + ability.getForm(),
                Function.identity(),
                (ability1, ability2) -> ability1)
            );

        return pokemonList.stream()
            .filter(pokemon ->
                pokemonAbilityMap.containsKey(pokemon.getIdx() + pokemon.getForm())
            )
            .map(pokemon -> {
                PokemonAbility pokemonAbility = pokemonAbilityMap.get(pokemon.getIdx() + pokemon.getForm());
                return new PokemonBean(pokemon, pokemonAbility);
            })
            .sorted()
            .collect(Collectors.toList());
    }
}
