package cn.fantasticmao.pokemon.web.application.assembler;

import cn.fantasticmao.pokemon.web.application.model.AbilityResponse;
import cn.fantasticmao.pokemon.web.domain.ability.model.Ability;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * AbilityAssembler
 *
 * @author fantasticmao
 * @since 2025-11-05
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AbilityAssembler {

    List<AbilityResponse> toAbilityResponse(List<Ability> abilityList);

    AbilityResponse toAbilityResponse(Ability ability);

}
