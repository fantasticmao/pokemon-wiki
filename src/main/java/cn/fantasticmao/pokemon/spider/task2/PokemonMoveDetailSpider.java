package cn.fantasticmao.pokemon.spider.task2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * PokemonMoveDetailSpider
 *
 * @author maodh
 * @since 2018/8/10
 */
public class PokemonMoveDetailSpider extends AbstractTask2Spider<PokemonMoveDetailSpider.Data> {

    public PokemonMoveDetailSpider(final String key) {
        super("https://wiki.52poke.com/wiki/" + key + "（招式）");
    }

    @Override
    protected PokemonMoveDetailSpider.Data parseData(Document document) {
        Elements trList = document.selectFirst("#mw-content-text > .mw-parser-output > table").selectFirst("tbody").children();
        String desc = trList.get(1).text();
        String imgUrl = trList.get(2).selectFirst("span").attr("data-url");
        imgUrl = imgUrl.startsWith("//") ? "http:" + imgUrl : imgUrl;
        String notes = trList.get(3).select("table > tbody > tr").get(6).select("td > ul").text();
        String scope = trList.get(5).select("table > tbody > tr").get(2).text();
        String effect = document.selectFirst("#mw-content-text > .mw-parser-output > h2 ~ p").text();
        return new PokemonMoveDetailSpider.Data(desc, imgUrl, notes, scope, effect);
    }

    @Getter
    @ToString
    @AllArgsConstructor
    static class Data implements AbstractTask2Spider.Data {
        private final String desc; // 描述
        private final String imgUrl; // gif图
        private final String notes; // 注释事项
        private final String scope; // 作用范围
        private final String effect; // 附加效果
    }

}