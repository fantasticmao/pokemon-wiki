package cn.fantasticmao.pokemon.wiki.domain;

import com.mundo.data.domain.AbstractDomain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Pokemon
 *
 * @author maodh
 * @since 2018/7/29
 */
@Entity
@Table(name = "pw_pokemon")
public class Pokemon extends AbstractDomain<Integer> {
}
