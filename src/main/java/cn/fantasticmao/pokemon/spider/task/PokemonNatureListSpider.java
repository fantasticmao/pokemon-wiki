package cn.fantasticmao.pokemon.spider.task;

import cn.fantasticmao.pokemon.spider.Config;
import com.mundo.core.support.Constant;
import com.mundo.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.jsoup.nodes.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
                    String increasedStat = "—".equals(element.child(3).html()) ? null : element.child(3).html();
                    String decreasedStat = "—".equals(element.child(4).html()) ? null : element.child(4).html();
                    String favoriteFlavor = "—".equals(element.child(5).html()) ? null : element.child(5).html();
                    String dislikedFlavor = "—".equals(element.child(6).html()) ? null : element.child(6).html();
                    return new PokemonNatureListSpider.Data(nameZh, nameJa, nameEn, increasedStat, decreasedStat, favoriteFlavor, dislikedFlavor);
                })
                .collect(Collectors.toList());
    }

    @Override
    boolean saveData(Connection connection, List<PokemonNatureListSpider.Data> dataList) {
        String sql = "INSERT INTO pw_pokemon_nature (nameZh, nameJa, nameEn, increasedStat, decreasedStat, favoriteFlavor, dislikedFlavor) VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement prep = connection.prepareStatement(sql)) {
            for (PokemonNatureListSpider.Data data : dataList) {
                prep.setString(1, data.getNameZh());
                prep.setString(2, data.getNameJa());
                prep.setString(3, data.getNameEn());
                prep.setString(4, ObjectUtil.defaultIfNull(data.getIncreasedStat(), Constant.Strings.EMPTY));
                prep.setString(5, ObjectUtil.defaultIfNull(data.getDecreasedStat(), Constant.Strings.EMPTY));
                prep.setString(6, ObjectUtil.defaultIfNull(data.getFavoriteFlavor(), Constant.Strings.EMPTY));
                prep.setString(7, ObjectUtil.defaultIfNull(data.getDislikedFlavor(), Constant.Strings.EMPTY));
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
