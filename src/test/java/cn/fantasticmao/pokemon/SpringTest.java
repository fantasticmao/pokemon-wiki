package cn.fantasticmao.pokemon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;

/**
 * SpringTest
 *
 * @author maodh
 * @since 2018/8/5
 */
@ActiveProfiles("snapshot")
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class SpringTest {
    @Resource
    private ApplicationContext applicationContext;

    @Test
    public void testSpring() {
        Assertions.assertNotNull(applicationContext);
    }
}
