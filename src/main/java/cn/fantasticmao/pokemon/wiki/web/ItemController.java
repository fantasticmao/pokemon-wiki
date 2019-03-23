package cn.fantasticmao.pokemon.wiki.web;

import cn.fantasticmao.pokemon.wiki.bean.ItemBean;
import cn.fantasticmao.pokemon.wiki.service.ItemService;
import com.mundo.web.support.JsonApi;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping(value = "/list")
    public JsonApi listItem() {
        List<ItemBean> itemList = itemService.list();
        return JsonApi.success().data(itemList);
    }
}
