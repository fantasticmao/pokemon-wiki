package cn.fantasticmao.pokemon.spider.task2;

import cn.fantasticmao.pokemon.spider.JsoupUtil;
import lombok.Getter;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * AbilityDetailSpider
 *
 * @author fantasticmao
 * @since 2018/8/27
 */
class AbilityDetailSpider extends AbstractTask2Spider<AbilityDetailSpider.Data> {
    private final int id;
    private final String nameZh;

    AbilityDetailSpider(final int id, final String nameZh) {
        super("https://wiki.52poke.com/zh-hans/" + nameZh, "（特性）");
        this.id = id;
        this.nameZh = nameZh;
    }

    @Override
    protected AbilityDetailSpider.Data parseData(@Nonnull Document document) {
        final String desc = document.select("#mw-content-text > .mw-parser-output > .at-c > tbody").eq(0).select("tr").eq(4).text();
        Elements elements = document.select("#mw-content-text > .mw-parser-output > h2").eq(0);
        final String effect = JsoupUtil.nextUntil(elements, "h2").select("p,h3").text();
        final List<String> pokemonList = document.select("#mw-content-text > .mw-parser-output > .at-c > tbody").eq(1).select("tr").stream()
            .filter(element -> element.hasClass("bgwhite"))
            .map(element -> element.child(2).child(0).text())
            .distinct()
            .collect(Collectors.toList());
        return new AbilityDetailSpider.Data(id, nameZh, desc, effect, pokemonList);
    }

    @Getter
    static class Data implements AbstractTask2Spider.Data {
        private final int id;
        private final String nameZh; // 特性名称
        private final String desc; // 特性描述
        private final String effect; // 特性效果
        private final List<String> pokemonList; // 拥有此特性的宝可梦

        public Data(int id, String nameZh, String desc, String effect, List<String> pokemonList) {
            this.id = id;
            this.nameZh = nameZh;
            this.desc = desc;
            this.effect = effect;
            this.pokemonList = pokemonList;
        }
    }
}
