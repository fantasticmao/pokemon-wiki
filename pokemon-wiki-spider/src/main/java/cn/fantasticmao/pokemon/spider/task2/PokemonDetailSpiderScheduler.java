package cn.fantasticmao.pokemon.spider.task2;

import cn.fantasticmao.pokemon.spider.PokemonDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.*;

/**
 * PokemonDetailSpiderScheduler
 *
 * @author fantasticmao
 * @since 2018/8/28
 */
public class PokemonDetailSpiderScheduler extends AbstractTask2SpiderScheduler<PokemonDetailSpider.Data> {

    public PokemonDetailSpiderScheduler(ExecutorService executorService) {
        super(executorService);
    }

    @Override
    protected Map<Integer/* 宝可梦全国图鉴编号 */, String/* 宝可梦中文名称 */> getDataIndex() {
        final TreeMap<Integer, String> dataMap = new TreeMap<>();
        final String sql = "SELECT idx, name_zh FROM t_pokemon";
        try (Connection connection = PokemonDataSource.INSTANCE.getConnection();
             PreparedStatement prep = connection.prepareStatement(sql);
             ResultSet resultSet = prep.executeQuery()) {
            while (resultSet.next()) {
                final int index = resultSet.getInt(1);
                final String name = resultSet.getString(2);
                dataMap.put(index, name);
            }
        } catch (SQLException e) {
            logger.error("select from t_pokemon error", e);
        }
        return Collections.unmodifiableMap(dataMap);
    }

    @Override
    protected boolean saveDataList(List<PokemonDetailSpider.Data> dataList) {
        // 1. 排序数据
        dataList.sort(Comparator.comparingInt(PokemonDetailSpider.Data::getIndex));

        final int batchSize = 100;
        // 2. 批量保存
        final String sqlPokemonDetail = "INSERT INTO t_pokemon_detail(idx, img_url, type, category, ability, height, weight, body_style, catch_rate, gender_ratio, egg_group1, egg_group2, hatch_time, effort_value) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        final String sqlPokemonDetailBaseStat = "INSERT INTO t_pokemon_detail_base_stat(idx, hp, attack, defense, sp_attack, sp_defense, speed, total, average) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        final String sqlPokemonDetailLearnSetByLevelingUp = "INSERT INTO t_pokemon_detail_learn_set_by_leveling_up(idx, level, move, type, category, power, accuracy, pp) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        final String sqlPokemonDetailLearnSetByTechnicalMachine = "INSERT INTO t_pokemon_detail_learn_set_by_technical_machine(idx, img_url, technical_machine, move, type, category, power, accuracy, pp) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        final String sqlPokemonDetailLearnSetByBreeding = "INSERT INTO t_pokemon_detail_learn_set_by_breeding(idx, parent, move, type, category, power, accuracy, pp) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = PokemonDataSource.INSTANCE.getConnection();
             PreparedStatement prepPokemonDetail = connection.prepareStatement(sqlPokemonDetail);
             PreparedStatement prepPokemonDetailBaseStat = connection.prepareStatement(sqlPokemonDetailBaseStat);
             PreparedStatement prepPokemonDetailLearnSetByLevelingUp = connection.prepareStatement(sqlPokemonDetailLearnSetByLevelingUp);
             PreparedStatement prepPokemonDetailLearnSetByTechnicalMachine = connection.prepareStatement(sqlPokemonDetailLearnSetByTechnicalMachine);
             PreparedStatement prepPokemonDetailLearnSetByBreeding = connection.prepareStatement(sqlPokemonDetailLearnSetByBreeding)) {
            PokemonDetailSpider.Data tempData = null;
            for (int i = batchSize, j = 0; ; i += batchSize) {
                for (; j < i && j < dataList.size(); j++) {
                    tempData = dataList.get(j);

                    // 基本信息
                    prepPokemonDetail.setInt(1, tempData.getIndex());
                    prepPokemonDetail.setString(2, tempData.getImgUrl());
                    prepPokemonDetail.setString(3, tempData.getType());
                    prepPokemonDetail.setString(4, tempData.getCategory());
                    prepPokemonDetail.setString(5, tempData.getAbility());
                    prepPokemonDetail.setString(6, tempData.getHeight());
                    prepPokemonDetail.setString(7, tempData.getWeight());
                    prepPokemonDetail.setString(8, tempData.getBodyStyle());
                    prepPokemonDetail.setString(9, tempData.getCatchRate());
                    prepPokemonDetail.setString(10, tempData.getGenderRatio());
                    prepPokemonDetail.setString(11, tempData.getEggGroup1());
                    prepPokemonDetail.setString(12, tempData.getEggGroup2());
                    prepPokemonDetail.setString(13, tempData.getHatchTime());
                    prepPokemonDetail.setString(14, tempData.getEffortValue());
                    prepPokemonDetail.addBatch();

                    // 种族值
                    PokemonDetailSpider.Data.BaseStat baseStat = tempData.getBaseStat();
                    prepPokemonDetailBaseStat.setInt(1, tempData.getIndex());
                    prepPokemonDetailBaseStat.setInt(2, baseStat.getHp());
                    prepPokemonDetailBaseStat.setInt(3, baseStat.getAttack());
                    prepPokemonDetailBaseStat.setInt(4, baseStat.getDefense());
                    prepPokemonDetailBaseStat.setInt(5, baseStat.getSpAttack());
                    prepPokemonDetailBaseStat.setInt(6, baseStat.getSpDefense());
                    prepPokemonDetailBaseStat.setInt(7, baseStat.getSpeed());
                    prepPokemonDetailBaseStat.setInt(8, baseStat.getTotal());
                    prepPokemonDetailBaseStat.setFloat(9, baseStat.getAverage());
                    prepPokemonDetailBaseStat.addBatch();

                    // 升级可学的招式
                    for (PokemonDetailSpider.Data.LearnSetByLevelingUp learnSetByLevelingUp : tempData.getLearnSetByLevelingUpList()) {
                        prepPokemonDetailLearnSetByLevelingUp.setInt(1, tempData.getIndex());
                        prepPokemonDetailLearnSetByLevelingUp.setString(2, learnSetByLevelingUp.getLevel());
                        prepPokemonDetailLearnSetByLevelingUp.setString(3, learnSetByLevelingUp.getMove());
                        prepPokemonDetailLearnSetByLevelingUp.setString(4, learnSetByLevelingUp.getType());
                        prepPokemonDetailLearnSetByLevelingUp.setString(5, learnSetByLevelingUp.getCategory());
                        prepPokemonDetailLearnSetByLevelingUp.setString(6, learnSetByLevelingUp.getPower());
                        prepPokemonDetailLearnSetByLevelingUp.setString(7, learnSetByLevelingUp.getAccuracy());
                        prepPokemonDetailLearnSetByLevelingUp.setString(8, learnSetByLevelingUp.getPp());
                        prepPokemonDetailLearnSetByLevelingUp.addBatch();
                    }

                    // 可使用的招式学习器
                    for (PokemonDetailSpider.Data.LearnSetByTechnicalMachine learnSetByTechnicalMachine : tempData.getLearnSetByTechnicalMachineList()) {
                        prepPokemonDetailLearnSetByTechnicalMachine.setInt(1, tempData.getIndex());
                        prepPokemonDetailLearnSetByTechnicalMachine.setString(2, learnSetByTechnicalMachine.getImgUrl());
                        prepPokemonDetailLearnSetByTechnicalMachine.setString(3, learnSetByTechnicalMachine.getTechnicalMachine());
                        prepPokemonDetailLearnSetByTechnicalMachine.setString(4, learnSetByTechnicalMachine.getMove());
                        prepPokemonDetailLearnSetByTechnicalMachine.setString(5, learnSetByTechnicalMachine.getType());
                        prepPokemonDetailLearnSetByTechnicalMachine.setString(6, learnSetByTechnicalMachine.getCategory());
                        prepPokemonDetailLearnSetByTechnicalMachine.setString(7, learnSetByTechnicalMachine.getPower());
                        prepPokemonDetailLearnSetByTechnicalMachine.setString(8, learnSetByTechnicalMachine.getAccuracy());
                        prepPokemonDetailLearnSetByTechnicalMachine.setString(9, learnSetByTechnicalMachine.getPp());
                        prepPokemonDetailLearnSetByTechnicalMachine.addBatch();
                    }

                    // 蛋招式
                    for (PokemonDetailSpider.Data.LearnSetByBreeding learnSetByBreeding : tempData.getLearnSetByBreedingList()) {
                        prepPokemonDetailLearnSetByBreeding.setInt(1, tempData.getIndex());
                        prepPokemonDetailLearnSetByBreeding.setString(2, learnSetByBreeding.getParent());
                        prepPokemonDetailLearnSetByBreeding.setString(3, learnSetByBreeding.getMove());
                        prepPokemonDetailLearnSetByBreeding.setString(4, learnSetByBreeding.getType());
                        prepPokemonDetailLearnSetByBreeding.setString(5, learnSetByBreeding.getCategory());
                        prepPokemonDetailLearnSetByBreeding.setString(6, learnSetByBreeding.getPower());
                        prepPokemonDetailLearnSetByBreeding.setString(7, learnSetByBreeding.getAccuracy());
                        prepPokemonDetailLearnSetByBreeding.setString(8, learnSetByBreeding.getPp());
                        prepPokemonDetailLearnSetByBreeding.addBatch();
                    }
                }
                prepPokemonDetail.executeBatch();
                prepPokemonDetailBaseStat.executeBatch();
                prepPokemonDetailLearnSetByLevelingUp.executeBatch();
                prepPokemonDetailLearnSetByTechnicalMachine.executeBatch();
                prepPokemonDetailLearnSetByBreeding.executeBatch();
                if (j >= dataList.size()) {
                    connection.commit();
                    return true;
                }
            }
        } catch (SQLException e) {
            logger.error("insert into t_pokemon_detail error", e);
            return false;
        }
    }

    @Override
    public void start() throws InterruptedException {
        try {
            logger.info("loading data index from t_pokemon...");
            final Map<Integer/* 宝可梦全国图鉴编号 */, String/* 宝可梦中文名称 */> dataIndex = this.getDataIndex();

            CompletionService<PokemonDetailSpider.Data> completionService = new ExecutorCompletionService<>(super.executorService);
            for (Map.Entry<Integer, String> entry : dataIndex.entrySet()) {
                PokemonDetailSpider spider = new PokemonDetailSpider(entry.getKey(), entry.getValue());
                completionService.submit(spider);
            }

            List<PokemonDetailSpider.Data> dataList = new LinkedList<>();
            for (int i = 0; i < dataIndex.size(); i++) {
                Future<PokemonDetailSpider.Data> future = completionService.take();
                PokemonDetailSpider.Data data = future.get();
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
