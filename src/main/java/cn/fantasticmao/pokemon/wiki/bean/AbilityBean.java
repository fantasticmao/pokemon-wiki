package cn.fantasticmao.pokemon.wiki.bean;

import cn.fantasticmao.pokemon.wiki.domain.Ability;
import cn.fantasticmao.pokemon.wiki.domain.AbilityDetail;
import lombok.Getter;
import lombok.ToString;

import javax.annotation.Nonnull;
import java.io.Serializable;

/**
 * AbilityBean
 *
 * @author maodh
 * @since 2018/8/29
 */
@Getter
@ToString
public class AbilityBean implements Serializable, Comparable<AbilityBean> {
    private final int id;
    private final String nameZh;
    private final String nameJa;
    private final String nameEn;
    private final int generation;
    private final String desc;
    private final String effect;
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
