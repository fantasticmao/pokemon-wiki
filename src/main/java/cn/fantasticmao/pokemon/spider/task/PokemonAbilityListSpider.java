package cn.fantasticmao.pokemon.spider.task;

import cn.fantasticmao.pokemon.spider.Config;
import org.jsoup.nodes.Document;

import java.sql.Connection;
import java.util.List;

/**
 * PokemonAbilityListSpider
 *
 * @author maodh
 * @since 2018/8/1
 */
public class PokemonAbilityListSpider extends AbstractSpider<PokemonAbilityListSpider.Data> {

    public PokemonAbilityListSpider() {
        super(Config.Site.POKEMON_ABILITY_LIST);
    }

    @Override
    List<PokemonAbilityListSpider.Data> parseData(Document document) {
        return null;
    }

    @Override
    boolean saveData(Connection connection, List<PokemonAbilityListSpider.Data> dataList) {
        return false;
    }

    class Data implements AbstractSpider.Data {
    }
}
