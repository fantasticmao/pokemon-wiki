package cn.fantasticmao.pokemon.spider.task1;

import cn.fantasticmao.mundo.core.support.Constant;
import cn.fantasticmao.pokemon.spider.Config;
import cn.fantasticmao.pokemon.spider.PokemonDataSource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * PokemonAbilityListSpider
 *
 * @author fantasticmao
 * @since 2018/8/1
 */
public class PokemonAbilityListSpider extends AbstractTask1Spider<PokemonAbilityListSpider.Data> {

    public PokemonAbilityListSpider(CountDownLatch doneSignal) {
        super(Config.Site.POKEMON_ABILITY_LIST, doneSignal);
    }

    @Override
    public List<PokemonAbilityListSpider.Data> parseData(Document document) {
        List<PokemonAbilityListSpider.Data> dataList = new LinkedList<>();
        dataList.addAll(getDataList1(document));
        dataList.addAll(getDataList2(document));
        dataList.addAll(getDataList3(document));
        dataList.addAll(getDataList4(document));
        dataList.addAll(getDataList5(document));
        dataList.addAll(getDataList6(document));
        dataList.addAll(getDataList7(document));
        dataList.addAll(getDataList8(document));
        return Collections.unmodifiableList(dataList);
    }

    @Override
    public boolean saveData(List<PokemonAbilityListSpider.Data> dataList) {
        final int batchSize = 100;
        final String sql = "INSERT INTO pw_pokemon_ability(`index`, nameZh, type1, type2, ability1, ability2, abilityHide, generation) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = PokemonDataSource.INSTANCE.getConnection();
             PreparedStatement prep = connection.prepareStatement(sql)) {
            PokemonAbilityListSpider.Data tempData = null;
            for (int i = batchSize, j = 0; ; i += batchSize) {
                for (; j < i && j < dataList.size(); j++) {
                    tempData = dataList.get(j);
                    prep.setInt(1, tempData.getIndex());
                    prep.setString(2, tempData.getNameZh());
                    prep.setString(3, tempData.getType1());
                    prep.setString(4, ObjectUtils.defaultIfNull(tempData.getType2(), Constant.Strings.EMPTY));
                    prep.setString(5, tempData.getAbility1());
                    prep.setString(6, ObjectUtils.defaultIfNull(tempData.getAbility2(), Constant.Strings.EMPTY));
                    prep.setString(7, ObjectUtils.defaultIfNull(tempData.getAbilityHide(), Constant.Strings.EMPTY));
                    prep.setInt(8, tempData.getGeneration());
                    prep.addBatch();
                }
                prep.executeBatch();
                if (j >= dataList.size()) {
                    connection.commit();
                    return true;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    static class Data implements AbstractTask1Spider.Data {
        private final int index;
        private final String nameZh;
        private final String type1;
        private final String type2;
        private final String ability1;
        private final String ability2;
        private final String abilityHide;
        private final int generation;
    }

    private static final Function<Element, PokemonAbilityListSpider.Data.DataBuilder> PARSER = element -> {
        int index = Integer.parseInt(element.child(0).text());
        String nameZh = element.child(2).text();
        String type1 = element.child(3).text();
        String type2 = element.child(4).hasClass("hide") ? null : element.child(4).text();
        String ability1 = element.child(5).text();
        String ability2 = element.child(6).hasClass("hide") ? null : element.child(6).text();
        String abilityHide = element.child(7).children().size() == 0 ? null : element.child(7).text();
        return new Data.DataBuilder()
            .index(index).nameZh(nameZh)
            .type1(type1).type2(type2)
            .ability1(ability1).ability2(ability2)
            .abilityHide(abilityHide);
    };

    // 关都地区
    private List<PokemonAbilityListSpider.Data> getDataList1(Document document) {
        return document.select(".bg-关都 > tbody > tr").parallelStream()
            .filter(element -> element.hasClass("bgwhite"))
            .map(element -> PARSER.apply(element)
                .generation(1)
                .build()
            )
            .collect(Collectors.toList());
    }

    // 城都地区
    private List<PokemonAbilityListSpider.Data> getDataList2(Document document) {
        return document.select(".bg-城都 > tbody > tr").parallelStream()
            .filter(element -> element.hasClass("bgwhite"))
            .map(element -> PARSER.apply(element)
                .generation(2)
                .build()
            )
            .collect(Collectors.toList());
    }

    // 丰缘地区
    private List<PokemonAbilityListSpider.Data> getDataList3(Document document) {
        return document.select(".bg-丰缘 > tbody > tr").parallelStream()
            .filter(element -> element.hasClass("bgwhite"))
            .map(element -> PARSER.apply(element)
                .generation(3)
                .build()
            )
            .collect(Collectors.toList());
    }

    // 神奥地区
    private List<PokemonAbilityListSpider.Data> getDataList4(Document document) {
        return document.select(".bg-神奥 > tbody > tr").parallelStream()
            .filter(element -> element.hasClass("bgwhite"))
            .map(element -> PARSER.apply(element)
                .generation(4)
                .build()
            )
            .collect(Collectors.toList());
    }

    // 合众地区
    private List<PokemonAbilityListSpider.Data> getDataList5(Document document) {
        return document.select(".bg-合众 > tbody > tr").parallelStream()
            .filter(element -> element.hasClass("bgwhite"))
            .map(element -> PARSER.apply(element)
                .generation(5)
                .build()
            )
            .collect(Collectors.toList());
    }

    // 卡洛斯地区
    private List<PokemonAbilityListSpider.Data> getDataList6(Document document) {
        return document.select(".bg-卡洛斯 > tbody > tr").parallelStream()
            .filter(element -> element.hasClass("bgwhite"))
            .map(element -> PARSER.apply(element)
                .generation(6)
                .build()
            )
            .collect(Collectors.toList());
    }

    // 阿罗拉地区
    private List<PokemonAbilityListSpider.Data> getDataList7(Document document) {
        return document.select(".bg-阿羅拉 > tbody > tr").parallelStream()
            .filter(element -> element.hasClass("bgwhite"))
            .map(element -> PARSER.apply(element)
                .generation(7)
                .build()
            )
            .collect(Collectors.toList());
    }

    // 伽勒尔地区
    private List<PokemonAbilityListSpider.Data> getDataList8(Document document) {
        return document.select(".bg-伽勒尔 > tbody > tr").parallelStream()
            .filter(element -> element.hasClass("bgwhite"))
            .map(element -> PARSER.apply(element)
                .generation(8)
                .build()
            )
            .collect(Collectors.toList());
    }
}
