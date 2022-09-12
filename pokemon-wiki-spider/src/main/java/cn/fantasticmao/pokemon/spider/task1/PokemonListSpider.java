package cn.fantasticmao.pokemon.spider.task1;

import cn.fantasticmao.mundo.core.support.Constant;
import cn.fantasticmao.pokemon.spider.Config;
import cn.fantasticmao.pokemon.spider.PokemonDataSource;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * PokemonListSpider
 *
 * @author fantasticmao
 * @since 2018/7/29
 */
public class PokemonListSpider extends AbstractTask1Spider<PokemonListSpider.Data> {

    public PokemonListSpider(CountDownLatch doneSignal) {
        super(Config.Site.POKEMON_LIST, doneSignal);
    }

    @Override
    public List<PokemonListSpider.Data> parseData(Document document) {
        List<PokemonListSpider.Data> dataList = new LinkedList<>();
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
    public boolean saveData(List<PokemonListSpider.Data> dataList) {
        final int batchSize = 100;
        final String sql = "INSERT INTO pw_pokemon(idx, name_zh, name_ja, name_en, type1, type2, form, generation) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = PokemonDataSource.INSTANCE.getConnection();
             PreparedStatement prep = connection.prepareStatement(sql)) {
            PokemonListSpider.Data tempData = null;
            for (int i = batchSize, j = 0; ; i += batchSize) {
                for (; j < i && j < dataList.size(); j++) {
                    tempData = dataList.get(j);
                    prep.setInt(1, tempData.getIndex());
                    prep.setString(2, tempData.getNameZh());
                    prep.setString(3, tempData.getNameJa());
                    prep.setString(4, tempData.getNameEn());
                    prep.setString(5, tempData.getType1());
                    prep.setString(6, ObjectUtils.defaultIfNull(tempData.getType2(), Constant.Strings.EMPTY));
                    prep.setString(7, ObjectUtils.defaultIfNull(tempData.getForm(), Constant.Strings.EMPTY));
                    prep.setInt(8, tempData.getGeneration());
                    prep.addBatch();
                }
                prep.executeBatch();
                if (j >= dataList.size()) {
                    connection.commit();
                    return true;
                }
            }
        } catch (SQLException e) {
            logger.error("insert into pw_pokemon error", e);
            return false;
        }
    }


    @Getter
    static class Data implements AbstractTask1Spider.Data {
        private final int index;
        private final String nameZh;
        private final String nameJa;
        private final String nameEn;
        private final String type1;
        private final String type2;
        private final String form;
        private final int generation;

        public Data(int index, String nameZh, String nameJa, String nameEn,
                    String type1, String type2, String form, int generation) {
            this.index = index;
            this.nameZh = nameZh;
            this.nameJa = nameJa;
            this.nameEn = nameEn;
            this.type1 = type1;
            this.type2 = type2;
            this.form = form;
            this.generation = generation;
        }
    }

    private static final BiFunction<Element, Integer, Data> PARSER = (element, generation) -> {
        int index = Integer.parseInt(element.child(0).text().replace("#", ""));
        String nameZh = element.child(2).child(0).text();
        Element smallElement = element.child(2).selectFirst("small");
        String form = smallElement == null ? null : smallElement.text();
        String nameJa = element.child(3).text();
        String nameEn = element.child(4).text();
        String type1 = element.child(5).text();
        String type2 = element.child(6).hasClass("hide") ? null : element.child(6).text();
        return new Data(index, nameZh, nameJa, nameEn, type1, type2, form, generation);
    };

    // 关都地区
    private List<PokemonListSpider.Data> getDataList1(Document document) {
        return document.select(".s-关都 > tbody > tr").parallelStream()
            .skip(2)
            .map(element -> PARSER.apply(element, 1))
            .collect(Collectors.toList());
    }

    // 城都地区
    private List<PokemonListSpider.Data> getDataList2(Document document) {
        return document.select(".s-城都 > tbody > tr").parallelStream()
            .skip(2)
            .map(element -> PARSER.apply(element, 2))
            .collect(Collectors.toList());
    }

    // 丰缘地区
    private List<PokemonListSpider.Data> getDataList3(Document document) {
        return document.select(".s-丰缘 > tbody > tr").parallelStream()
            .skip(2)
            .map(element -> PARSER.apply(element, 3))
            .collect(Collectors.toList());
    }

    // 神奥地区
    private List<PokemonListSpider.Data> getDataList4(Document document) {
        return document.select(".s-神奧 > tbody > tr").parallelStream()
            .skip(2)
            .map(element -> PARSER.apply(element, 4))
            .collect(Collectors.toList());
    }

    // 合众地区
    private List<PokemonListSpider.Data> getDataList5(Document document) {
        return document.select(".s-合眾 > tbody > tr").parallelStream()
            .skip(2)
            .map(element -> PARSER.apply(element, 5))
            .collect(Collectors.toList());
    }

    // 卡洛斯地区
    private List<PokemonListSpider.Data> getDataList6(Document document) {
        return document.select(".s-卡洛斯 > tbody > tr").parallelStream()
            .skip(2)
            .map(element -> PARSER.apply(element, 6))
            .collect(Collectors.toList());
    }

    // 阿罗拉地区
    private List<PokemonListSpider.Data> getDataList7(Document document) {
        return document.select(".s-阿羅拉 > tbody > tr").parallelStream()
            .skip(2)
            .map(element -> PARSER.apply(element, 7))
            .collect(Collectors.toList());
    }

    // 伽勒尔地区
    private List<PokemonListSpider.Data> getDataList8(Document document) {
        return document.select(".s-伽勒尔 > tbody > tr").parallelStream()
            .skip(2)
            .map(element -> PARSER.apply(element, 8))
            .collect(Collectors.toList());
    }
}
