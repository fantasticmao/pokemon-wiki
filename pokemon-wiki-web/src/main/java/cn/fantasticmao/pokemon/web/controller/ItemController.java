package cn.fantasticmao.pokemon.web.controller;

import cn.fantasticmao.mundo.web.support.JsonApi;
import cn.fantasticmao.pokemon.web.bean.ItemBean;
import cn.fantasticmao.pokemon.web.service.ItemService;
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
 * 道具相关接口
 *
 * @author fantasticmao
 * @since 2019-03-23
 */
@Slf4j
@RestController
@RequestMapping("/item")
public class ItemController {
    @Resource
    private ItemService itemService;

    /**
     * 道具详情接口
     *
     * @param nameZh 道具中文名称
     */
    @GetMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonApi<List<ItemBean>> listItemDetail(@RequestParam(defaultValue = "") String nameZh) {
        if (StringUtils.isEmpty(nameZh)) {
            return JsonApi.error(HttpStatus.BAD_REQUEST);
        }

        List<ItemBean> itemList = itemService.listByNameZh(nameZh);
        return JsonApi.<List<ItemBean>>success().data(itemList);
    }

    /**
     * 道具列表接口
     *
     * @param page 页数，默认 -1，表示获取全量数据
     * @param size 页长，默认 50
     */
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonApi<List<ItemBean>> listItem(@RequestParam(defaultValue = "-1") Integer page,
                                            @RequestParam(defaultValue = "50") Integer size) {
        List<ItemBean> itemList = itemService.list(page, size);
        return JsonApi.<List<ItemBean>>success().data(itemList);
    }
}
