package cn.fantasticmao.pokemon;

import cn.fantasticmao.pokemon.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * SpringTest
 *
 * @author maodh
 * @since 2018/8/5
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("snapshot")
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class SpringTest {
    @Resource
    private ApplicationContext applicationContext;

    @Test
    public void testSpring() {
        Assert.assertNotNull(applicationContext);
    }
}
