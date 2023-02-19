package cn.fantasticmao.pokemon.web.service;

import cn.fantasticmao.pokemon.web.bean.AbilityBean;

import javax.annotation.Nullable;
import java.util.List;

/**
 * AbilityService
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
public interface AbilityService {

    List<AbilityBean> listByName(@Nullable String nameZh, @Nullable String nameEn);

    List<AbilityBean> list(int page, int size);
}
