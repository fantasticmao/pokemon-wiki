package cn.fantasticmao.pokemon.web.application.assembler;

import cn.fantasticmao.pokemon.web.application.model.PokemonResponse;
import cn.fantasticmao.pokemon.web.domain.pokemon.model.Pokemon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * AbilityAssembler
 *
 * @author fantasticmao
 * @since 2025-11-05
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PokemonAssembler {

    List<PokemonResponse> toPokemonResponse(List<Pokemon> pokemonList);

    @Mapping(target = "type1", source = "ability.type1")
    @Mapping(target = "type2", source = "ability.type2")
    @Mapping(target = "ability1", source = "ability.ability1")
    @Mapping(target = "ability2", source = "ability.ability2")
    @Mapping(target = "abilityHide", source = "ability.abilityHide")
    PokemonResponse toPokemonResponse(Pokemon pokemon);

}
