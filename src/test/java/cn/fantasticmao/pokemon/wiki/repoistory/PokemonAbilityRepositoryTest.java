package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.SpringTest;
import cn.fantasticmao.pokemon.wiki.domain.PokemonAbility;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * PokemonAbilityRepositoryTest
 *
 * @author maodh
 * @since 2018/8/5
 */
public class PokemonAbilityRepositoryTest extends SpringTest {
    @Resource
    private PokemonAbilityRepository pokemonAbilityRepository;

    @Test
    public void findOne() {
        PokemonAbility pokemonAbility = pokemonAbilityRepository.findOne(1);
        System.out.println(pokemonAbility);
    }

}