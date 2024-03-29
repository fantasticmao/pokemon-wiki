package cn.fantasticmao.pokemon.spider.task2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * AbstractTask2SpiderScheduler
 *
 * @author fantasticmao
 * @since 2018/8/13
 */
abstract class AbstractTask2SpiderScheduler<T extends AbstractTask2Spider.Data> {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected final ExecutorService executorService;

    protected AbstractTask2SpiderScheduler(ExecutorService executorService) {
        this.executorService = executorService;
    }

    /**
     * 从 Database 中获取 Key-Value 数据索引
     */
    protected abstract Map<Integer, String> getDataIndex();

    /**
     * 保存爬虫获取的数据至 Database 中
     */
    protected abstract boolean saveDataList(List<T> dataList);

    /**
     * 启动执行程序
     */
    protected abstract void start() throws InterruptedException;
}
