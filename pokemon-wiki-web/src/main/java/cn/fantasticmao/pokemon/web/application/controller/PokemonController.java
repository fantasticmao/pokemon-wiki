package cn.fantasticmao.pokemon.web.application.controller;

import cn.fantasticmao.pokemon.web.application.assembler.PokemonAssembler;
import cn.fantasticmao.pokemon.web.application.model.PokemonResponse;
import cn.fantasticmao.pokemon.web.domain.pokemon.model.Pokemon;
import cn.fantasticmao.pokemon.web.domain.pokemon.service.PokemonDomainService;
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
 * 宝可梦相关接口
 *
 * @author fantasticmao
 * @since 2018/7/29
 */
@Controller
@RequestMapping("/pokemon")
public class PokemonController {
    @Resource
    private PokemonDomainService pokemonDomainService;
    @Resource
    private PokemonAssembler pokemonAssembler;

    /**
     * 宝可梦详情接口
     *
     * @param index  全国图鉴编号
     * @param nameZh 宝可梦的中文名称，支持模糊查询
     * @param nameEn 宝可梦的英文名称，支持模糊查询
     * @param form   形态
     */
    @GetMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PokemonResponse>> listPokemonDetail(@RequestParam(required = false) Integer index,
                                                                   @RequestParam(required = false) String nameZh,
                                                                   @RequestParam(required = false) String nameEn,
                                                                   @RequestParam(required = false) String form) {
        if ((index == null || index <= 0) && StringUtils.isAllEmpty(nameZh, nameEn)) {
            return ResponseEntity.badRequest().build();
        }

        List<Pokemon> pokemonList = pokemonDomainService.listByIndexOrName(index, nameZh, nameEn, form);
        return ResponseEntity.ok(pokemonAssembler.toPokemonResponse(pokemonList));
    }

    /**
     * 宝可梦列表接口
     *
     * @param generation 宝可梦的世代
     * @param eggGroup   蛋组
     * @param page       页数
     * @param size       页长，默认 50
     */
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PokemonResponse>> listPokemon(@RequestParam(required = false) Integer generation,
                                                             @RequestParam(required = false) String eggGroup,
                                                             @RequestParam(required = false) Integer page,
                                                             @RequestParam(defaultValue = "50") Integer size) {
        List<Pokemon> pokemonList = pokemonDomainService.listByGenerationAndEggGroup(generation, eggGroup, page, size);
        return ResponseEntity.ok(pokemonAssembler.toPokemonResponse(pokemonList));
    }
}
