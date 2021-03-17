package cn.fantasticmao.pokemon.wiki.web;

import cn.fantasticmao.pokemon.wiki.bean.AbilityBean;
import cn.fantasticmao.pokemon.wiki.service.AbilityService;
import com.mundo.web.support.JsonApi;
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
 * 特性相关接口
 *
 * @author maodh
 * @since 2018/8/29
 */
@Slf4j
@Api(tags = "特性相关接口")
@RestController
@RequestMapping("/ability")
public class AbilityController {
    @Resource
    private AbilityService abilityService;

    /**
     * @param nameZh 特性中文名称
     */
    @ApiOperation(value = "特性详情接口")
    @GetMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonApi<List<AbilityBean>> listAbilityDetail(@ApiParam(value = "中文名称，支持模糊查询，例如「火」", example = "火", required = true)
                                                        @RequestParam(defaultValue = "") String nameZh) {
        log.info("输入参数 nameZh: {}", nameZh);
        if (StringUtils.isEmpty(nameZh)) {
            return JsonApi.error(HttpStatus.BAD_REQUEST);
        }

        List<AbilityBean> abilityBeanList = abilityService.listByNameZh(nameZh);
        return JsonApi.<List<AbilityBean>>success().data(abilityBeanList);
    }
}
