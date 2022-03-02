package cn.fantasticmao.pokemon.web;

import cn.fantasticmao.pokemon.Application;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

/**
 * SpringTest
 *
 * @author fantasticmao
 * @since 2018/8/5
 */
@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class)
public class SpringTest {
    @Resource
    private ApplicationContext applicationContext;

    @Test
    public void testSpring() {
        Assertions.assertNotNull(applicationContext);
    }
}
