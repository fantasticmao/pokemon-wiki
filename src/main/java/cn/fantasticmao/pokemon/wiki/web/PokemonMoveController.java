package cn.fantasticmao.pokemon.wiki.web;

import cn.fantasticmao.pokemon.wiki.domain.PokemonMove;
import cn.fantasticmao.pokemon.wiki.service.PokemonMoveService;
import com.mundo.web.annotation.JsonpController;
import com.mundo.web.support.JsonApi;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 宝可梦招式列表
 *
 * @author maodh
 * @see <a href="https://pokemon.fantasticmao.cn/pokemon/move/list">https://pokemon.fantasticmao.cn/pokemon/move/list</a>
 * @since 2018/8/6
 */
@RestController
@JsonpController
@RequestMapping("/pokemon/move")
public class PokemonMoveController {
    @Resource
    private PokemonMoveService pokemonMoveService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonApi listPokemonMove() {
        List<PokemonMove> pokemonMoveList = pokemonMoveService.listAll();
        return JsonApi.success().data(pokemonMoveList);
    }
}