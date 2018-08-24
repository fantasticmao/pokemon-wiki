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
    private final int id;
    private final String nameZh;

    PokemonMoveDetailSpider(final int id, final String nameZh) {
        super("https://wiki.52poke.com/zh-hans/" + nameZh + "（招式）");
        this.id = id;
        this.nameZh = nameZh;
    }

    @Override
    protected PokemonMoveDetailSpider.Data parseData(Document document) {
        try {
            if (document == null) {
                // 例如请求「https://wiki.52poke.com/zh-hans/究极无敌大冲撞（招式）」的 Status Code 是 404，
                // 但 Response Body 却返回了真实数据，所以需要对其进行特殊处理
                return PokemonMoveDetailSpider.Data.defaultByName(id, nameZh);
            } else {
                Elements trList = document.selectFirst("#mw-content-text > .mw-parser-output > .roundy").selectFirst("tbody").children();
                // 解析获取招式描述
                final String desc = trList.get(1).text();
                // 解析获取图片链接，TODO 完善
                final String imgUrl;
                if (trList.get(2).selectFirst("span") != null) {
                    // 解析例如「https://wiki.52poke.com/zh-hans/拍击（招式）」的招式图片
                    imgUrl = "http:" + trList.get(2).selectFirst("span").attr("data-url");
                } else if (trList.get(2).selectFirst("img") != null) {
                    // 解析例如「https://wiki.52poke.com/zh-hans/火花（招式）」的招式图片
                    imgUrl = "http:" + trList.get(2).selectFirst("img").attr("src");
                } else {
                    // 解析例如「https://wiki.52poke.com/zh-hans/辅助齿轮（招式）」的招式图片
                    imgUrl = Constant.Strings.EMPTY;
                }
                // 解析获取注意事项
                final String notes = trList.get(3).select("table > tbody > tr").get(6).select("td > ul").text();
                // 解析获取作用范围
                final String scope = trList.get(5).select("table > tbody > tr").get(2).text();
                // 解析获取附加效果
                final String effect = document.select("#mw-content-text > .mw-parser-output > h2").eq(0).nextUntil("h2").select("p").text();
                return new PokemonMoveDetailSpider.Data(id, nameZh, desc, imgUrl, notes, scope, effect);
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
        private final int id;
        private final String nameZh; // 招式名称
        private final String desc; // 招式描述
        private final String imgUrl; // 图片链接
        private final String notes; // 注意事项
        private final String scope; // 作用范围
        private final String effect; // 附加效果

        private static Data defaultByName(int id, String nameZh) {
            return new Data(id, nameZh, Constant.Strings.EMPTY, Constant.Strings.EMPTY, Constant.Strings.EMPTY,
                    Constant.Strings.EMPTY, Constant.Strings.EMPTY);
        }
    }

}