package cn.fantasticmao.pokemon.wiki.web;

import cn.fantasticmao.pokemon.wiki.domain.PokemonAbility;
import cn.fantasticmao.pokemon.wiki.service.PokemonAbilityService;
import com.mundo.web.annotation.JsonpController;
import com.mundo.web.support.JsonApi;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 宝可梦特性列表（按全国图鉴编号）
 *
 * @author maodh
 * @see <a href="https://pokemon.fantasticmao.cn/pokemon/ability/list">https://pokemon.fantasticmao.cn/pokemon/ability/list</a>
 * @since 2018/8/6
 */
@RestController
@JsonpController
@RequestMapping("/pokemon/ability")
public class PokemonAbilityController {
    @Resource
    private PokemonAbilityService pokemonAbilityService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonApi listPokemonAbility() {
        List<PokemonAbility> pokemonAbilityList = pokemonAbilityService.listAll();
        return JsonApi.success().data(pokemonAbilityList);
    }
}
