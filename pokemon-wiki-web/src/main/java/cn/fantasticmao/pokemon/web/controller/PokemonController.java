package cn.fantasticmao.pokemon.web.controller;

import cn.fantasticmao.mundo.core.support.Constant;
import cn.fantasticmao.mundo.web.support.JsonApi;
import cn.fantasticmao.pokemon.web.bean.PokemonBean;
import cn.fantasticmao.pokemon.web.service.PokemonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 宝可梦相关接口
 *
 * @author fantasticmao
 * @since 2018/7/29
 */
@Slf4j
@RestController
@RequestMapping("/pokemon")
public class PokemonController {
    @Resource
    private PokemonService pokemonService;

    /**
     * 宝可梦详情接口
     *
     * @param nameZh 宝可梦的中文名称，支持模糊查询
     */
    @GetMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonApi<List<PokemonBean>> listPokemonDetail(@RequestParam(defaultValue = "0") Integer index,
                                                        @RequestParam(defaultValue = "") String nameZh) {
        if ((index == null || index <= 0) && StringUtils.isEmpty(nameZh)) {
            return JsonApi.error(HttpStatus.BAD_REQUEST);
        }

        List<PokemonBean> pokemonBeanList = pokemonService.listByIndexOrNameZh(index, nameZh);
        return JsonApi.<List<PokemonBean>>success().data(pokemonBeanList);
    }

    /**
     * 宝可梦列表接口
     *
     * @param generation 宝可梦的世代，默认 0 为全部
     * @param eggGroup   蛋组，默认表示g不参与过滤条件
     * @param page       页数，默认 -1，表示获取全量数据
     * @param size       页长，默认 50
     */
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonApi<List<PokemonBean>> listPokemon(@RequestParam(defaultValue = "0") Integer generation,
                                                  @RequestParam(defaultValue = Constant.Strings.EMPTY) String eggGroup,
                                                  @RequestParam(defaultValue = "-1") Integer page,
                                                  @RequestParam(defaultValue = "50") Integer size) {
        List<PokemonBean> pokemonBeanList = pokemonService.listByGenerationAndEggGroup(generation, eggGroup, page, size);
        return JsonApi.<List<PokemonBean>>success().data(pokemonBeanList);
    }
}
