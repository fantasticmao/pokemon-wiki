package cn.fantasticmao.pokemon.web.domain;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.domain.common.service.WechatDomainService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * WechatDomainServiceTest
 *
 * @author fantasticmao
 * @since 2021-10-31
 */
public class WechatDomainServiceTest extends SpringTest {
    @Resource
    private WechatDomainService wechatDomainService;

    @Test
    public void token() {
        String token = wechatDomainService.token();
        Assertions.assertNotNull(token);
    }
}
