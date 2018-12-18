package cn.fantasticmao.pokemon.wiki.web;

import cn.fantasticmao.pokemon.wiki.bean.PokemonBean;
import cn.fantasticmao.pokemon.wiki.service.PokemonService;
import com.mundo.core.util.StringUtil;
import com.mundo.web.support.JsonApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 宝可梦相关接口
 *
 * @author maodh
 * @since 2018/7/29
 */
@Slf4j
@RestController
@RequestMapping("/pokemon")
public class PokemonController {
    @Resource
    private PokemonService pokemonService;

    /**
     * @param nameZh 宝可梦的中文名称，支持模糊查询
     */
    @GetMapping(value = "/detail")
    public JsonApi listPokemonDetail(@RequestParam(defaultValue = "") String nameZh) {
        log.info("输入参数 nameZh: {}", nameZh);
        if (StringUtil.isEmpty(nameZh)) {
            return JsonApi.error(HttpStatus.BAD_REQUEST);
        }

        List<PokemonBean> pokemonBeanList = pokemonService.listByNameZh(nameZh);
        return JsonApi.success().data(pokemonBeanList);
    }

    /**
     * @param generation 宝可梦的世代，默认 0 为全部
     */
    @GetMapping(value = "/list")
    public JsonApi listPokemon(@RequestParam(defaultValue = "0") Integer generation) {
        List<PokemonBean> pokemonBeanList = pokemonService.listByGeneration(generation);
        return JsonApi.success().data(pokemonBeanList);
    }
}
