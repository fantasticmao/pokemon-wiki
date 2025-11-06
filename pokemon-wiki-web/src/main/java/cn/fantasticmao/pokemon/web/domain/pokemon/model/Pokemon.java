package cn.fantasticmao.pokemon.web.domain.pokemon.model;

import lombok.Data;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 宝可梦实体
 *
 * @author fantasticmao
 * @since 2025-11-05
 */
@Data
public class Pokemon implements Comparable<Pokemon> {
    /**
     * 数据主键
     */
    private Integer id;

    /**
     * 全国图鉴
     */
    private Integer index;

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
     * 形态
     */
    private String form;

    /**
     * 第几世代
     */
    private Integer generation;

    /**
     * 宝可梦能力
     */
    private PokemonAbility ability;

    /**
     * 宝可梦详细信息
     */
    @Nullable
    private PokemonDetail detail;

    /**
     * 宝可梦种族值
     */
    @Nullable
    private PokemonDetailBaseStat baseStat;

    /**
     * 宝可梦可学会的招式
     */
    @Nullable
    private List<PokemonDetailLearnSetByLevelingUp> learnSetByLevelingUp;

    /**
     * 宝可梦能使用的招式学习器
     */
    @Nullable
    private List<PokemonDetailLearnSetByTechnicalMachine> learnSetByTechnicalMachine;

    /**
     * 宝可梦蛋招式
     */
    @Nullable
    private List<PokemonDetailLearnSetByBreeding> learnSetByBreeding;

    @Override
    public int compareTo(Pokemon that) {
        int r;
        if ((r = Integer.compare(this.getGeneration(), that.getGeneration())) != 0) {
            return r;
        } else if ((r = Integer.compare(this.getIndex(), that.getIndex())) != 0) {
            return r;
        } else {
            return 0;
        }
    }
}
