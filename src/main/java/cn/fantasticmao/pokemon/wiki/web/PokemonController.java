package cn.fantasticmao.pokemon.wiki.web;

import cn.fantasticmao.pokemon.wiki.domain.Pokemon;
import cn.fantasticmao.pokemon.wiki.service.PokemonService;
import com.mundo.web.annotation.JsonpController;
import com.mundo.web.support.JsonApi;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 宝可梦列表（按全国图鉴编号）
 *
 * @author maodh
 * @see <a href="https://pokemon.fantasticmao.cn/pokemon/list">https://pokemon.fantasticmao.cn/pokemon/list</a>
 * @since 2018/7/29
 */
@RestController
@JsonpController
@RequestMapping("/pokemon")
public class PokemonController {
    @Resource
    private PokemonService pokemonService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonApi listPokemon() {
        List<Pokemon> pokemonList = pokemonService.listAll();
        return JsonApi.success().data(pokemonList);
    }

    @GetMapping(value = "/list/name/{name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonApi listPokemonByName(@PathVariable String name) {
        List<Pokemon> pokemonList = pokemonService.listByName(name);
        return JsonApi.success().data(pokemonList);
    }

    @GetMapping(value = "/list/type/{type}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonApi listPokemonByType(@PathVariable String type) {
        List<Pokemon> pokemonList = pokemonService.listByType(type);
        return JsonApi.success().data(pokemonList);
    }

    @GetMapping(value = "/list/generation/{generation}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonApi listPokemonByType(@PathVariable int generation) {
        List<Pokemon> pokemonList = pokemonService.listByGeneration(generation);
        return JsonApi.success().data(pokemonList);
    }
}
