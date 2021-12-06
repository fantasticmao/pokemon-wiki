package cn.fantasticmao.pokemon.web.web;

import cn.fantasticmao.mundo.web.support.JsonApi;
import cn.fantasticmao.pokemon.web.bean.AbilityBean;
import cn.fantasticmao.pokemon.web.service.AbilityService;
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
 * @author fantasticmao
 * @since 2018/8/29
 */
@Slf4j
@RestController
@RequestMapping("/ability")
public class AbilityController {
    @Resource
    private AbilityService abilityService;

    /**
     * 特性详情接口
     *
     * @param nameZh 特性中文名称
     */
    @GetMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonApi<List<AbilityBean>> listAbilityDetail(@RequestParam(defaultValue = "") String nameZh) {
        log.info("输入参数 nameZh: {}", nameZh);
        if (StringUtils.isEmpty(nameZh)) {
            return JsonApi.error(HttpStatus.BAD_REQUEST);
        }

        List<AbilityBean> abilityBeanList = abilityService.listByNameZh(nameZh);
        return JsonApi.<List<AbilityBean>>success().data(abilityBeanList);
    }

    /**
     * 特性列表接口
     *
     * @param page 页数，默认 0，表示首页
     * @param size 页长，默认 50
     */
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonApi<List<AbilityBean>> listAbility(@RequestParam(defaultValue = "0") Integer page,
                                                  @RequestParam(defaultValue = "50") Integer size) {
        page = Math.max(page, 0);
        size = Math.max(size, 1);
        size = Math.min(size, 500);
        List<AbilityBean> abilityBeanList = abilityService.list(page, size);
        return JsonApi.<List<AbilityBean>>success().data(abilityBeanList);
    }
}