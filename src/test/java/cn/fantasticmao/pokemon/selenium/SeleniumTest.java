package cn.fantasticmao.pokemon.selenium;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * SeleniumTest
 *
 * @author maodh
 * @since 2018/8/28
 */
public class SeleniumTest {

    @Ignore
    @Test
    public void test() {
        System.setProperty("webdriver.chrome.driver", "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");

        WebDriver driver = new ChromeDriver();

        driver.get("http://www.google.com");

        System.out.println(driver.getTitle());

        driver.close();
    }
}
