package cn.fantasticmao.pokemon.spider.task1;

import cn.fantasticmao.pokemon.spider.Config;
import cn.fantasticmao.pokemon.spider.PokemonDataSource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.nodes.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * ItemListSpider
 *
 * @author maomao
 * @since 2019-03-20
 */
public class ItemListSpider extends AbstractTask1Spider<ItemListSpider.Data> {

    public ItemListSpider(CountDownLatch doneSignal) {
        super(Config.Site.ITEM_LIST, doneSignal);
    }

    @Override
    protected List<Data> parseData(Document document) {
        // TODO 解析数据
        return null;
    }

    @Override
    protected boolean saveData(List<ItemListSpider.Data> dataList) {
        final int batchSize = 100;
        final String sql = "INSERT INTO pw_item(type, imgUrl, nameZh, nameJa, nameEn, `desc`, generation) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = PokemonDataSource.INSTANCE.getConnection();
             PreparedStatement prep = connection.prepareCall(sql)) {
            ItemListSpider.Data tmpData = null;
            for (int i = batchSize, j = 0; ; i += batchSize) {
                for (; j < i && j < dataList.size(); j++) {
                    tmpData = dataList.get(j);
                    prep.setString(1, tmpData.getType());
                    prep.setString(2, tmpData.getImgUrl());
                    prep.setString(3, tmpData.getNameZh());
                    prep.setString(4, tmpData.getNameJa());
                    prep.setString(5, tmpData.getNameEn());
                    prep.setString(6, tmpData.getDesc());
                    prep.setInt(7, tmpData.getGeneration());
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
    public static class Data implements AbstractTask1Spider.Data {
        private final String type;
        private final String imgUrl;
        private final String nameZh;
        private final String nameJa;
        private final String nameEn;
        private final String desc;
        private final int generation;
    }
}
