package cn.fantasticmao.pokemon.spider.task2;

import cn.fantasticmao.mundo.core.support.Constant;
import cn.fantasticmao.pokemon.spider.PokemonDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.*;

/**
 * AbilityDetailSpiderScheduler
 *
 * @author fantasticmao
 * @since 2018/8/13
 */
public class AbilityDetailSpiderScheduler extends AbstractTask2SpiderScheduler<AbilityDetailSpider.Data> {

    public AbilityDetailSpiderScheduler(ExecutorService executorService) {
        super(executorService);
    }

    @Override
    protected Map<Integer, String> getDataIndex() {
        final TreeMap<Integer, String> dataMap = new TreeMap<>();
        final String sql = "SELECT id, name_zh FROM t_ability";
        try (Connection connection = PokemonDataSource.INSTANCE.getConnection();
             PreparedStatement prep = connection.prepareStatement(sql);
             ResultSet resultSet = prep.executeQuery()) {
            while (resultSet.next()) {
                final int id = resultSet.getInt(1);
                final String name = resultSet.getString(2);
                dataMap.put(id, name);
            }
        } catch (SQLException e) {
            logger.error("select from t_ability error", e);
        }
        return Collections.unmodifiableMap(dataMap);
    }

    @Override
    protected boolean saveDataList(List<AbilityDetailSpider.Data> dataList) {
        // 1. 排序数据
        dataList.sort(Comparator.comparingInt(AbilityDetailSpider.Data::getId));

        // 2. 保存数据
        final String sql = "INSERT INTO t_ability_detail(name_zh, desc, effect, pokemons) " +
            "VALUES (?, ?, ?, ?)";
        try (Connection connection = PokemonDataSource.INSTANCE.getConnection();
             PreparedStatement prep = connection.prepareStatement(sql)) {
            for (AbilityDetailSpider.Data data : dataList) {
                prep.setString(1, data.getNameZh());
                prep.setString(2, data.getDesc());
                prep.setString(3, data.getEffect());
                prep.setString(4, String.join(Constant.Strings.COMMA, data.getPokemonList()));
                prep.addBatch();
            }
            prep.executeBatch();
            connection.commit();
            return true;
        } catch (SQLException e) {
            logger.error("insert into t_ability_detail error", e);
            return false;
        }
    }

    @Override
    public void start() throws InterruptedException {
        try {
            logger.info("loading data index from t_ability...");
            final Map<Integer, String> dataIndex = this.getDataIndex();

            // 1. 提交爬虫任任务
            CompletionService<AbilityDetailSpider.Data> completionService = new ExecutorCompletionService<>(super.executorService);
            for (Map.Entry<Integer, String> entry : dataIndex.entrySet()) {
                AbilityDetailSpider spider = new AbilityDetailSpider(entry.getKey(), entry.getValue());
                completionService.submit(spider);
            }

            // 2. 获取爬虫结果
            List<AbilityDetailSpider.Data> dataList = new LinkedList<>();
            for (int i = 0; i < dataIndex.size(); i++) {
                Future<AbilityDetailSpider.Data> future = completionService.take();
                AbilityDetailSpider.Data data = future.get();
                if (data != null) {
                    dataList.add(future.get());
                }
            }

            logger.info("saving data list into t_ability_detail...");
            final boolean result = this.saveDataList(dataList);
            logger.info("{} execute {}", this.getClass().getName(), result ? "success" : "failure");
        } catch (ExecutionException e) {
            logger.error("{} execute error", this.getClass().getName(), e);
        }
    }
}
