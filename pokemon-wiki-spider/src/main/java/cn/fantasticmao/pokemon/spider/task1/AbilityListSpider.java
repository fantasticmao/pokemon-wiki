package cn.fantasticmao.pokemon.spider.task1;

import cn.fantasticmao.pokemon.spider.Config;
import cn.fantasticmao.pokemon.spider.PokemonDataSource;
import lombok.Getter;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * AbilityListSpider
 *
 * @author fantasticmao
 * @since 2018/8/26
 */
public class AbilityListSpider extends AbstractTask1Spider<AbilityListSpider.Data> {

    public AbilityListSpider(CountDownLatch doneSignal) {
        super(Config.Site.ABILITY_LIST, doneSignal);
    }

    @Override
    protected List<AbilityListSpider.Data> parseData(Document document) {
        List<AbilityListSpider.Data> dataList = new LinkedList<>();
        dataList.addAll(getDataList3(document));
        dataList.addAll(getDataList4(document));
        dataList.addAll(getDataList5(document));
        dataList.addAll(getDataList6(document));
        dataList.addAll(getDataList7(document));
        dataList.addAll(getDataList8(document));
        dataList.addAll(getDataList9(document));
        return dataList;
    }

    @Override
    protected boolean saveData(List<AbilityListSpider.Data> dataList) {
        String sql = "INSERT INTO t_ability(name_zh, name_ja, name_en, effect, generation) " +
            "VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = PokemonDataSource.INSTANCE.getConnection();
             PreparedStatement prep = connection.prepareStatement(sql)) {
            for (AbilityListSpider.Data data : dataList) {
                prep.setString(1, data.getNameZh());
                prep.setString(2, data.getNameJa());
                prep.setString(3, data.getNameEn());
                prep.setString(4, data.getEffect());
                prep.setInt(5, data.getGeneration());
                prep.addBatch();
            }
            prep.executeBatch();
            connection.commit();
            return true;
        } catch (SQLException e) {
            logger.error("insert into t_ability error", e);
            return false;
        }
    }

    @Getter
    static class Data implements AbstractTask1Spider.Data {
        private final String nameZh;
        private final String nameJa;
        private final String nameEn;
        private final String effect;
        private final int generation;

        public Data(String nameZh, String nameJa, String nameEn, String effect, int generation) {
            this.nameZh = nameZh;
            this.nameJa = nameJa;
            this.nameEn = nameEn;
            this.effect = effect;
            this.generation = generation;
        }
    }

    private static final BiFunction<Element, Integer, Data> PARSER = (element, generation) -> {
        String nameZh = element.child(1).text();
        String nameJa = element.child(2).text();
        String nameEn = element.child(3).text();
        String effect = element.child(4).text();
        return new Data(nameZh, nameJa, nameEn, effect, generation);
    };

    // 丰缘地区
    private List<AbilityListSpider.Data> getDataList3(Document document) {
        return document.select(".s-丰缘 > tbody > tr").stream()
            .skip(1)
            .map(element -> PARSER.apply(element, 3))
            .collect(Collectors.toList());
    }

    // 神奥地区
    private List<AbilityListSpider.Data> getDataList4(Document document) {
        return document.select(".s-神奥 > tbody > tr").stream()
            .skip(1)
            .map(element -> PARSER.apply(element, 4))
            .collect(Collectors.toList());
    }

    // 合众地区
    private List<AbilityListSpider.Data> getDataList5(Document document) {
        return document.select(".s-合众 > tbody > tr").stream()
            .skip(1)
            .map(element -> PARSER.apply(element, 5))
            .collect(Collectors.toList());
    }

    // 卡洛斯地区
    private List<AbilityListSpider.Data> getDataList6(Document document) {
        return document.select(".s-卡洛斯 > tbody > tr").stream()
            .skip(1)
            .map(element -> PARSER.apply(element, 6))
            .collect(Collectors.toList());
    }

    // 阿罗拉地区
    private List<AbilityListSpider.Data> getDataList7(Document document) {
        return document.select(".s-阿羅拉 > tbody > tr").stream()
            .skip(1)
            .map(element -> PARSER.apply(element, 7))
            .collect(Collectors.toList());
    }

    // 伽勒尔地区
    private List<AbilityListSpider.Data> getDataList8(Document document) {
        return document.select(".s-伽勒尔 > tbody > tr").stream()
            .skip(1)
            .map(element -> PARSER.apply(element, 8))
            .collect(Collectors.toList());
    }

    // 帕底亚地区
    private List<AbilityListSpider.Data> getDataList9(Document document) {
        return document.select(".b-帕底亚 > tbody > tr").stream()
            .skip(1)
            .map(element -> PARSER.apply(element, 9))
            .collect(Collectors.toList());
    }
}
