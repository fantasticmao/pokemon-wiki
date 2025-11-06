package cn.fantasticmao.pokemon.web.domain.move.service;

import cn.fantasticmao.pokemon.web.domain.move.model.Move;

import javax.annotation.Nullable;
import java.util.List;

/**
 * MoveDomainService
 *
 * @author fantasticmao
 * @since 2018/8/6
 */
public interface MoveDomainService {

    List<Move> listByName(@Nullable String nameZh, @Nullable String nameEn);

    List<Move> list(int page, int size);

}
