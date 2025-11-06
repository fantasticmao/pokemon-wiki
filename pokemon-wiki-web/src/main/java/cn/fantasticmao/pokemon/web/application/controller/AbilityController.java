package cn.fantasticmao.pokemon.web.application.controller;

import cn.fantasticmao.pokemon.web.application.assembler.AbilityAssembler;
import cn.fantasticmao.pokemon.web.application.model.AbilityResponse;
import cn.fantasticmao.pokemon.web.domain.ability.model.Ability;
import cn.fantasticmao.pokemon.web.domain.ability.service.AbilityDomainService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    private AbilityDomainService abilityDomainService;
    @Resource
    private AbilityAssembler abilityAssembler;

    /**
     * 特性详情接口
     *
     * @param nameZh 特性中文名称
     * @param nameEn 特性英文名称
     */
    @GetMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AbilityResponse>> listAbilityDetail(@RequestParam(required = false) String nameZh,
                                                                   @RequestParam(required = false) String nameEn) {
        if (StringUtils.isAllEmpty(nameZh, nameEn)) {
            return ResponseEntity.badRequest().build();
        }

        List<Ability> abilityList = abilityDomainService.listByName(nameZh, nameEn);
        return ResponseEntity.ok(abilityAssembler.toAbilityResponse(abilityList));
    }

    /**
     * 特性列表接口
     *
     * @param page 页数，默认 0，表示首页
     * @param size 页长，默认 50
     */
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AbilityResponse>> listAbility(@RequestParam(defaultValue = "0") Integer page,
                                                             @RequestParam(defaultValue = "50") Integer size) {
        if (page < 0 || size < 1) {
            return ResponseEntity.badRequest().build();
        }
        List<Ability> abilityList = abilityDomainService.list(page, size);
        return ResponseEntity.ok(abilityAssembler.toAbilityResponse(abilityList));
    }
}
