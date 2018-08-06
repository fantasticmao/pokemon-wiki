package cn.fantasticmao.pokemon.spider.task;

import java.sql.Connection;
import java.util.List;

/**
 * SaveDataTask
 *
 * @author maodh
 * @since 2018/8/6
 */
public abstract class SaveDataTask<T extends AbstractSpider.Data> {
    final List<T> dataList;

    SaveDataTask(List<T> dataList) {
        this.dataList = dataList;
    }

    public abstract boolean save(Connection connection);
}