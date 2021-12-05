package cn.fantasticmao.pokemon.web.service;

import cn.fantasticmao.pokemon.web.SpringTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * WechatServiceTest
 *
 * @author fantasticmao
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
}
