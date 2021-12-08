package cn.fantasticmao.pokemon.web.web;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import cn.fantasticmao.mundo.web.support.JsonApi;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping(value = "/logLevel")
    public JsonApi<String> changeLogLevel(@RequestParam String level) {
        ILoggerFactory loggerFactory = LoggerFactory.getILoggerFactory();

        if (loggerFactory instanceof LoggerContext) {
            LoggerContext loggerContext = (LoggerContext) loggerFactory;
            Logger logger = loggerContext.getLogger("cn.fantasticmao");

            Level before = logger.getLevel();
            Level after = Level.valueOf(level);
            logger.setLevel(after);

            String message = String.format("修改日志等级成功：%s => %s", before, after);
            return JsonApi.<String>success().data(message);
        }
        return JsonApi.error(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
