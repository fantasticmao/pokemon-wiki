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
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * PokemonBaseStatListSpider
 *
 * @author maodh
 * @since 2018/8/4
 */
public class PokemonBaseStatListSpider extends AbstractSpider<PokemonBaseStatListSpider.Data> {

    public PokemonBaseStatListSpider(CountDownLatch doneSignal) {
        super(Config.Site.POKEMON_BASE_STAT_LIST, doneSignal);
    }

    @Override
    public List<PokemonBaseStatListSpider.Data> parseData(Document document) {
        List<PokemonBaseStatListSpider.Data> dataList = document.select("#mw-content-text table").get(0).select("tbody > tr").stream()
                .filter(element -> element.hasClass("bgwhite"))
                .map(element -> {
                    int index = Integer.parseInt(element.child(0).html());
                    String nameZh = element.child(2).child(0).html();
                    int hp = Integer.parseInt(element.child(3).html());
                    int attack = Integer.parseInt(element.child(4).html());
                    int defense = Integer.parseInt(element.child(5).html());
                    int spAttack = Integer.parseInt(element.child(6).html());
                    int spDefense = Integer.parseInt(element.child(7).html());
                    int speed = Integer.parseInt(element.child(8).html());
                    int total = Integer.parseInt(element.child(9).html());
                    float average = Float.parseFloat(element.child(10).html());
                    return new PokemonBaseStatListSpider.Data(index, nameZh, hp, attack, defense, spAttack, spDefense, speed, total, average);
                })
                .collect(Collectors.toList());
        return Collections.unmodifiableList(dataList);
    }

    @Override
    public boolean saveData(List<PokemonBaseStatListSpider.Data> dataList) {
        final int batchSize = 100;
        final String sql = "INSERT INTO pw_pokemon_base_stat(`index`, nameZh, hp, attack, defense, spAttack, spDefense, speed, total, average) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = PokemonDataSource.INSTANCE.getConnection();
             PreparedStatement prep = connection.prepareStatement(sql)) {
            PokemonBaseStatListSpider.Data temData = null;
            for (int i = batchSize, j = 0; ; i += batchSize) {
                for (; j < i && j < dataList.size(); j++) {
                    temData = dataList.get(j);
                    prep.setInt(1, temData.getIndex());
                    prep.setString(2, temData.getNameZh());
                    prep.setInt(3, temData.getHp());
                    prep.setInt(4, temData.getAttack());
                    prep.setInt(5, temData.getDefense());
                    prep.setInt(6, temData.getSpAttack());
                    prep.setInt(7, temData.getSpDefense());
                    prep.setInt(8, temData.getSpeed());
                    prep.setInt(9, temData.getTotal());
                    prep.setFloat(10, temData.getAverage());
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
        private final int hp;
        private final int attack;
        private final int defense;
        private final int spAttack;
        private final int spDefense;
        private final int speed;
        private final int total;
        private final float average;
    }
}
