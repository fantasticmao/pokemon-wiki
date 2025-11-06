package cn.fantasticmao.pokemon.web.infra.converter;

import cn.fantasticmao.pokemon.web.domain.item.model.Item;
import cn.fantasticmao.pokemon.web.infra.model.ItemPo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * ItemConverter
 *
 * @author fantasticmao
 * @since 2025-11-06
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemConverter {

    Item toItem(ItemPo po);

}
