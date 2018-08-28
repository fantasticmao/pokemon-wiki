package cn.fantasticmao.pokemon.spider.task2;

import com.mundo.core.support.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.jsoup.nodes.Document;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * AbilityDetailSpider
 *
 * @author maodh
 * @since 2018/8/27
 */
class AbilityDetailSpider extends AbstractTask2Spider<AbilityDetailSpider.Data> {
    private final int id;
    private final String nameZh;

    AbilityDetailSpider(final int id, final String nameZh) {
        super("https://wiki.52poke.com/zh-hans/" + nameZh + "（特性）");
        this.id = id;
        this.nameZh = nameZh;
    }

    @Override
    protected AbilityDetailSpider.Data parseData(Document document) {
        if (document == null) {
            return AbilityDetailSpider.Data.defaultByName(id, nameZh);
        } else {
            final String desc = document.selectFirst("#mw-content-text > .mw-parser-output > .at-c > tbody").children().get(3).select("table").text();
            final String effect = document.select("#mw-content-text > .mw-parser-output > h2").eq(0).nextUntil("h2").select("p,h3").text();
            final List<String> pokemonList = document.select("#mw-content-text > .mw-parser-output > .at-c > tbody").get(1).select("tr").stream()
                    .filter(element -> element.hasClass("bgwhite"))
                    .map(element -> element.child(2).text())
                    .collect(Collectors.toList());
            return new AbilityDetailSpider.Data(id, nameZh, desc, effect, pokemonList);
        }
    }

    @Getter
    @ToString
    @AllArgsConstructor
    static class Data implements AbstractTask2Spider.Data {
        private final int id;
        private final String nameZh; // 特性名称
        private final String desc; // 特性描述
        private final String effect; // 特性效果
        private final List<String> pokemonList; // 拥有此特性的宝可梦

        private static Data defaultByName(int id, String nameZh) {
            return new Data(id, nameZh, Constant.Strings.EMPTY, Constant.Strings.EMPTY, Collections.emptyList());
        }
    }
}
