package cn.fantasticmao.pokemon.spider.task2;

import cn.fantasticmao.pokemon.spider.PokemonDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.*;

/**
 * MoveDetailSpiderScheduler
 *
 * @author fantasticmao
 * @since 2018/8/10
 */
public class MoveDetailSpiderScheduler extends AbstractTask2SpiderScheduler<MoveDetailSpider.Data> {

    public MoveDetailSpiderScheduler(ExecutorService executorService) {
        super(executorService);
    }

    @Override
    protected Map<Integer, String> getDataIndex() {
        final TreeMap<Integer, String> dataMap = new TreeMap<>();
        final String sql = "SELECT id, name_zh FROM t_move";
        try (Connection connection = PokemonDataSource.INSTANCE.getConnection();
             PreparedStatement prep = connection.prepareStatement(sql);
             ResultSet resultSet = prep.executeQuery()) {
            while (resultSet.next()) {
                final int id = resultSet.getInt(1);
                final String name = resultSet.getString(2);
                dataMap.put(id, name);
            }
        } catch (SQLException e) {
            logger.error("select from t_move error", e);
        }
        return Collections.unmodifiableMap(dataMap);
    }

    @Override
    protected boolean saveDataList(List<MoveDetailSpider.Data> dataList) {
        // 1. 排序数据
        dataList.sort(Comparator.comparingInt(MoveDetailSpider.Data::getId));

        // 2. 批量保存
        final int batchSize = 100;
        final String sql = "INSERT INTO t_move_detail(name_zh, desc, img_url, notes, scope, effect) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = PokemonDataSource.INSTANCE.getConnection();
             PreparedStatement prep = connection.prepareStatement(sql)) {
            MoveDetailSpider.Data tempData = null;
            for (int i = batchSize, j = 0; ; i += batchSize) {
                for (; j < i && j < dataList.size(); j++) {
                    tempData = dataList.get(j);
                    prep.setString(1, tempData.getNameZh());
                    prep.setString(2, tempData.getDesc());
                    prep.setString(3, tempData.getImgUrl());
                    prep.setString(4, tempData.getNotes());
                    prep.setString(5, tempData.getScope());
                    prep.setString(6, tempData.getEffect());
                    prep.addBatch();
                }
                prep.executeBatch();
                if (j >= dataList.size()) {
                    connection.commit();
                    return true;
                }
            }
        } catch (SQLException e) {
            logger.error("insert into t_move_detail", e);
            return false;
        }
    }

    @Override
    public void start() throws InterruptedException {
        try {
            logger.info("loading data index from t_move...");
            final Map<Integer, String> dataIndex = this.getDataIndex();

            // 1. 提交爬虫任任务
            CompletionService<MoveDetailSpider.Data> completionService = new ExecutorCompletionService<>(super.executorService);
            for (Map.Entry<Integer, String> entry : dataIndex.entrySet()) {
                MoveDetailSpider spider = new MoveDetailSpider(entry.getKey(), entry.getValue());
                completionService.submit(spider);
            }

            // 2. 获取爬虫结果
            List<MoveDetailSpider.Data> dataList = new LinkedList<>();
            for (int i = 0; i < dataIndex.size(); i++) {
                Future<MoveDetailSpider.Data> future = completionService.take();
                MoveDetailSpider.Data data = future.get();
                if (data != null) {
                    dataList.add(future.get());
                }
            }

            logger.info("saving data list into t_move_detail...");
            final boolean result = this.saveDataList(dataList);
            logger.info("{} execute {}", this.getClass().getName(), result ? "success" : "failure");
        } catch (ExecutionException e) {
            logger.error("{} execute error", this.getClass().getName(), e);
        }
    }
}
