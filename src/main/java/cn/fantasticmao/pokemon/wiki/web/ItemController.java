package cn.fantasticmao.pokemon.wiki.web;

import com.mundo.web.annotation.JsonpController;
import com.mundo.web.support.JsonApi;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 道具列表
 *
 * @author maodh
 * @see <a href="https://pokemon.fantasticmao.cn/item/list">https://pokemon.fantasticmao.cn/item/list</a>
 * @since 2018/7/29
 */
@RestController
@JsonpController
@RequestMapping("/item")
public class ItemController {

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonApi listItem() {
        return JsonApi.success();
    }
}
