package cn.fantasticmao.pokemon.spider.bean;

import javax.validation.constraints.NotNull;

/**
 * NameComparable
 *
 * @author maodh
 * @since 2018/8/3
 */
public interface NameComparable<T extends NameComparable> extends Comparable<T> {

    String getName();

    @Override
    default int compareTo(@NotNull T t) {
        if (this.getName().length() < t.getName().length()) {
            return -1;
        } else if (this.getName().length() == t.getName().length()) {
            return this.getName().compareTo(t.getName());
        } else {
            return 1;
        }
    }
}
