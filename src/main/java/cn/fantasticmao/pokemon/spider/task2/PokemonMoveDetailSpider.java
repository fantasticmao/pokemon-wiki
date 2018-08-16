package cn.fantasticmao.pokemon.spider.task2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.jsoup.nodes.Document;

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
        // TODO 解析数据
        return new PokemonMoveDetailSpider.Data("a", "b", "c", "d", "e");
    }

    @Getter
    @ToString
    @AllArgsConstructor
    static class Data implements AbstractTask2Spider.Data {
        private final String desc;
        private final String imgUrl;
        private final String effect;
        private final String notes;
        private final String scope;
    }

}