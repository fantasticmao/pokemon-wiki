package cn.fantasticmao.pokemon.web.application.controller;

import cn.fantasticmao.pokemon.web.application.assembler.MoveAssembler;
import cn.fantasticmao.pokemon.web.application.model.MoveResponse;
import cn.fantasticmao.pokemon.web.domain.move.model.Move;
import cn.fantasticmao.pokemon.web.domain.move.service.MoveDomainService;
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
 * 招式相关接口
 *
 * @author fantasticmao
 * @since 2018/8/6
 */
@Controller
@RequestMapping("/move")
public class MoveController {
    @Resource
    private MoveDomainService moveDomainService;
    @Resource
    private MoveAssembler moveAssembler;

    /**
     * 招式详情接口
     *
     * @param nameZh 招式中文名称
     * @param nameEn 招式英文名称
     */
    @GetMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MoveResponse>> listMoveDetail(@RequestParam(required = false) String nameZh,
                                                             @RequestParam(required = false) String nameEn) {
        if (StringUtils.isAllEmpty(nameZh, nameEn)) {
            return ResponseEntity.badRequest().build();
        }

        List<Move> moveList = moveDomainService.listByName(nameZh, nameEn);
        return ResponseEntity.ok(moveAssembler.toMoveResponse(moveList));
    }

    /**
     * 招式列表接口
     *
     * @param page 页数，默认 0，表示首页
     * @param size 页长，默认 50
     */
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MoveResponse>> listMove(@RequestParam(defaultValue = "0") Integer page,
                                                       @RequestParam(defaultValue = "50") Integer size) {
        if (page < 0 || size < 1) {
            return ResponseEntity.badRequest().build();
        }
        List<Move> moveList = moveDomainService.list(page, size);
        return ResponseEntity.ok(moveAssembler.toMoveResponse(moveList));
    }
}
