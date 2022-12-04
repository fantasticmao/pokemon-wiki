package cn.fantasticmao.pokemon.web.service;

import cn.fantasticmao.pokemon.web.bean.MoveBean;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * MoveService
 *
 * @author fantasticmao
 * @since 2018/8/6
 */
public interface MoveService {

    List<MoveBean> listByNameZh(@Nonnull String nameZh);

    List<MoveBean> list(int page, int size);
}
