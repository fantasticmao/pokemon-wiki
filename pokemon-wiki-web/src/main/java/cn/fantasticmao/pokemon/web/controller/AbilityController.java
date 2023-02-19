package cn.fantasticmao.pokemon.web.controller;

import cn.fantasticmao.mundo.web.support.JsonApi;
import cn.fantasticmao.pokemon.web.bean.AbilityBean;
import cn.fantasticmao.pokemon.web.service.AbilityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * 特性相关接口
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
@Controller
@RequestMapping("/ability")
public class AbilityController {
    @Resource
    private AbilityService abilityService;

    /**
     * 特性详情接口
     *
     * @param nameZh 特性中文名称
     * @param nameEn 特性英文名称
     */
    @GetMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonApi<List<AbilityBean>>> listAbilityDetail(@RequestParam(required = false) String nameZh,
                                                                        @RequestParam(required = false) String nameEn) {
        if (StringUtils.isAllEmpty(nameZh, nameEn)) {
            return ResponseEntity.badRequest().build();
        }

        List<AbilityBean> abilityBeanList = abilityService.listByName(nameZh, nameEn);
        return JsonApi.ok(abilityBeanList).toResponseEntity();
    }

    /**
     * 特性列表接口
     *
     * @param page 页数，默认 0，表示首页
     * @param size 页长，默认 50
     */
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonApi<List<AbilityBean>>> listAbility(@RequestParam(defaultValue = "0") Integer page,
                                                                  @RequestParam(defaultValue = "50") Integer size) {
        List<AbilityBean> abilityBeanList = abilityService.list(page, size);
        return JsonApi.ok(abilityBeanList).toResponseEntity();
    }
}
