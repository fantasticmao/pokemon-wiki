package cn.fantasticmao.pokemon.spider.task2;

import cn.fantasticmao.pokemon.spider.PokemonDataSource;
import com.mundo.core.util.JsonUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * PokemonMoveDetailSpiderScheduler
 *
 * @author maodh
 * @since 2018/8/10
 */
public class PokemonMoveDetailSpiderScheduler extends AbstractTask2SpiderScheduler<PokemonMoveDetailSpider.Data> {

    @Override
    public Map<Integer, String> getDataIndex() throws SQLException {
        final TreeMap<Integer, String> dataMap = new TreeMap<>();
        final String sql = "SELECT id, nameZh FROM pw_pokemon_move";
        try (Connection connection = PokemonDataSource.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                final int id = resultSet.getInt(1);
                final String name = resultSet.getString(2);
                dataMap.put(id, name);
            }
        }
        return Collections.unmodifiableMap(dataMap);
    }

    @Override
    protected boolean saveDataList(List<PokemonMoveDetailSpider.Data> dataList) throws SQLException {
        System.out.println(JsonUtil.toJson(dataList));
        return false;
    }

    @Override
    public void start(ExecutorService executorService) {
        try {
            logger.info("加载索引数据...");
            final Map<Integer, String> dataIndex = this.getDataIndex();

            List<PokemonMoveDetailSpider.Data> dataList = new LinkedList<>();
            List<Future<PokemonMoveDetailSpider.Data>> futureList = new LinkedList<>();
            for (String moveName : dataIndex.values()) {
                PokemonMoveDetailSpider spider = new PokemonMoveDetailSpider(moveName);
                Future<PokemonMoveDetailSpider.Data> future = executorService.submit(spider);
                futureList.add(future);
            }
            for (Future<PokemonMoveDetailSpider.Data> future : futureList) {
                dataList.add(future.get());
            }

            logger.info("保存数据...");
            final boolean result = this.saveDataList(dataList);
            logger.info("{} {}", this.getClass().getName(), result ? "保存数据成功" : "保存数据失败");
        } catch (SQLException | InterruptedException | ExecutionException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
