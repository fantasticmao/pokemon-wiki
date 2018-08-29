package cn.fantasticmao.pokemon.wiki.web;

import cn.fantasticmao.pokemon.wiki.domain.Nature;
import cn.fantasticmao.pokemon.wiki.service.NatureService;
import com.mundo.web.annotation.JsonpController;
import com.mundo.web.support.JsonApi;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 性格相关接口
 *
 * @author maodh
 * @since 2018/8/6
 */
@RestController
@JsonpController
@RequestMapping("/nature")
public class NatureController {
    @Resource
    private NatureService natureService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonApi listPokemonNature() {
        List<Nature> pokemonNatureList = natureService.listAll();
        return JsonApi.success().data(pokemonNatureList);
    }
}
