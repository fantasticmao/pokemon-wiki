package cn.fantasticmao.pokemon.wiki.web;

import cn.fantasticmao.pokemon.wiki.bean.MoveBean;
import cn.fantasticmao.pokemon.wiki.service.MoveService;
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
     * @param nameZh 招式中文名称
     */
    @GetMapping(value = "/detail")
    public JsonApi listPokemonMove(@RequestParam(defaultValue = "") String nameZh) {
        log.info("输入参数 nameZh: {}", nameZh);
        if (StringUtil.isEmpty(nameZh)) {
            return JsonApi.error(HttpStatus.BAD_REQUEST);
        }

        List<MoveBean> moveBeanList = moveService.listByNameZh(nameZh);
        return JsonApi.success().data(moveBeanList);
    }
}