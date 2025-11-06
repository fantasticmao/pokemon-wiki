package cn.fantasticmao.pokemon.web.application.assembler;

import cn.fantasticmao.pokemon.web.application.model.ItemResponse;
import cn.fantasticmao.pokemon.web.domain.item.model.Item;
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
public interface ItemAssembler {

    List<ItemResponse> toItemResponse(List<Item> itemList);

    ItemResponse toItemResponse(Item item);

}
