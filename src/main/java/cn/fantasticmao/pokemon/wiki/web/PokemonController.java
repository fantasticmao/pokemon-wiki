package cn.fantasticmao.pokemon.wiki.web;

import com.mundo.web.annotation.JsonpController;
import com.mundo.web.support.JsonApi;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PokemonController
 *
 * @author maodh
 * @since 2018/7/29
 */
@RestController
@JsonpController
@RequestMapping("/pokemon")
public class PokemonController {

    /**
     * 宝可梦列表接口
     *
     * @see <a href="https://pokemon.fantasticmao.cn/pokemon/list">https://pokemon.fantasticmao.cn/pokemon/list</a>
     */
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonApi listPokemon() {
        return JsonApi.success();
    }

    /**
     * 宝可梦特性列表接口
     *
     * @see <a href="https://pokemon.fantasticmao.cn/pokemon/ability/list">https://pokemon.fantasticmao.cn/pokemon/ability/list</a>
     */
    @GetMapping(value = "/ability/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonApi listPokemonAbility() {
        return JsonApi.success();
    }

    /**
     * 宝可梦性格列表接口
     *
     * @see <a href="https://pokemon.fantasticmao.cn/pokemon/nature/list">https://pokemon.fantasticmao.cn/pokemon/nature/list</a>
     */
    @GetMapping(value = "/nature/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonApi listPokemonNature() {
        return JsonApi.success();
    }

    /**
     * 宝可梦招式列表接口
     *
     * @see <a href="https://pokemon.fantasticmao.cn/pokemon/move/list">https://pokemon.fantasticmao.cn/pokemon/move/list</a>
     */
    @GetMapping(value = "/move/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonApi listPokemonMove() {
        return JsonApi.success();
    }

    /**
     * 宝可梦种族值列表接口
     *
     * @see <a href="https://pokemon.fantasticmao.cn/pokemon/base_stats/list">https://pokemon.fantasticmao.cn/pokemon/base_stats/list</a>
     */
    @GetMapping(value = "/base_stats/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonApi listPokemonBaseStats() {
        return JsonApi.success();
    }
}
