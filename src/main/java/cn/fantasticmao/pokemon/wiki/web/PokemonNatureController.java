package cn.fantasticmao.pokemon.wiki.web;

import cn.fantasticmao.pokemon.wiki.domain.PokemonNature;
import cn.fantasticmao.pokemon.wiki.service.PokemonNatureService;
import com.mundo.web.annotation.JsonpController;
import com.mundo.web.support.JsonApi;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 宝可梦性格列表
 *
 * @author maodh
 * @see <a href="https://pokemon.fantasticmao.cn/pokemon/nature/list">https://pokemon.fantasticmao.cn/pokemon/nature/list</a>
 * @since 2018/8/6
 */
@RestController
@JsonpController
@RequestMapping("/pokemon/nature")
public class PokemonNatureController {
    @Resource
    private PokemonNatureService pokemonNatureService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonApi listPokemonNature() {
        List<PokemonNature> pokemonNatureList = pokemonNatureService.listAll();
        return JsonApi.success().data(pokemonNatureList);
    }
}
