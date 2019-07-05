package cn.fantasticmao.pokemon.wiki.bean;

import cn.fantasticmao.pokemon.wiki.domain.Ability;
import cn.fantasticmao.pokemon.wiki.domain.AbilityDetail;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "特性编号", example = "18")
    private final int id;
    @ApiModelProperty(value = "中文名称", example = "引火")
    private final String nameZh;
    @ApiModelProperty(value = "日文名称", example = "もらいび")
    private final String nameJa;
    @ApiModelProperty(value = "英文名称", example = "Flash Fire")
    private final String nameEn;
    @ApiModelProperty(value = "第几世代", example = "3")
    private final int generation;
    @ApiModelProperty(value = "特性描述，以空格分隔", example = "受到火属性的招式攻击时，吸收火焰，自己使出的火属性招式会变强。")
    private final String desc;
    @ApiModelProperty(value = "特性效果，以空格分隔", example = "对战中 该特性的宝可梦不受火属性招式影响，当被火属性招式击中时，此宝可梦标记为引火状态，直到换下场或者失去此特性。 只要此宝可梦已处于引火状态，此宝可梦使用火属性攻击招式时，宝可梦的攻击和特攻×1.5。 信长的野望中 受到火属性招式攻击时不受伤害，反而提升攻击力1阶段。1回合内无论受到多少次火属性招式攻击，攻击力都只会提升一次。")
    private final String effect;
    @ApiModelProperty(value = "拥有此特性的宝可梦，以逗号分隔", example = "六尾,九尾,卡蒂狗,风速狗,小火马,烈焰马,火伊布,火球鼠,火岩鼠,火暴兽,戴鲁比,黑鲁加,席多蓝恩,烛光灵,灯火幽灵,水晶灯火灵,熔蚁兽")
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
