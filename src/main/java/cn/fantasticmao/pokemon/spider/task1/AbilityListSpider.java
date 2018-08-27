package cn.fantasticmao.pokemon.spider.task1;

import cn.fantasticmao.pokemon.spider.Config;
import cn.fantasticmao.pokemon.spider.PokemonDataSource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.jsoup.nodes.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * AbilityListSpider
 *
 * @author maodh
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
        return dataList;
    }

    @Override
    protected boolean saveData(List<AbilityListSpider.Data> dataList) {
        String sql = "INSERT INTO pw_ability(nameZh, nameJa, nameEn, effect, generation) VALUES (?, ?, ?, ?, ?)";
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
            e.printStackTrace();
        }
        return false;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    public class Data implements AbstractTask1Spider.Data {
        private final String nameZh;
        private final String nameJa;
        private final String nameEn;
        private final String effect;
        private final int generation;
    }

    // 丰缘地区
    private List<AbilityListSpider.Data> getDataList3(Document document) {
        return document.select(".s-丰缘 > tbody > tr").stream()
                .skip(1)
                .map(element -> {
                    String nameZh = element.child(1).child(0).html();
                    String nameJa = element.child(2).html();
                    String nameEn = element.child(3).html();
                    String effect = element.child(4).html();
                    int generation = 3;
                    return new AbilityListSpider.Data(nameZh, nameJa, nameEn, effect, generation);
                })
                .collect(Collectors.toList());
    }

    // 神奥地区
    private List<AbilityListSpider.Data> getDataList4(Document document) {
        return document.select(".s-神奥 > tbody > tr").stream()
                .skip(1)
                .map(element -> {
                    String nameZh = element.child(1).child(0).html();
                    String nameJa = element.child(2).html();
                    String nameEn = element.child(3).html();
                    String effect = element.child(4).html();
                    int generation = 4;
                    return new AbilityListSpider.Data(nameZh, nameJa, nameEn, effect, generation);
                })
                .collect(Collectors.toList());
    }

    // 合众地区
    private List<AbilityListSpider.Data> getDataList5(Document document) {
        return document.select(".s-合众 > tbody > tr").stream()
                .skip(1)
                .map(element -> {
                    String nameZh = element.child(1).child(0).html();
                    String nameJa = element.child(2).html();
                    String nameEn = element.child(3).html();
                    String effect = element.child(4).html();
                    int generation = 5;
                    return new AbilityListSpider.Data(nameZh, nameJa, nameEn, effect, generation);
                })
                .collect(Collectors.toList());
    }

    // 卡洛斯地区
    private List<AbilityListSpider.Data> getDataList6(Document document) {
        return document.select(".s-卡洛斯 > tbody > tr").stream()
                .skip(1)
                .map(element -> {
                    String nameZh = element.child(1).child(0).html();
                    String nameJa = element.child(2).html();
                    String nameEn = element.child(3).html();
                    String effect = element.child(4).html();
                    int generation = 6;
                    return new AbilityListSpider.Data(nameZh, nameJa, nameEn, effect, generation);
                })
                .collect(Collectors.toList());
    }

    // 阿罗拉地区
    private List<AbilityListSpider.Data> getDataList7(Document document) {
        return document.select(".s-阿羅拉 > tbody > tr").stream()
                .skip(1)
                .map(element -> {
                    String nameZh = element.child(1).child(0).html();
                    String nameJa = element.child(2).html();
                    String nameEn = element.child(3).html();
                    String effect = element.child(4).html();
                    int generation = 7;
                    return new AbilityListSpider.Data(nameZh, nameJa, nameEn, effect, generation);
                })
                .collect(Collectors.toList());
    }
}