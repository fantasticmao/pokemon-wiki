package cn.fantasticmao.pokemon.web.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * AdminController
 *
 * @author fantasticmao
 * @since 2018/9/19
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @PostMapping(value = "/logLevel")
    public ResponseEntity<String> changeLogLevel(@RequestParam String level) {
        Level _level = Level.valueOf(level);
        Configurator.setLevel("cn.fantasticmao", _level);
        String message = String.format("修改日志等级成功：=> %s%n", _level);
        return ResponseEntity.ok(message);
    }
}
