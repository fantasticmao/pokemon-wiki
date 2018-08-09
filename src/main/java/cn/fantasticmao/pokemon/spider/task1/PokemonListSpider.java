package cn.fantasticmao.pokemon.spider.task1;

import cn.fantasticmao.pokemon.spider.AbstractSpider;
import cn.fantasticmao.pokemon.spider.Config;
import cn.fantasticmao.pokemon.spider.PokemonDataSource;
import com.mundo.core.support.Constant;
import com.mundo.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.jsoup.nodes.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * PokemonListSpider
 *
 * @author maodh
 * @since 2018/7/29
 */
public class PokemonListSpider extends AbstractSpider<PokemonListSpider.Data> {

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
        return Collections.unmodifiableList(dataList);
    }

    @Override
    public boolean saveData(List<PokemonListSpider.Data> dataList) {
        final int batchSize = 100;
        final String sql = "INSERT INTO pw_pokemon(`index`, nameZh, nameJa, nameEn, type1, type2, generation) VALUES (?, ?, ?, ?, ?, ?, ?)";
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
                    prep.setString(6, ObjectUtil.defaultIfNull(tempData.getType2(), Constant.Strings.EMPTY));
                    prep.setInt(7, tempData.getGeneration());
                    prep.addBatch();
                }
                prep.executeBatch();
                if (j >= dataList.size()) {
                    connection.commit();
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Getter
    @ToString
    @AllArgsConstructor
    static class Data implements AbstractSpider.Data {
        private final int index;
        private final String nameZh;
        private final String nameJa;
        private final String nameEn;
        private final String type1;
        private final String type2;
        private final int generation;
    }

    // 关都地区
    private List<PokemonListSpider.Data> getDataList1(Document document) {
        return document.select(".s-关都 > tbody > tr").parallelStream()
                .skip(2)
                .map(element -> {
                    int index = Integer.parseInt(element.child(1).html().replace("#", ""));
                    String nameZh = element.child(3).child(0).html();
                    String nameJa = element.child(4).html();
                    String nameEn = element.child(5).html();
                    String type1 = element.child(6).child(0).html();
                    String type2 = element.child(7).hasClass("hide") ? null : element.child(7).child(0).html();
                    return new PokemonListSpider.Data(index, nameZh, nameJa, nameEn, type1, type2, 1);
                })
                .collect(Collectors.toList());
    }

    // 城都地区
    private List<PokemonListSpider.Data> getDataList2(Document document) {
        return document.select(".s-城都 > tbody > tr").parallelStream()
                .skip(2)
                .map(element -> {
                    int index = Integer.parseInt(element.child(2).html().replace("#", ""));
                    String nameZh = element.child(4).child(0).html();
                    String nameJa = element.child(5).html();
                    String nameEn = element.child(6).html();
                    String type1 = element.child(7).child(0).html();
                    String type2 = element.child(8).hasClass("hide") ? null : element.child(8).child(0).html();
                    return new PokemonListSpider.Data(index, nameZh, nameJa, nameEn, type1, type2, 2);
                })
                .collect(Collectors.toList());
    }

    // 丰缘地区
    private List<PokemonListSpider.Data> getDataList3(Document document) {
        return document.select(".s-豐緣 > tbody > tr").parallelStream()
                .skip(2)
                .map(element -> {
                    int index = Integer.parseInt(element.child(2).html().replace("#", ""));
                    String nameZh = element.child(4).child(0).html();
                    String nameJa = element.child(5).html();
                    String nameEn = element.child(6).html();
                    String type1 = element.child(7).child(0).html();
                    String type2 = element.child(8).hasClass("hide") ? null : element.child(8).child(0).html();
                    return new PokemonListSpider.Data(index, nameZh, nameJa, nameEn, type1, type2, 3);
                })
                .collect(Collectors.toList());
    }

    // 神奥地区
    private List<PokemonListSpider.Data> getDataList4(Document document) {
        return document.select(".s-神奧 > tbody > tr").parallelStream()
                .skip(2)
                .map(element -> {
                    int index = Integer.parseInt(element.child(1).html().replace("#", ""));
                    String nameZh = element.child(3).child(0).html();
                    String nameJa = element.child(4).html();
                    String nameEn = element.child(5).html();
                    String type1 = element.child(6).child(0).html();
                    String type2 = element.child(7).hasClass("hide") ? null : element.child(7).child(0).html();
                    return new PokemonListSpider.Data(index, nameZh, nameJa, nameEn, type1, type2, 4);
                })
                .collect(Collectors.toList());
    }

    // 合众地区
    private List<PokemonListSpider.Data> getDataList5(Document document) {
        return document.select(".s-合眾 > tbody > tr").parallelStream()
                .skip(2)
                .map(element -> {
                    int index = Integer.parseInt(element.child(2).html().replace("#", ""));
                    String nameZh = element.child(4).child(0).html();
                    String nameJa = element.child(5).html();
                    String nameEn = element.child(6).html();
                    String type1 = element.child(7).child(0).html();
                    String type2 = element.child(8).hasClass("hide") ? null : element.child(8).child(0).html();
                    return new PokemonListSpider.Data(index, nameZh, nameJa, nameEn, type1, type2, 5);
                })
                .collect(Collectors.toList());
    }

    // 卡洛斯地区
    private List<PokemonListSpider.Data> getDataList6(Document document) {
        return document.select(".s-卡洛斯 > tbody > tr").parallelStream()
                .skip(2)
                .map(element -> {
                    int index = Integer.parseInt(element.child(3).html().replace("#", ""));
                    String nameZh = element.child(5).child(0).html();
                    String nameJa = element.child(6).html();
                    String nameEn = element.child(7).html();
                    String type1 = element.child(8).child(0).html();
                    String type2 = element.child(9).hasClass("hide") ? null : element.child(9).child(0).html();
                    return new PokemonListSpider.Data(index, nameZh, nameJa, nameEn, type1, type2, 6);
                })
                .collect(Collectors.toList());
    }

    // 阿罗拉地区
    private List<PokemonListSpider.Data> getDataList7(Document document) {
        return document.select(".s-阿羅拉 > tbody > tr").parallelStream()
                .skip(2)
                .map(element -> {
                    int index = Integer.parseInt(element.child(2).html().replace("#", ""));
                    String nameZh = element.child(4).child(0).html();
                    String nameJa = element.child(5).html();
                    String nameEn = element.child(6).html();
                    String type1 = element.child(7).child(0).html();
                    String type2 = element.child(8).hasClass("hide") ? null : element.child(8).child(0).html();
                    return new PokemonListSpider.Data(index, nameZh, nameJa, nameEn, type1, type2, 7);
                })
                .collect(Collectors.toList());
    }
}
