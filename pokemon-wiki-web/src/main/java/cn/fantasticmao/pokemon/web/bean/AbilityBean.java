package cn.fantasticmao.pokemon.web.bean;

import cn.fantasticmao.pokemon.web.domain.Ability;
import cn.fantasticmao.pokemon.web.domain.AbilityDetail;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * AbilityBean
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
@Getter
@Setter
public class AbilityBean implements Comparable<AbilityBean> {
    /**
     * 特性编号
     */
    private int id;

    /**
     * 中文名称
     */
    private String nameZh;
    /**
     * 日文名称
     */
    private String nameJa;

    /**
     * 英文名称
     */
    private String nameEn;

    /**
     * 第几世代
     */
    private int generation;

    /**
     * 特性描述，以空格分隔
     */
    @Deprecated
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String desc;

    /**
     * 特性效果，以空格分隔
     */
    @Deprecated
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String effect;

    /**
     * 拥有此特性的宝可梦，以逗号分隔
     */
    @Deprecated
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pokemons;

    /**
     * 特性详情
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Detail detail;

    @Override
    public int compareTo(@Nonnull AbilityBean that) {
        int r;
        if ((r = Integer.compare(this.getGeneration(), that.getGeneration())) != 0) {
            return r;
        } else if ((r = Integer.compare(this.getId(), that.getId())) != 0) {
            return r;
        } else {
            return 0;
        }
    }

    public AbilityBean() {
    }

    private AbilityBean(int id, String nameZh, String nameJa, String nameEn, int generation,
                        String desc, String effect, String pokemons, Detail detail) {
        this.id = id;
        this.nameZh = nameZh;
        this.nameJa = nameJa;
        this.nameEn = nameEn;
        this.generation = generation;
        this.desc = desc;
        this.effect = effect;
        this.pokemons = pokemons;
        this.detail = detail;
    }

    public static AbilityBean ofDomain(@Nonnull Ability ability, @Nullable AbilityDetail abilityDetail) {
        return new AbilityBean(ability.getId(), ability.getNameZh(), ability.getNameJa(),
            ability.getNameEn(), ability.getGeneration(),
            abilityDetail == null ? null : abilityDetail.getDesc(),
            abilityDetail == null ? null : abilityDetail.getEffect(),
            abilityDetail == null ? null : abilityDetail.getPokemons(),
            Detail.ofDomain(abilityDetail));
    }

    @Getter
    @Setter
    private static class Detail {
        /**
         * 特性描述，以空格分隔
         */
        private String desc;

        /**
         * 特性效果，以空格分隔
         */
        private String effect;

        /**
         * 拥有此特性的宝可梦，以逗号分隔
         */
        private String pokemons;

        public Detail() {
        }

        private Detail(String desc, String effect, String pokemons) {
            this.desc = desc;
            this.effect = effect;
            this.pokemons = pokemons;
        }

        public static Detail ofDomain(@Nullable AbilityDetail domain) {
            if (domain == null) {
                return null;
            }
            return new Detail(domain.getDesc(), domain.getEffect(), domain.getPokemons());
        }
    }
}
