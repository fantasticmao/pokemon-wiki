package cn.fantasticmao.pokemon.wiki.web;

import cn.fantasticmao.pokemon.wiki.domain.PokemonBaseStat;
import cn.fantasticmao.pokemon.wiki.service.PokemonBaseStatService;
import com.mundo.web.annotation.JsonpController;
import com.mundo.web.support.JsonApi;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 宝可梦种族值列表（第七世代）
 *
 * @author maodh
 * @see <a href="https://pokemon.fantasticmao.cn/pokemon/base_stat/list">https://pokemon.fantasticmao.cn/pokemon/base_stat/list</a>
 * @since 2018/8/6
 */
@RestController
@JsonpController
@RequestMapping("/pokemon/base_stat")
public class PokemonBaseStatController {
    @Resource
    private PokemonBaseStatService pokemonBaseStatService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonApi listPokemonBaseStats() {
        List<PokemonBaseStat> pokemonBaseStatList = pokemonBaseStatService.listAll();
        return JsonApi.success().data(pokemonBaseStatList);
    }
}
