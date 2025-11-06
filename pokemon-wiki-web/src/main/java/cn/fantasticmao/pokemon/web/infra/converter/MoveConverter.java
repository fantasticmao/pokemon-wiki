package cn.fantasticmao.pokemon.web.infra.converter;

import cn.fantasticmao.pokemon.web.domain.move.model.Move;
import cn.fantasticmao.pokemon.web.domain.move.model.MoveDetail;
import cn.fantasticmao.pokemon.web.infra.model.MoveDetailPo;
import cn.fantasticmao.pokemon.web.infra.model.MovePo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * MoveConverter
 *
 * @author fantasticmao
 * @since 2025-11-06
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MoveConverter {

    Move toMove(MovePo po);

    MoveDetail toMoveDetail(MoveDetailPo po);

}
