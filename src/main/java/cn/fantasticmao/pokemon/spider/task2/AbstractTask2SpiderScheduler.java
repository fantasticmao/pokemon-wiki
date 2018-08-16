package cn.fantasticmao.pokemon.spider.task2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * AbstractTask2SpiderScheduler
 *
 * @author maodh
 * @since 2018/8/13
 */
abstract class AbstractTask2SpiderScheduler<T extends AbstractTask2Spider.Data> {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 从 MySQL 中获取 Key-Value 数据索引
     */
    protected abstract Map<Integer, String> getDataIndex() throws SQLException;

    /**
     * 保存爬虫获取的数据至 MySQL 中
     */
    protected abstract boolean saveDataList(List<T> dataList) throws SQLException;

    /**
     * 启动执行程序
     *
     * @param executorService 调度爬虫线程的线程池
     */
    protected abstract void start(ExecutorService executorService);
}
