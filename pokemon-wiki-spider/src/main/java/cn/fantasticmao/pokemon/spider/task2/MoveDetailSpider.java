package cn.fantasticmao.pokemon.spider.task2;

import cn.fantasticmao.mundo.core.support.Constant;
import cn.fantasticmao.pokemon.spider.JsoupUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * MoveDetailSpider
 *
 * @author fantasticmao
 * @since 2018/8/10
 */
class MoveDetailSpider extends AbstractTask2Spider<MoveDetailSpider.Data> {
    private final int id;
    private final String nameZh;

    MoveDetailSpider(final int id, final String nameZh) {
        super("https://wiki.52poke.com/zh-hans/" + nameZh, "（招式）");
        this.id = id;
        this.nameZh = nameZh;
    }

    @Override
    protected MoveDetailSpider.Data parseData(Document document) throws UnsupportedEncodingException {
        Elements trList = document.selectFirst("#mw-content-text > .mw-parser-output > .roundy").selectFirst("tbody").children();
        // 解析获取招式描述
        final String desc = trList.get(1).text();
        // 解析获取图片链接
        String imgUrl;
        if (trList.get(2).selectFirst("span") != null) {
            // 解析例如「https://wiki.52poke.com/zh-hans/拍击（招式）」的招式图片
            imgUrl = trList.get(2).selectFirst("span").attr("data-url").replace("//media.52poke.com", "https://s1.52poke.wiki");
        } else if (trList.get(2).selectFirst("img") != null) {
            // 解析例如「https://wiki.52poke.com/zh-hans/火花（招式）」的招式图片
            imgUrl = trList.get(2).selectFirst("img").attr("data-url").replace("//media.52poke.com", "https://s1.52poke.wiki");
            imgUrl = URLDecoder.decode(imgUrl, StandardCharsets.UTF_8.name());
        } else {
            imgUrl = Constant.Strings.EMPTY;
        }
        // 解析获取注意事项
        final String notes = trList.get(3).select("b + table > tbody > tr").get(6).select("td > ul").text();
        // 解析获取作用范围
        final String scope = trList.get(6).select("b + table > tbody > tr").get(1).text();
        // 解析获取附加效果
        Elements elements = document.select("#mw-content-text > .mw-parser-output > h2").eq(0);
        final String effect = JsoupUtil.nextUntil(elements, "h2").select("p").text();
        return new MoveDetailSpider.Data(id, nameZh, desc, imgUrl, notes, scope, effect);
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
    }

}