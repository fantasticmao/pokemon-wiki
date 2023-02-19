package cn.fantasticmao.pokemon.web.service;

import cn.fantasticmao.pokemon.web.bean.MoveBean;

import javax.annotation.Nullable;
import java.util.List;

/**
 * MoveService
 *
 * @author fantasticmao
 * @since 2018/8/6
 */
public interface MoveService {

    List<MoveBean> listByName(@Nullable String nameZh, @Nullable String nameEn);

    List<MoveBean> list(int page, int size);
}
