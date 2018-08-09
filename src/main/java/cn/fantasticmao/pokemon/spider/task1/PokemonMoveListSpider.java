package cn.fantasticmao.pokemon.spider.task1;

import cn.fantasticmao.pokemon.spider.Config;
import cn.fantasticmao.pokemon.spider.PokemonDataSource;
import com.mundo.core.support.Constant;
import com.mundo.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
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
 * PokemonMoveListSpider
 *
 * @author maodh
 * @since 2018/8/4
 */
public class PokemonMoveListSpider extends AbstractSpider<PokemonMoveListSpider.Data> {

    public PokemonMoveListSpider(CountDownLatch doneSignal) {
        super(Config.Site.POKEMON_MOVE_LIST, doneSignal);
    }

    @Override
    public List<PokemonMoveListSpider.Data> parseData(Document document) {
        List<PokemonMoveListSpider.Data> dataList = new LinkedList<>();
        dataList.addAll(getData1(document));
        dataList.addAll(getData2(document));
        dataList.addAll(getData3(document));
        dataList.addAll(getData4(document));
        dataList.addAll(getData5(document));
        dataList.addAll(getData6(document));
        dataList.addAll(getData7(document));
        return Collections.unmodifiableList(dataList);
    }

    @Override
    public boolean saveData(List<PokemonMoveListSpider.Data> dataList) {
        final int batchSize = 100;
        final String sql = "INSERT INTO pw_pokemon_move(nameZh, nameJa, nameEn, type, category, power, accuracy, pp, generation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = PokemonDataSource.INSTANCE.getConnection();
             PreparedStatement prep = connection.prepareStatement(sql)) {
            PokemonMoveListSpider.Data tempData = null;
            for (int i = batchSize, j = 0; ; i += batchSize) {
                for (; j < i && j < dataList.size(); j++) {
                    tempData = dataList.get(j);
                    prep.setString(1, tempData.getNameZh());
                    prep.setString(2, tempData.getNameJa());
                    prep.setString(3, tempData.getNameEn());
                    prep.setString(4, tempData.getType());
                    prep.setString(5, tempData.getCategory());
                    prep.setString(6, ObjectUtil.defaultIfNull(tempData.getPower(), Constant.Strings.EMPTY));
                    prep.setString(7, ObjectUtil.defaultIfNull(tempData.getAccuracy(), Constant.Strings.EMPTY));
                    prep.setString(8, tempData.getPp());
                    prep.setInt(9, tempData.getGeneration());
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
    @Setter
    @AllArgsConstructor
    public static class Data implements AbstractSpider.Data {
        private final String nameZh;
        private final String nameJa;
        private final String nameEn;
        private final String type;
        private final String category;
        private final String power;
        private final String accuracy;
        private final String pp;
        private final int generation;
    }

    // 第一世代
    private List<PokemonMoveListSpider.Data> getData1(Document document) {
        return document.select(".bg-关都 > tbody > tr").stream()
                .skip(1)
                .map(element -> {
                    String nameZh = element.child(1).child(0).html();
                    String nameJa = element.child(2).html();
                    String nameEn = element.child(3).children().size() == 0 ? element.child(3).html() : element.child(3).child(0).html();
                    String type = element.child(4).child(0).html();
                    String category = element.child(5).child(0).html();
                    String power = "—".equals(element.child(6).html()) ? null : element.child(6).html();
                    String accuracy = "—".equals(element.child(7).html()) ? null : element.child(7).html();
                    String pp = element.child(8).html();
                    return new PokemonMoveListSpider.Data(nameZh, nameJa, nameEn, type, category, power, accuracy, pp, 1);
                })
                .collect(Collectors.toList());
    }

    // 第二世代
    private List<PokemonMoveListSpider.Data> getData2(Document document) {
        return document.select(".bg-城都 > tbody > tr").stream()
                .skip(1)
                .map(element -> {
                    String nameZh = element.child(1).child(0).html();
                    String nameJa = element.child(2).html();
                    String nameEn = element.child(3).html();
                    String type = element.child(4).child(0).html();
                    String category = element.child(5).child(0).html();
                    String power = "—".equals(element.child(6).html()) ? null : element.child(6).html();
                    String accuracy = "—".equals(element.child(7).html()) ? null : element.child(7).html();
                    String pp = element.child(8).html();
                    return new PokemonMoveListSpider.Data(nameZh, nameJa, nameEn, type, category, power, accuracy, pp, 2);
                })
                .collect(Collectors.toList());
    }

    // 第三世代
    private List<PokemonMoveListSpider.Data> getData3(Document document) {
        return document.select(".bg-丰缘 > tbody > tr").stream()
                .skip(1)
                .map(element -> {
                    String nameZh = element.child(1).child(0).html();
                    String nameJa = element.child(2).html();
                    String nameEn = element.child(3).html();
                    String type = element.child(4).child(0).html();
                    String category = element.child(5).child(0).html();
                    String power = "—".equals(element.child(6).html()) ? null : element.child(6).html();
                    String accuracy = "—".equals(element.child(7).html()) ? null : element.child(7).html();
                    String pp = element.child(8).html();
                    return new PokemonMoveListSpider.Data(nameZh, nameJa, nameEn, type, category, power, accuracy, pp, 3);
                })
                .collect(Collectors.toList());
    }

    // 第四世代
    private List<PokemonMoveListSpider.Data> getData4(Document document) {
        return document.select(".bg-神奥 > tbody > tr").stream()
                .skip(1)
                .map(element -> {
                    String nameZh = element.child(1).child(0).html();
                    String nameJa = element.child(2).html();
                    String nameEn = element.child(3).html();
                    String type = element.child(4).child(0).html();
                    String category = element.child(5).child(0).html();
                    String power = "—".equals(element.child(6).html()) ? null : element.child(6).html();
                    String accuracy = "—".equals(element.child(7).html()) ? null : element.child(7).html();
                    String pp = element.child(8).html();
                    return new PokemonMoveListSpider.Data(nameZh, nameJa, nameEn, type, category, power, accuracy, pp, 4);
                })
                .collect(Collectors.toList());
    }

    // 第五世代
    private List<PokemonMoveListSpider.Data> getData5(Document document) {
        return document.select(".bg-合众 > tbody > tr").stream()
                .skip(1)
                .map(element -> {
                    String nameZh = element.child(1).child(0).html();
                    String nameJa = element.child(2).html();
                    String nameEn = element.child(3).html();
                    String type = element.child(4).child(0).html();
                    String category = element.child(5).child(0).html();
                    String power = "—".equals(element.child(6).html()) ? null : element.child(6).html();
                    String accuracy = "—".equals(element.child(7).html()) ? null : element.child(7).html();
                    String pp = element.child(8).html();
                    return new PokemonMoveListSpider.Data(nameZh, nameJa, nameEn, type, category, power, accuracy, pp, 5);
                })
                .collect(Collectors.toList());
    }

    // 第六世代
    private List<PokemonMoveListSpider.Data> getData6(Document document) {
        return document.select(".bg-卡洛斯 > tbody > tr").stream()
                .filter(element -> element.child(0).children().size() == 0)
                .skip(1)
                .map(element -> {
                    String nameZh = element.child(1).child(0).html();
                    String nameJa = element.child(2).html();
                    String nameEn = element.child(3).html();
                    String type = element.child(4).child(0).html();
                    String category = element.child(5).child(0).html();
                    String power = "—".equals(element.child(6).html()) ? null : element.child(6).html();
                    String accuracy = "—".equals(element.child(7).html()) ? null : element.child(7).html();
                    String pp = element.child(8).html();
                    return new PokemonMoveListSpider.Data(nameZh, nameJa, nameEn, type, category, power, accuracy, pp, 6);
                })
                .collect(Collectors.toList());
    }

    // 第七世代
    private List<PokemonMoveListSpider.Data> getData7(Document document) {
        return document.select(".bg-阿罗拉 > tbody > tr").stream()
                .filter(element -> element.child(0).children().size() == 0)
                .skip(1)
                .map(element -> {
                    String nameZh = element.child(1).children().size() == 1 ? element.child(1).child(0).html() : element.child(1).child(1).html();
                    String nameJa = element.child(2).html();
                    String nameEn = element.child(3).html();
                    String type = element.child(4).child(0).html();
                    String category = element.child(5).child(0).html();
                    String power = "—".equals(element.child(6).html()) ? null : element.child(6).html();
                    String accuracy = "—".equals(element.child(7).html()) ? null : element.child(7).html();
                    String pp = element.child(8).html();
                    return new PokemonMoveListSpider.Data(nameZh, nameJa, nameEn, type, category, power, accuracy, pp, 7);
                })
                .collect(Collectors.toList());
    }
}