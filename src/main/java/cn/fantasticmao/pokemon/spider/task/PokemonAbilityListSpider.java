package cn.fantasticmao.pokemon.spider.task;

import cn.fantasticmao.pokemon.spider.Config;
import cn.fantasticmao.pokemon.spider.bean.PokemonAbility;
import cn.fantasticmao.pokemon.spider.bean.PokemonType;
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
        private final int index;
        private final String infoUrl;
        private final String nameZh;
        private final List<PokemonType> typeList;
        private final List<PokemonAbility> abilityList;
        private final PokemonAbility abilityHide;

        public Data(int index, String infoUrl, String nameZh, List<PokemonType> typeList, List<PokemonAbility> abilityList, PokemonAbility abilityHide) {
            this.index = index;
            this.infoUrl = infoUrl;
            this.nameZh = nameZh;
            this.typeList = typeList;
            this.abilityList = abilityList;
            this.abilityHide = abilityHide;
        }
    }
}
