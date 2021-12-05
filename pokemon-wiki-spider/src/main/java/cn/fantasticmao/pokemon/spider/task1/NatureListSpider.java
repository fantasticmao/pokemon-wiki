package cn.fantasticmao.pokemon.spider.task1;

import cn.fantasticmao.mundo.core.support.Constant;
import cn.fantasticmao.pokemon.spider.Config;
import cn.fantasticmao.pokemon.spider.PokemonDataSource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.ObjectUtils;
import org.jsoup.nodes.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * NatureListSpider
 *
 * @author fantasticmao
 * @since 2018/8/4
 */
public class NatureListSpider extends AbstractTask1Spider<NatureListSpider.Data> {

    public NatureListSpider(CountDownLatch doneSignal) {
        super(Config.Site.NATURE_LIST, doneSignal);
    }

    @Override
    public List<NatureListSpider.Data> parseData(Document document) {
        List<NatureListSpider.Data> dataList = document.selectFirst("#mw-content-text table").select("tbody > tr").stream()
            .skip(1)
            .map(element -> {
                String nameZh = element.child(0).text();
                String nameJa = element.child(1).text();
                String nameEn = element.child(2).text();
                String increasedStat = "—".equals(element.child(3).text()) ? null : element.child(3).text();
                String decreasedStat = "—".equals(element.child(4).text()) ? null : element.child(4).text();
                String favoriteFlavor = "—".equals(element.child(5).text()) ? null : element.child(5).text();
                String dislikedFlavor = "—".equals(element.child(6).text()) ? null : element.child(6).text();
                return new NatureListSpider.Data(nameZh, nameJa, nameEn, increasedStat, decreasedStat, favoriteFlavor, dislikedFlavor);
            })
            .collect(Collectors.toList());
        return Collections.unmodifiableList(dataList);
    }

    @Override
    public boolean saveData(List<NatureListSpider.Data> dataList) {
        String sql = "INSERT INTO pw_nature(nameZh, nameJa, nameEn, increasedStat, decreasedStat, favoriteFlavor, dislikedFlavor) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = PokemonDataSource.INSTANCE.getConnection();
             PreparedStatement prep = connection.prepareStatement(sql)) {
            for (NatureListSpider.Data data : dataList) {
                prep.setString(1, data.getNameZh());
                prep.setString(2, data.getNameJa());
                prep.setString(3, data.getNameEn());
                prep.setString(4, ObjectUtils.defaultIfNull(data.getIncreasedStat(), Constant.Strings.EMPTY));
                prep.setString(5, ObjectUtils.defaultIfNull(data.getDecreasedStat(), Constant.Strings.EMPTY));
                prep.setString(6, ObjectUtils.defaultIfNull(data.getFavoriteFlavor(), Constant.Strings.EMPTY));
                prep.setString(7, ObjectUtils.defaultIfNull(data.getDislikedFlavor(), Constant.Strings.EMPTY));
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
    static class Data implements AbstractTask1Spider.Data {
        private final String nameZh;
        private final String nameJa;
        private final String nameEn;
        private final String increasedStat;
        private final String decreasedStat;
        private final String favoriteFlavor;
        private final String dislikedFlavor;
    }
}
