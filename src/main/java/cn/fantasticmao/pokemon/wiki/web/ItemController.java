package cn.fantasticmao.pokemon.wiki.web;

import cn.fantasticmao.pokemon.wiki.bean.ItemBean;
import cn.fantasticmao.pokemon.wiki.service.ItemService;
import com.mundo.web.support.JsonApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "道具相关接口")
@RestController
@RequestMapping("/item")
public class ItemController {
    @Resource
    private ItemService itemService;

    @ApiOperation(value = "道具列表接口")
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonApi<List<ItemBean>> listItem() {
        List<ItemBean> itemList = itemService.list();
        return JsonApi.<List<ItemBean>>success().data(itemList);
    }
}
