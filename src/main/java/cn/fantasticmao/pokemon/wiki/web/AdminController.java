package cn.fantasticmao.pokemon.wiki.web;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.mundo.web.support.JsonApi;
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
 * @author maodh
 * @since 2018/9/19
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping(value = "/logLevel")
    public JsonApi changeLogLevel(@RequestParam String level) {
        ILoggerFactory loggerFactory = LoggerFactory.getILoggerFactory();

        if (loggerFactory instanceof LoggerContext) {
            LoggerContext loggerContext = (LoggerContext) loggerFactory;
            Logger logger = loggerContext.getLogger("cn.fantasticmao.pokemon.wiki");

            Level levelBefore = logger.getLevel();
            Level levelAfter = Level.valueOf(level);
            logger.setLevel(levelAfter);

            System.out.println(String.format("修改日志等级成功：%s => %s", levelBefore, levelAfter));
            return JsonApi.success();
        }
        return JsonApi.error(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
