package cn.fantasticmao.pokemon.web.web;

import cn.fantasticmao.mundo.web.support.JsonApi;
import cn.fantasticmao.pokemon.web.bean.ItemBean;
import cn.fantasticmao.pokemon.web.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 道具相关接口
 *
 * @author maomao
 * @since 2019-03-23
 */
@Slf4j
@RestController
@RequestMapping("/item")
public class ItemController {
    @Resource
    private ItemService itemService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonApi<List<ItemBean>> listItem() {
        List<ItemBean> itemList = itemService.listAll();
        return JsonApi.<List<ItemBean>>success().data(itemList);
    }
}
