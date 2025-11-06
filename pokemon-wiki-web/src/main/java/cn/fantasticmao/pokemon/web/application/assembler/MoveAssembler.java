package cn.fantasticmao.pokemon.web.application.assembler;

import cn.fantasticmao.pokemon.web.application.model.MoveResponse;
import cn.fantasticmao.pokemon.web.domain.move.model.Move;
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
public interface MoveAssembler {

    List<MoveResponse> toMoveResponse(List<Move> moveList);

    MoveResponse toMoveResponse(Move move);

}
