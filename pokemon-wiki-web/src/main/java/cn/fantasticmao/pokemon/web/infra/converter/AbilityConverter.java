package cn.fantasticmao.pokemon.web.infra.converter;

import cn.fantasticmao.pokemon.web.domain.ability.model.Ability;
import cn.fantasticmao.pokemon.web.domain.ability.model.AbilityDetail;
import cn.fantasticmao.pokemon.web.infra.model.AbilityDetailPo;
import cn.fantasticmao.pokemon.web.infra.model.AbilityPo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * AbilityConverter
 *
 * @author maodaohe
 * @since 2025-11-06
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AbilityConverter {

    Ability toAbility(AbilityPo po);

    AbilityDetail toAbilityDetail(AbilityDetailPo po);

}
