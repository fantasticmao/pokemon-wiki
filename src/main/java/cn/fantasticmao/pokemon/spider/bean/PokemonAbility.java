package cn.fantasticmao.pokemon.spider.bean;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

/**
 * PokemonAbility
 *
 * @author maodh
 * @since 2018/8/3
 */
@Getter
@ToString
public class PokemonAbility implements NameComparable<PokemonAbility> {
    private final String name;
    private final String url;

    public PokemonAbility(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokemonAbility that = (PokemonAbility) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
