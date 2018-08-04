package cn.fantasticmao.pokemon.spider.task;

import cn.fantasticmao.pokemon.spider.Config;
import com.mundo.core.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.jsoup.nodes.Document;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PokemonNatureListSpider
 *
 * @author maodh
 * @since 2018/8/4
 */
public class PokemonNatureListSpider extends AbstractSpider<PokemonNatureListSpider.Data> {

    public PokemonNatureListSpider() {
        super(Config.Site.POKEMON_NATURE_LIST);
    }

    @Override
    List<PokemonNatureListSpider.Data> parseData(Document document) {
        return document.select("#mw-content-text table").get(1).select("tbody > tr").stream()
                .skip(1)
                .map(element -> {
                    String nameZh = element.child(0).html();
                    String nameJa = element.child(1).html();
                    String nameEn = element.child(2).html();
                    String increasedStat = element.child(3).html();
                    String decreasedStat = element.child(4).html();
                    String favoriteFlavor = element.child(5).html();
                    String dislikedFlavor = element.child(6).html();
                    return new PokemonNatureListSpider.Data(nameZh, nameJa, nameEn, increasedStat, decreasedStat, favoriteFlavor, dislikedFlavor);
                })
                .collect(Collectors.toList());
    }

    @Override
    boolean saveData(Connection connection, List<PokemonNatureListSpider.Data> dataList) {
        System.out.println(JsonUtil.toJson(dataList));
        return false;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    class Data implements AbstractSpider.Data {
        private final String nameZh;
        private final String nameJa;
        private final String nameEn;
        private final String increasedStat;
        private final String decreasedStat;
        private final String favoriteFlavor;
        private final String dislikedFlavor;
    }
}
