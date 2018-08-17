package cn.fantasticmao.pokemon.spider.task2;

import com.mundo.core.support.Constant;
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
class PokemonMoveDetailSpider extends AbstractTask2Spider<PokemonMoveDetailSpider.Data> {
    private final String nameZh;

    PokemonMoveDetailSpider(final String nameZh) {
        super("https://wiki.52poke.com/zh-hans/" + nameZh + "（招式）");
        this.nameZh = nameZh;
    }

    @Override
    protected PokemonMoveDetailSpider.Data parseData(Document document) {
        try {
            if (document == null) {
                return PokemonMoveDetailSpider.Data.defaultByName(nameZh);
            } else {
                Elements trList = document.selectFirst("#mw-content-text > .mw-parser-output > .roundy").selectFirst("tbody").children();
                final String desc = trList.get(1).text();
                final String imgUrl;
                if (trList.get(2).selectFirst("span") != null) {
                    imgUrl = "http:" + trList.get(2).selectFirst("span").attr("data-url");
                } else if (trList.get(2).selectFirst("img") != null) {
                    imgUrl = "http:" + trList.get(2).selectFirst("img").attr("src");
                } else {
                    imgUrl = Constant.Strings.EMPTY;
                }
                final String notes = trList.get(3).select("table > tbody > tr").get(6).select("td > ul").text();
                final String scope = trList.get(5).select("table > tbody > tr").get(2).text();
                final String effect = document.selectFirst("#mw-content-text > .mw-parser-output > h2 ~ p").text();
                return new PokemonMoveDetailSpider.Data(nameZh, desc, imgUrl, notes, scope, effect);
            }
        } catch (Exception e) {
            logger.error("解析数据异常 " + nameZh, e);
            throw e;
        }
    }

    @Getter
    @ToString
    @AllArgsConstructor
    static class Data implements AbstractTask2Spider.Data {
        private final String nameZh; // 招式名称
        private final String desc; // 招式描述
        private final String imgUrl; // 图片链接
        private final String notes; // 注意事项
        private final String scope; // 作用范围
        private final String effect; // 附加效果

        public static Data defaultByName(String nameZh) {
            return new Data(nameZh, Constant.Strings.EMPTY, Constant.Strings.EMPTY,
                    Constant.Strings.EMPTY, Constant.Strings.EMPTY, Constant.Strings.EMPTY);
        }
    }

}