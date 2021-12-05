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
        final String sql = "SELECT id, nameZh FROM pw_ability";
        try (Connection connection = PokemonDataSource.INSTANCE.getConnection();
             PreparedStatement prep = connection.prepareStatement(sql);
             ResultSet resultSet = prep.executeQuery()) {
            while (resultSet.next()) {
                final int id = resultSet.getInt(1);
                final String name = resultSet.getString(2);
                dataMap.put(id, name);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.unmodifiableMap(dataMap);
    }

    @Override
    protected boolean saveDataList(List<AbilityDetailSpider.Data> dataList) {
        // 1. 排序数据
        dataList.sort(Comparator.comparingInt(AbilityDetailSpider.Data::getId));

        // 2. 保存数据
        final String sql = "INSERT INTO pw_ability_detail(nameZh, desc, effect, pokemons) VALUES (?, ?, ?, ?)";
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
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public void start() {
        try {
            logger.info("加载索引数据...");
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
                dataList.add(future.get());
            }

            logger.info("保存数据...");
            final boolean result = this.saveDataList(dataList);
            logger.info("{} {}", this.getClass().getName(), result ? "保存数据成功" : "保存数据失败");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error(e.getMessage(), e);
        } catch (ExecutionException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
