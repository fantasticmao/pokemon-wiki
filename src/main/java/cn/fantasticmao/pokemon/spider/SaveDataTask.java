package cn.fantasticmao.pokemon.spider;

import java.sql.Connection;
import java.util.List;

/**
 * SaveDataTask
 *
 * @author maodh
 * @since 2018/8/6
 */
public abstract class SaveDataTask<T extends AbstractSpider.Data> {
    protected final List<T> dataList;

    protected SaveDataTask(List<T> dataList) {
        this.dataList = dataList;
    }

    public abstract boolean save(Connection connection);
}