package cn.fantasticmao.pokemon.wiki.web;

import cn.fantasticmao.mundo.core.support.Constant;
import cn.fantasticmao.mundo.web.support.JsonApi;
import cn.fantasticmao.pokemon.wiki.bean.PokemonBean;
import cn.fantasticmao.pokemon.wiki.service.PokemonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
 * @author maodh
 * @since 2018/7/29
 */
@Slf4j
@Api(tags = "宝可梦相关接口")
@RestController
@RequestMapping("/pokemon")
public class PokemonController {
    @Resource
    private PokemonService pokemonService;

    /**
     * @param nameZh 宝可梦的中文名称，支持模糊查询
     */
    @ApiOperation(value = "宝可梦详情接口")
    @GetMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonApi<List<PokemonBean>> listPokemonDetail(@ApiParam(value = "全国图鉴编号", example = "1")
                                                        @RequestParam(defaultValue = "0") Integer index,
                                                        @ApiParam(value = "中文名称，支持模糊查询，例如「妙蛙」", example = "妙蛙种子")
                                                        @RequestParam(defaultValue = "") String nameZh) {
        log.info("输入参数 index:{} nameZh: {}", index, nameZh);
        if ((index == null || index <= 0) && StringUtils.isEmpty(nameZh)) {
            return JsonApi.error(HttpStatus.BAD_REQUEST);
        }

        List<PokemonBean> pokemonBeanList = pokemonService.listByIndexOrNameZh(index, nameZh);
        return JsonApi.<List<PokemonBean>>success().data(pokemonBeanList);
    }

    /**
     * @param generation 宝可梦的世代，默认 0 为全部
     * @param eggGroup   蛋组，默认表示g不参与过滤条件
     */
    @ApiOperation(value = "宝可梦列表接口")
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonApi<List<PokemonBean>> listPokemon(@ApiParam(value = "第几世代，0 表示获取全部世代的宝可梦", example = "1")
                                                  @RequestParam(defaultValue = "0") Integer generation,
                                                  @ApiParam(value = "蛋组名称", example = "植物群")
                                                  @RequestParam(defaultValue = Constant.Strings.EMPTY) String eggGroup) {
        List<PokemonBean> pokemonBeanList = pokemonService.listByGenerationAndEggGroup(generation, eggGroup);
        return JsonApi.<List<PokemonBean>>success().data(pokemonBeanList);
    }
}
