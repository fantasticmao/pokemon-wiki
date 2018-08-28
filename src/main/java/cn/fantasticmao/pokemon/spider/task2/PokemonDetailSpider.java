package cn.fantasticmao.pokemon.spider.task2;

import com.mundo.core.support.Constant;
import com.mundo.core.util.CollectionUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.jsoup.nodes.Document;

import java.util.List;
import java.util.stream.Collectors;

/**
 * PokemonDetailSpider
 *
 * @author maodh
 * @since 2018/8/28
 */
public class PokemonDetailSpider extends AbstractTask2Spider<PokemonDetailSpider.Data> {
    private final int id;
    private final String nameZh;

    PokemonDetailSpider(int id, String nameZh) {
        super("https://wiki.52poke.com/zh-hans/" + nameZh);
        this.id = id;
        this.nameZh = nameZh;
    }

    @Override
    protected PokemonDetailSpider.Data parseData(Document document) throws Exception {
        try {
            String idStr = String.format("%03d", id);
            final String imgUrl = document.selectFirst("img[alt^=" + idStr + "]").attr("data-url").replace("//media.52poke.com", "https://s1.52poke.wiki");

            final String type = document.selectFirst("[title=属性]").parent().nextElementSibling().select("span").stream()
                    .map(element -> element.text().trim())
                    .collect(Collectors.joining(Constant.Strings.COMMA));

            final String category = document.selectFirst("[title=分类]").parent().nextElementSibling().text().trim();

            List<String> abilityList = document.selectFirst("[title=特性]").parent().nextElementSibling().select("td").get(0).select("a").stream()
                    .map(element -> element.text().trim())
                    .collect(Collectors.toList());
            if (document.selectFirst("[title=特性]").parent().nextElementSibling().select("td").size() > 1) {
                String abilityHide = document.selectFirst("[title=特性]").parent().nextElementSibling().select("td").get(1).select("a").text().trim() + "（隐藏特性）";
                abilityList.add(abilityHide);
            }
            final String ability = CollectionUtil.toString(abilityList);

            final String height = document.selectFirst("[title=宝可梦列表（按身高排序）]").parent().nextElementSibling().text().trim();

            final String weight = document.selectFirst("[title=宝可梦列表（按体重排序）]").parent().nextElementSibling().text().trim();

            final String bodyStyle = document.selectFirst("[title=宝可梦列表（按体形分类）]").parent().nextElementSibling().selectFirst("img").attr("data-url").replace("//media.52poke.com", "https://s1.52poke.wiki");

            final String catchRate = document.selectFirst("[title=捕获率]").parent().nextElementSibling().select("small").text().trim();

            final String genderRatio = document.selectFirst("[title=宝可梦列表（按性别比例分类）]").parent().nextElementSibling().select("span").stream()
                    .map(element -> element.text().trim())
                    .collect(Collectors.joining(Constant.Strings.COMMA));

            final String eggGroup = document.selectFirst("[title=宝可梦培育]").parent().nextElementSibling().select("td").get(0).select("a").stream()
                    .map(element -> element.text().trim())
                    .collect(Collectors.joining(Constant.Strings.COMMA));

            String hatchTime = document.selectFirst("[title=宝可梦培育]").parent().nextElementSibling().select("td").get(1).select("small").text().trim();
            hatchTime = hatchTime.substring(1, hatchTime.length() - 1);

            final String effortValue = document.selectFirst("[title=基础点数]").parent().nextElementSibling().selectFirst("tr").select("td").stream()
                    .map(element -> element.text().trim())
                    .collect(Collectors.joining(Constant.Strings.COMMA));
            return new PokemonDetailSpider.Data(id, nameZh, imgUrl, type, category, ability, height, weight, bodyStyle, catchRate, genderRatio, eggGroup, hatchTime, effortValue);
        } catch (Exception e) {
            logger.error(nameZh, e);
            throw e;
        }
    }

    @Getter
    @ToString
    @AllArgsConstructor
    static class Data implements AbstractTask2Spider.Data {
        private final int id;
        private final String nameZh;
        private final String imgUrl; // 图片链接
        private final String type; // 属性
        private final String category; // 分类
        private final String ability; // 特性
        private final String height; // 身高
        private final String weight; // 体重
        private final String bodyStyle; // 体形
        private final String catchRate; // 捕获率
        private final String genderRatio; // 性别比例
        private final String eggGroup; // 生蛋分组
        private final String hatchTime; // 孵化时间
        private final String effortValue; // 基础点数
    }
}
