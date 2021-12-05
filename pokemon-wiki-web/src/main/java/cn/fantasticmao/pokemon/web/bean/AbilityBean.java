package cn.fantasticmao.pokemon.web.bean;

import cn.fantasticmao.pokemon.web.domain.Ability;
import cn.fantasticmao.pokemon.web.domain.AbilityDetail;
import lombok.Getter;
import lombok.ToString;

import javax.annotation.Nonnull;
import java.io.Serializable;

/**
 * AbilityBean
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
@Getter
@ToString
public class AbilityBean implements Serializable, Comparable<AbilityBean> {
    /**
     * 特性编号
     */
    private final int id;

    /**
     * 中文名称
     */
    private final String nameZh;
    /**
     * 日文名称
     */
    private final String nameJa;

    /**
     * 英文名称
     */
    private final String nameEn;

    /**
     * 第几世代
     */
    private final int generation;

    /**
     * 特性描述，以空格分隔
     */
    private final String desc;

    /**
     * 特性效果，以空格分隔
     */
    private final String effect;

    /**
     * 拥有此特性的宝可梦，以逗号分隔
     */
    private final String pokemons;

    @Override
    public int compareTo(@Nonnull AbilityBean abilityBean) {
        int r;
        if ((r = Integer.compare(this.getGeneration(), abilityBean.getGeneration())) != 0) {
            return r;
        } else if ((r = Integer.compare(this.getId(), abilityBean.getId())) != 0) {
            return r;
        } else {
            return 0;
        }
    }

    private AbilityBean(int id, String nameZh, String nameJa, String nameEn, int generation, String desc, String effect, String pokemons) {
        this.id = id;
        this.nameZh = nameZh;
        this.nameJa = nameJa;
        this.nameEn = nameEn;
        this.generation = generation;
        this.desc = desc;
        this.effect = effect;
        this.pokemons = pokemons;
    }

    public static AbilityBean ofDomain(Ability ability, AbilityDetail abilityDetail) {
        return new AbilityBean(ability.getId(), ability.getNameZh(), ability.getNameJa(), ability.getNameEn(),
            ability.getGeneration(), abilityDetail.getDesc(), abilityDetail.getEffect(), abilityDetail.getPokemons());
    }
}
