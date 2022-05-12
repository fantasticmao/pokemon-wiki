package cn.fantasticmao.pokemon.web.controller;

import cn.fantasticmao.mundo.web.support.JsonApi;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * AdminController
 *
 * @author fantasticmao
 * @since 2018/9/19
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping(value = "/logLevel")
    public JsonApi<String> changeLogLevel(@RequestParam String level) {
        Level _level = Level.valueOf(level);
        Configurator.setLevel("cn.fantasticmao", _level);
        String message = String.format("修改日志等级成功：=> %s", _level);
        return JsonApi.success(message);
    }
}
