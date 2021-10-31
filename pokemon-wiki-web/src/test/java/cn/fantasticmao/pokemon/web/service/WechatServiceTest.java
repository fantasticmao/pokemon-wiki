package cn.fantasticmao.pokemon.web.service;

import cn.fantasticmao.pokemon.web.SpringTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * WechatServiceTest
 *
 * @author maomao
 * @since 2021-10-31
 */
public class WechatServiceTest extends SpringTest {
    @Resource
    private WechatService wechatService;

    @Test
    public void token() {
        String token = wechatService.token();
        Assertions.assertNotNull(token);
    }

    public void searchPokemonInfosByName() {
        String message = wechatService.searchPokemonInfosByName("妙蛙");
        Assertions.assertNotNull(message);
        int lines = message.split(System.lineSeparator()).length;
        Assertions.assertEquals(3, lines);
    }
}
