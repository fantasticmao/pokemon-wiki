package cn.fantasticmao.pokemon.wiki.web;

import com.mundo.web.mvc.WeChatConfigController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * WeChatController
 *
 * @author maodh
 * @since 2018/12/5
 */
@Slf4j
@RestController
@RequestMapping("/wechat")
public class WeChatServerConfigController extends WeChatConfigController {

    @GetMapping("/config")
    @Override
    public String configServer(HttpServletRequest request) {
        return super.configServer(request);
    }
}
