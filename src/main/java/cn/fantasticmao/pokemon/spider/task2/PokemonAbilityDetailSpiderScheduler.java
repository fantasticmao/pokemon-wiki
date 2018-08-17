package cn.fantasticmao.pokemon.spider.task2;

import cn.fantasticmao.pokemon.spider.PokemonDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * PokemonAbilityDetailSpiderScheduler
 *
 * @author maodh
 * @since 2018/8/13
 */
public class PokemonAbilityDetailSpiderScheduler extends AbstractTask2SpiderScheduler {

    @Override
    public Map<Integer, String> getDataIndex() {
        // 1. 查询数据
        final Set<String> dataSet = new HashSet<>();
        final String sql = "SELECT ability1 FROM pw_pokemon_ability WHERE ability1 != '' " +
                "UNION " +
                "SELECT ability2 FROM pw_pokemon_ability WHERE ability2 != '' " +
                "UNION " +
                "SELECT abilityHide FROM pw_pokemon_ability WHERE abilityHide != ''";
        try (Connection connection = PokemonDataSource.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                final String name = resultSet.getString(1);
                dataSet.add(name);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }

        // 2. 排序数据
        final List<String> dataList = dataSet.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        final TreeMap<Integer, String> dataMap = dataList.stream()
                .collect(Collectors.toMap(dataList::indexOf, Function.identity(), (e1, e2) -> e1, TreeMap::new));
        return Collections.unmodifiableMap(dataMap);
    }

    @Override
    protected boolean saveDataList(List dataList) {
        return false;
    }

    @Override
    public void start(ExecutorService executorService) {
    }
}
