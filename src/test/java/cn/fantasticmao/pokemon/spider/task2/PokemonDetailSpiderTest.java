package cn.fantasticmao.pokemon.spider.task2;

import com.mundo.core.support.Constant;
import com.mundo.core.util.CollectionUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PokemonDetailSpiderTest
 *
 * @author maodh
 * @since 2018/8/28
 */
public class PokemonDetailSpiderTest {

    @Test
    public void test() throws IOException {
        Document document = Jsoup.connect("https://wiki.52poke.com/zh-hans/基格尔德").get();

        String idStr = String.format("%03d", 718);
        String imgUrl = document.selectFirst("img[alt^=" + idStr + "]").attr("data-url").replace("//media.52poke.com", "https://s1.52poke.wiki");
        System.out.println("imgUrl: " + imgUrl);

        String type = document.selectFirst("[title=属性]").parent().nextElementSibling().select("span").stream()
                .map(element -> element.text().trim())
                .collect(Collectors.joining(Constant.Strings.COMMA));
        System.out.println("type: " + type);

        String category = document.selectFirst("[title=分类]").parent().nextElementSibling().text().trim();
        System.out.println("category: " + category);

        // TODO 完善
        List<String> abilityList = document.selectFirst("[title=特性]").parent().nextElementSibling().select("td").get(0).select("a").stream()
                .map(element -> element.text().trim())
                .collect(Collectors.toList());
        if (document.selectFirst("[title=特性]").parent().nextElementSibling().select("td").size() > 1) {
            String abilityHide = document.selectFirst("[title=特性]").parent().nextElementSibling().select("td").get(1).select("a").text().trim() + "（隐藏特性）";
            abilityList.add(abilityHide);
        }
        System.out.println("ability: " + CollectionUtil.toString(abilityList));

        String height = document.selectFirst("[title=宝可梦列表（按身高排序）]").parent().nextElementSibling().text().trim();
        System.out.println("height: " + height);

        String weight = document.selectFirst("[title=宝可梦列表（按体重排序）]").parent().nextElementSibling().text().trim();
        System.out.println("weight: " + weight);

        String bodyStyle = document.selectFirst("[title=宝可梦列表（按体形分类）]").parent().nextElementSibling().selectFirst("img").attr("data-url").replace("//media.52poke.com", "https://s1.52poke.wiki");
        System.out.println("bodyStyle: " + bodyStyle);

        String catchRate = document.selectFirst("[title=捕获率]").parent().nextElementSibling().select("small").text().trim();
        System.out.println("catchRate: " + catchRate);

        String genderRatio = document.selectFirst("[title=宝可梦列表（按性别比例分类）]").parent().nextElementSibling().select("span").stream()
                .map(element -> element.text().trim())
                .collect(Collectors.joining(Constant.Strings.COMMA));
        System.out.println("genderRatio: " + genderRatio);

        String eggGroup = document.selectFirst("[title=宝可梦培育]").parent().nextElementSibling().select("td").get(0).select("a").stream()
                .map(element -> element.text().trim())
                .collect(Collectors.joining(Constant.Strings.COMMA));
        System.out.println("eggGroup: " + eggGroup);

        String hatchTime = document.selectFirst("[title=宝可梦培育]").parent().nextElementSibling().select("td").get(1).select("small").text().trim();
        hatchTime = hatchTime.substring(1, hatchTime.length() - 1);
        System.out.println("hatchTime: " + hatchTime);

        String effortValue = document.selectFirst("[title=基础点数]").parent().nextElementSibling().selectFirst("tr").select("td").stream()
                .map(element -> element.text().trim())
                .collect(Collectors.joining(Constant.Strings.COMMA));
        System.out.println("effortValue: " + effortValue);
    }
}
