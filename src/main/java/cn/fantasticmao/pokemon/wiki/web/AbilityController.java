package cn.fantasticmao.pokemon.wiki.web;

import cn.fantasticmao.pokemon.wiki.bean.AbilityBean;
import cn.fantasticmao.pokemon.wiki.service.AbilityService;
import com.mundo.core.util.StringUtil;
import com.mundo.web.annotation.JsonpController;
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
 * 特性相关接口
 *
 * @author maodh
 * @since 2018/8/29
 */
@Slf4j
@RestController
@JsonpController
@RequestMapping("/ability")
public class AbilityController {
    @Resource
    private AbilityService abilityService;

    /**
     * @param nameZh 特性中文名称
     */
    @GetMapping(value = "/detail")
    public JsonApi listAbilityDetail(@RequestParam(defaultValue = "") String nameZh) {
        log.trace("输入参数 nameZh: {}", nameZh);
        if (StringUtil.isEmpty(nameZh)) {
            return JsonApi.error(HttpStatus.BAD_REQUEST);
        }

        List<AbilityBean> abilityBeanList = abilityService.listByNameZh(nameZh);
        return JsonApi.success().data(abilityBeanList);
    }
}
