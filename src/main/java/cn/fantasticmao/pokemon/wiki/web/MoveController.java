package cn.fantasticmao.pokemon.wiki.web;

import cn.fantasticmao.pokemon.wiki.domain.Move;
import cn.fantasticmao.pokemon.wiki.service.MoveService;
import com.mundo.web.annotation.JsonpController;
import com.mundo.web.support.JsonApi;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 招式相关接口
 *
 * @author maodh
 * @since 2018/8/6
 */
@RestController
@JsonpController
@RequestMapping("/move")
public class MoveController {
    @Resource
    private MoveService moveService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonApi listPokemonMove() {
        List<Move> pokemonMoveList = moveService.listAll();
        return JsonApi.success().data(pokemonMoveList);
    }
}