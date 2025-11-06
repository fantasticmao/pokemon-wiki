package cn.fantasticmao.pokemon.web.infra.repoistory.impl;

import cn.fantasticmao.pokemon.web.domain.pokemon.model.Pokemon;
import cn.fantasticmao.pokemon.web.domain.pokemon.repoistory.PokemonRepository;
import cn.fantasticmao.pokemon.web.infra.converter.PokemonConverter;
import cn.fantasticmao.pokemon.web.infra.dao.PokemonAbilityDao;
import cn.fantasticmao.pokemon.web.infra.dao.PokemonDao;
import cn.fantasticmao.pokemon.web.infra.dao.PokemonDetailBaseStatDao;
import cn.fantasticmao.pokemon.web.infra.dao.PokemonDetailDao;
import cn.fantasticmao.pokemon.web.infra.dao.PokemonDetailLearnSetByBreedingDao;
import cn.fantasticmao.pokemon.web.infra.dao.PokemonDetailLearnSetByLevelingUpDao;
import cn.fantasticmao.pokemon.web.infra.dao.PokemonDetailLearnSetByTechnicalMachineDao;
import cn.fantasticmao.pokemon.web.infra.model.PokemonDetailBaseStatPo;
import cn.fantasticmao.pokemon.web.infra.model.PokemonDetailLearnSetByBreedingPo;
import cn.fantasticmao.pokemon.web.infra.model.PokemonDetailLearnSetByLevelingUpPo;
import cn.fantasticmao.pokemon.web.infra.model.PokemonDetailLearnSetByTechnicalMachinePo;
import cn.fantasticmao.pokemon.web.infra.model.PokemonDetailPo;
import cn.fantasticmao.pokemon.web.infra.model.PokemonPo;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * PokemonRepositoryImpl
 *
 * @author fantasticmao
 * @since 2025-11-05
 */
@Repository
public class PokemonRepositoryImpl implements PokemonRepository {
    @Resource
    private PokemonDao pokemonDao;
    @Resource
    private PokemonAbilityDao pokemonAbilityDao;
    @Resource
    private PokemonDetailDao pokemonDetailDao;
    @Resource
    private PokemonDetailBaseStatDao pokemonDetailBaseStatDao;
    @Resource
    private PokemonDetailLearnSetByLevelingUpDao pokemonDetailLearnSetByLevelingUpDao;
    @Resource
    private PokemonDetailLearnSetByTechnicalMachineDao pokemonDetailLearnSetByTechnicalMachineDao;
    @Resource
    private PokemonDetailLearnSetByBreedingDao pokemonDetailLearnSetByBreedingDao;
    @Resource
    private PokemonConverter pokemonConverter;

    @Override
    public List<Pokemon> findByIndexAndForm(Integer index, @Nullable String form) {
        List<PokemonPo> pokemonPoList = pokemonDao.findByIndexAndForm(index, form);
        if (CollectionUtils.isEmpty(pokemonPoList)) {
            return Collections.emptyList();
        }
        return this.buildPokemonList(pokemonPoList);
    }

    @Override
    public List<Pokemon> findByNameAndForm(@Nullable String nameZh, @Nullable String nameEn,
                                           @Nullable String form) {
        List<PokemonPo> pokemonPoList = pokemonDao.findByNameAndForm(nameZh, nameEn, form);
        if (CollectionUtils.isEmpty(pokemonPoList)) {
            return Collections.emptyList();
        }
        return this.buildPokemonList(pokemonPoList);
    }

    @Override
    public List<Pokemon> findByGenerationAndEggGroup(Integer generation, @Nullable String eggGroup,
                                                     @Nullable Integer page, int size) {
        List<PokemonPo> pokemonPoList = pokemonDao.findByGenerationAndEggGroup(generation, eggGroup, page, size);
        if (CollectionUtils.isEmpty(pokemonPoList)) {
            return Collections.emptyList();
        }
        return buildPokemonTinyList(pokemonPoList);
    }

    private List<Pokemon> buildPokemonTinyList(List<PokemonPo> pokemonPoList) {
        final Set<Integer> pokemonIndexSet = pokemonPoList.stream()
            .map(PokemonPo::getIdx)
            .collect(Collectors.toSet());

        var abilityPoMap = pokemonAbilityDao.findByIndexIn(pokemonIndexSet).stream()
            .collect(Collectors.toMap(
                ability -> ability.getIdx() + ability.getForm(),
                Function.identity(),
                (ability1, ability2) -> ability1)
            );

        return pokemonPoList.stream()
            .filter(pokemonPo -> abilityPoMap.containsKey(pokemonPo.getIdx() + pokemonPo.getForm()))
            .map(pokemonPo -> {
                Pokemon pokemon = pokemonConverter.toPokemon(pokemonPo);

                var ability = pokemonConverter.toPokemonAbility(
                    abilityPoMap.get(pokemonPo.getIdx() + pokemonPo.getForm())
                );
                pokemon.setAbility(ability);
                return pokemon;
            })
            .sorted()
            .toList();
    }

    private List<Pokemon> buildPokemonList(List<PokemonPo> pokemonPoList) {
        final Set<Integer> pokemonIndexSet = pokemonPoList.stream()
            .map(PokemonPo::getIdx)
            .collect(Collectors.toSet());

        var abilityPoMap = pokemonAbilityDao.findByIndexIn(pokemonIndexSet).stream()
            .collect(Collectors.toMap(
                ability -> ability.getIdx() + ability.getForm(),
                Function.identity(),
                (ability1, ability2) -> ability1)
            );

        var detailPoMap = pokemonDetailDao.findByIndexIn(pokemonIndexSet).stream()
            .collect(Collectors.toMap(
                PokemonDetailPo::getIdx,
                Function.identity(),
                (ability1, ability2) -> ability1)
            );

        var detailBaseStatPoMap = pokemonDetailBaseStatDao.findByIndexIn(pokemonIndexSet).stream()
            .collect(Collectors.toMap(
                PokemonDetailBaseStatPo::getIdx,
                Function.identity(),
                (baseStat1, baseStat2) -> baseStat1)
            );

        var detailLearnSetByLevelingUpPoMap = pokemonDetailLearnSetByLevelingUpDao.findByIndexIn(pokemonIndexSet).stream()
            .collect(Collectors.groupingBy(PokemonDetailLearnSetByLevelingUpPo::getIdx));

        var detailLearnSetByTechnicalMachinePoMap = pokemonDetailLearnSetByTechnicalMachineDao.findByIndexIn(pokemonIndexSet).stream()
            .collect(Collectors.groupingBy(PokemonDetailLearnSetByTechnicalMachinePo::getIdx));

        var detailLearnSetByBreedingPoMap = pokemonDetailLearnSetByBreedingDao.findByIndexIn(pokemonIndexSet).stream()
            .collect(Collectors.groupingBy(PokemonDetailLearnSetByBreedingPo::getIdx));

        return pokemonPoList.stream()
            .filter(pokemonPo -> abilityPoMap.containsKey(pokemonPo.getIdx() + pokemonPo.getForm())
                && detailPoMap.containsKey(pokemonPo.getIdx())
                && detailBaseStatPoMap.containsKey(pokemonPo.getIdx()))
            .map(pokemonPo -> {
                Pokemon pokemon = pokemonConverter.toPokemon(pokemonPo);

                var ability = pokemonConverter.toPokemonAbility(
                    abilityPoMap.get(pokemonPo.getIdx() + pokemonPo.getForm())
                );
                pokemon.setAbility(ability);

                var detail = pokemonConverter.toPokemonDetail(
                    detailPoMap.get(pokemonPo.getIdx())
                );
                pokemon.setDetail(detail);

                var baseStat = pokemonConverter.toPokemonDetailBaseStat(
                    detailBaseStatPoMap.get(pokemonPo.getIdx())
                );
                pokemon.setBaseStat(baseStat);

                var learnSetByLevelingUp = pokemonConverter.toPokemonDetailLearnSetByLevelingUp(
                    detailLearnSetByLevelingUpPoMap.getOrDefault(pokemonPo.getIdx(), Collections.emptyList())
                );
                pokemon.setLearnSetByLevelingUp(learnSetByLevelingUp);

                var learnSetByTechnicalMachine = pokemonConverter.toPokemonDetailLearnSetByTechnicalMachine(
                    detailLearnSetByTechnicalMachinePoMap.getOrDefault(pokemonPo.getIdx(), Collections.emptyList())
                );
                pokemon.setLearnSetByTechnicalMachine(learnSetByTechnicalMachine);

                var learnSetByBreeding = pokemonConverter.toPokemonDetailLearnSetByBreeding(
                    detailLearnSetByBreedingPoMap.getOrDefault(pokemonPo.getIdx(), Collections.emptyList())
                );
                pokemon.setLearnSetByBreeding(learnSetByBreeding);
                return pokemon;
            })
            .sorted()
            .toList();
    }
}
