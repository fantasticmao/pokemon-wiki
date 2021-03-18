package cn.fantasticmao.pokemon.spider.task2;

import cn.fantasticmao.mundo.core.support.Constant;
import org.apache.commons.collections4.CollectionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Ignore;
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

    @Ignore
    @Test
    public void test() throws IOException {
        Document document = Jsoup.connect("https://wiki.52poke.com/zh-hans/老翁龙").get();

        Element table = document.select(".form1").size() == 0
                ? document : document.selectFirst("._toggle[style!='display:none;'] table");

        String idStr = String.format("%03d", 780);
        String imgUrl = table.selectFirst("img[alt^=" + idStr + "]").attr("data-url").replace("//media.52poke.com", "https://s1.52poke.wiki");
        System.out.println("imgUrl: " + imgUrl);

        String type = table.selectFirst("[title=属性]").parent().nextElementSibling().select("span").stream()
                .map(element -> element.text().trim())
                .collect(Collectors.joining(Constant.Strings.COMMA));
        System.out.println("type: " + type);

        String category = table.selectFirst("[title=分类]").parent().nextElementSibling().text().trim();
        System.out.println("category: " + category);

        List<String> abilityList = table.selectFirst("[title=特性]").parent().nextElementSibling().select("td").get(0).select("a").stream()
                .map(element -> element.text().trim())
                .collect(Collectors.toList());
        if (table.selectFirst("[title=特性]").parent().nextElementSibling().select("td").size() > 1) {
            String abilityHide = table.selectFirst("[title=特性]").parent().nextElementSibling().select("td").get(1).select("a").text().trim() + "（隐藏特性）";
            abilityList.add(abilityHide);
        }
        System.out.println("ability: " + String.join(Constant.Strings.COMMA, abilityList));

        String height = table.selectFirst("[title=宝可梦列表（按身高排序）]").parent().nextElementSibling().text().trim();
        System.out.println("height: " + height);

        String weight = table.selectFirst("[title=宝可梦列表（按体重排序）]").parent().nextElementSibling().text().trim();
        System.out.println("weight: " + weight);

        Element bodyStyleElement = table.selectFirst("[title=宝可梦列表（按体形分类）]").parent().nextElementSibling();
        String bodyStyle = bodyStyleElement.select("img").size() == 0 ? "无" : bodyStyleElement.
                selectFirst("img").attr("data-url").replace("//media.52poke.com", "https://s1.52poke.wiki");
        System.out.println("bodyStyle: " + bodyStyle);

        String catchRate = table.selectFirst("[title=捕获率]").parent().nextElementSibling().select("small").text().trim();
        System.out.println("catchRate: " + catchRate);

        List<String> genderRatioList = table.selectFirst("[title=宝可梦列表（按性别比例分类）]").parent().nextElementSibling().select("span").stream()
                .map(element -> element.text().trim())
                .collect(Collectors.toList());
        String genderRatio = CollectionUtils.isEmpty(genderRatioList) ? "无性别" : String.join(Constant.Strings.COMMA, genderRatioList);
        System.out.println("genderRatio: " + genderRatio);

        String eggGroup = table.selectFirst("[title=宝可梦培育]").parent().nextElementSibling().select("td").get(0).select("a").stream()
                .map(element -> element.text().trim())
                .collect(Collectors.joining(Constant.Strings.COMMA));
        System.out.println("eggGroup: " + eggGroup);

        String hatchTime = table.selectFirst("[title=宝可梦培育]").parent().nextElementSibling().select("td").get(1).select("small").text().trim();
        hatchTime = hatchTime.substring(1, hatchTime.length() - 1);
        System.out.println("hatchTime: " + hatchTime);

        String effortValue = table.selectFirst("[title=基础点数]").parent().nextElementSibling().selectFirst("tr").select("td").stream()
                .map(element -> element.text().trim())
                .collect(Collectors.joining(Constant.Strings.COMMA));
        System.out.println("effortValue: " + effortValue);
    }
}
