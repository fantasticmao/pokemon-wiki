package cn.fantasticmao.pokemon.web.application.controller;

import cn.fantasticmao.pokemon.web.application.assembler.ItemAssembler;
import cn.fantasticmao.pokemon.web.application.model.ItemResponse;
import cn.fantasticmao.pokemon.web.domain.item.model.Item;
import cn.fantasticmao.pokemon.web.domain.item.service.ItemDomainService;
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
 * 道具相关接口
 *
 * @author fantasticmao
 * @since 2019-03-23
 */
@Controller
@RequestMapping("/item")
public class ItemController {
    @Resource
    private ItemDomainService itemDomainService;
    @Resource
    private ItemAssembler itemAssembler;

    /**
     * 道具详情接口
     *
     * @param nameZh 道具中文名称
     * @param nameEn 道具英文名称
     */
    @GetMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemResponse>> listItemDetail(@RequestParam(required = false) String nameZh,
                                                             @RequestParam(required = false) String nameEn) {
        if (StringUtils.isAllEmpty(nameZh, nameEn)) {
            return ResponseEntity.badRequest().build();
        }

        List<Item> itemList = itemDomainService.listByName(nameZh, nameEn);
        return ResponseEntity.ok(itemAssembler.toItemResponse(itemList));
    }

    /**
     * 道具列表接口
     *
     * @param page 页数
     * @param size 页长，默认 50
     */
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemResponse>> listItem(@RequestParam(required = false) Integer page,
                                                       @RequestParam(defaultValue = "50") Integer size) {
        if (page < 0 || size < 1) {
            return ResponseEntity.badRequest().build();
        }
        List<Item> itemList = itemDomainService.list(page, size);
        return ResponseEntity.ok(itemAssembler.toItemResponse(itemList));
    }
}
