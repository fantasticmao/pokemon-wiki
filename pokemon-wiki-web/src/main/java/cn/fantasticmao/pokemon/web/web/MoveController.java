package cn.fantasticmao.pokemon.web.web;

import cn.fantasticmao.mundo.web.support.JsonApi;
import cn.fantasticmao.pokemon.web.bean.MoveBean;
import cn.fantasticmao.pokemon.web.service.MoveService;
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
 * 招式相关接口
 *
 * @author maodh
 * @since 2018/8/6
 */
@Slf4j
@RestController
@RequestMapping("/move")
public class MoveController {
    @Resource
    private MoveService moveService;

    /**
     * 招式详情接口
     *
     * @param nameZh 招式中文名称
     */
    @GetMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonApi<List<MoveBean>> listPokemonMove(@RequestParam(defaultValue = "") String nameZh) {
        log.info("输入参数 nameZh: {}", nameZh);
        if (StringUtils.isEmpty(nameZh)) {
            return JsonApi.error(HttpStatus.BAD_REQUEST);
        }

        List<MoveBean> moveBeanList = moveService.listByNameZh(nameZh);
        return JsonApi.<List<MoveBean>>success().data(moveBeanList);
    }
}