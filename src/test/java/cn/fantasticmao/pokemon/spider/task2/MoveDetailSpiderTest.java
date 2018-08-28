package cn.fantasticmao.pokemon.spider.task2;

import com.mundo.core.support.Constant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * MoveDetailSpiderTest
 *
 * @author maodh
 * @since 2018/8/29
 */
public class MoveDetailSpiderTest {

    @Test
    public void test() throws IOException {
        String url = "https://wiki.52poke.com/zh-hans/拍击（招式）";
        Document document = Jsoup.connect(url).get();

        Elements trList = document.selectFirst("#mw-content-text > .mw-parser-output > .roundy").selectFirst("tbody").children();

        String desc = trList.get(1).text();
        System.out.println("desc: " + desc);

        String imgUrl;
        if (trList.get(2).selectFirst("span") != null) {
            // 解析例如「https://wiki.52poke.com/zh-hans/拍击（招式）」的招式图片
            imgUrl = trList.get(2).selectFirst("span").attr("data-url").replace("//media.52poke.com", "https://s1.52poke.wiki");
        } else if (trList.get(2).selectFirst("img") != null) {
            // 解析例如「https://wiki.52poke.com/zh-hans/火花（招式）」的招式图片
            imgUrl = trList.get(2).selectFirst("img").attr("data-url").replace("//media.52poke.com", "https://s1.52poke.wiki");
            imgUrl = URLDecoder.decode(imgUrl, StandardCharsets.UTF_8.name());
        } else {
            // 解析例如「https://wiki.52poke.com/zh-hans/辅助齿轮（招式）」的招式图片
            imgUrl = Constant.Strings.EMPTY;
        }
        System.out.println("imgUrl: " + imgUrl);


        String notes = trList.get(3).select("table > tbody > tr").get(6).select("td > ul").text();
        System.out.println("notes: " + notes);


        String scope = trList.get(5).select("table > tbody > tr").get(2).text();
        System.out.println("scope: " + scope);

        String effect = document.select("#mw-content-text > .mw-parser-output > h2").eq(0).nextUntil("h2").select("p").text();
        System.out.println("effect: " + effect);
    }
}
