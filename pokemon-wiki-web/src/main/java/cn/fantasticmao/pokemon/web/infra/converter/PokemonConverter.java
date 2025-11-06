package cn.fantasticmao.pokemon.web.infra.converter;

import cn.fantasticmao.pokemon.web.domain.pokemon.model.Pokemon;
import cn.fantasticmao.pokemon.web.domain.pokemon.model.PokemonAbility;
import cn.fantasticmao.pokemon.web.domain.pokemon.model.PokemonDetail;
import cn.fantasticmao.pokemon.web.domain.pokemon.model.PokemonDetailBaseStat;
import cn.fantasticmao.pokemon.web.domain.pokemon.model.PokemonDetailLearnSetByBreeding;
import cn.fantasticmao.pokemon.web.domain.pokemon.model.PokemonDetailLearnSetByLevelingUp;
import cn.fantasticmao.pokemon.web.domain.pokemon.model.PokemonDetailLearnSetByTechnicalMachine;
import cn.fantasticmao.pokemon.web.infra.model.PokemonAbilityPo;
import cn.fantasticmao.pokemon.web.infra.model.PokemonDetailBaseStatPo;
import cn.fantasticmao.pokemon.web.infra.model.PokemonDetailLearnSetByBreedingPo;
import cn.fantasticmao.pokemon.web.infra.model.PokemonDetailLearnSetByLevelingUpPo;
import cn.fantasticmao.pokemon.web.infra.model.PokemonDetailLearnSetByTechnicalMachinePo;
import cn.fantasticmao.pokemon.web.infra.model.PokemonDetailPo;
import cn.fantasticmao.pokemon.web.infra.model.PokemonPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * PokemonConverter
 *
 * @author maodaohe
 * @since 2025-11-06
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PokemonConverter {

    @Mapping(target = "index", source = "idx")
    Pokemon toPokemon(PokemonPo po);

    PokemonAbility toPokemonAbility(PokemonAbilityPo po);

    PokemonDetail toPokemonDetail(PokemonDetailPo po);

    PokemonDetailBaseStat toPokemonDetailBaseStat(PokemonDetailBaseStatPo po);

    List<PokemonDetailLearnSetByLevelingUp> toPokemonDetailLearnSetByLevelingUp(List<PokemonDetailLearnSetByLevelingUpPo> poList);

    PokemonDetailLearnSetByLevelingUp toPokemonDetailLearnSetByLevelingUp(PokemonDetailLearnSetByLevelingUpPo po);

    List<PokemonDetailLearnSetByTechnicalMachine> toPokemonDetailLearnSetByTechnicalMachine(List<PokemonDetailLearnSetByTechnicalMachinePo> poList);

    PokemonDetailLearnSetByTechnicalMachine toPokemonDetailLearnSetByTechnicalMachine(PokemonDetailLearnSetByTechnicalMachinePo po);

    List<PokemonDetailLearnSetByBreeding> toPokemonDetailLearnSetByBreeding(List<PokemonDetailLearnSetByBreedingPo> poList);

    PokemonDetailLearnSetByBreeding toPokemonDetailLearnSetByBreeding(PokemonDetailLearnSetByBreedingPo po);

}
