package cn.fantasticmao.pokemon.web.controller;

import cn.fantasticmao.mundo.web.support.JsonApi;
import cn.fantasticmao.pokemon.web.bean.MoveBean;
import cn.fantasticmao.pokemon.web.service.MoveService;
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
 * 招式相关接口
 *
 * @author fantasticmao
 * @since 2018/8/6
 */
@Controller
@RequestMapping("/move")
public class MoveController {
    @Resource
    private MoveService moveService;

    /**
     * 招式详情接口
     *
     * @param nameZh 招式中文名称
     * @param nameEn 招式英文名称
     */
    @GetMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonApi<List<MoveBean>>> listMoveDetail(@RequestParam(required = false) String nameZh,
                                                                  @RequestParam(required = false) String nameEn) {
        if (StringUtils.isAllEmpty(nameZh, nameEn)) {
            return ResponseEntity.badRequest().build();
        }

        List<MoveBean> moveBeanList = moveService.listByName(nameZh, nameEn);
        return JsonApi.ok(moveBeanList).toResponseEntity();
    }

    /**
     * 招式列表接口
     *
     * @param page 页数，默认 0，表示首页
     * @param size 页长，默认 50
     */
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonApi<List<MoveBean>>> listMove(@RequestParam(defaultValue = "0") Integer page,
                                                            @RequestParam(defaultValue = "50") Integer size) {
        List<MoveBean> moveBeanList = moveService.list(page, size);
        return JsonApi.ok(moveBeanList).toResponseEntity();
    }
}
