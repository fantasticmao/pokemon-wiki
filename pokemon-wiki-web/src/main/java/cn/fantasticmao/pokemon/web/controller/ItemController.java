package cn.fantasticmao.pokemon.web.controller;

import cn.fantasticmao.mundo.web.support.JsonApi;
import cn.fantasticmao.pokemon.web.bean.ItemBean;
import cn.fantasticmao.pokemon.web.service.ItemService;
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
 * 道具相关接口
 *
 * @author fantasticmao
 * @since 2019-03-23
 */
@Controller
@RequestMapping("/item")
public class ItemController {
    @Resource
    private ItemService itemService;

    /**
     * 道具详情接口
     *
     * @param nameZh 道具中文名称
     * @param nameEn 道具英文名称
     */
    @GetMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonApi<List<ItemBean>>> listItemDetail(@RequestParam(required = false) String nameZh,
                                                                  @RequestParam(required = false) String nameEn) {
        if (StringUtils.isAllEmpty(nameZh, nameEn)) {
            return ResponseEntity.badRequest().build();
        }

        List<ItemBean> itemList = itemService.listByName(nameZh, nameEn);
        return JsonApi.ok(itemList).toResponseEntity();
    }

    /**
     * 道具列表接口
     *
     * @param page 页数
     * @param size 页长，默认 50
     */
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonApi<List<ItemBean>>> listItem(@RequestParam(required = false) Integer page,
                                                            @RequestParam(defaultValue = "50") Integer size) {
        List<ItemBean> itemList = itemService.list(page, size);
        return JsonApi.ok(itemList).toResponseEntity();
    }
}
