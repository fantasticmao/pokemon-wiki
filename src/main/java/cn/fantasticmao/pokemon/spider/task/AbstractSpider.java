package cn.fantasticmao.pokemon.spider.task;

import cn.fantasticmao.pokemon.spider.Config;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * AbstractSpider
 *
 * @author maodh
 * @since 2018/7/29
 */
abstract class AbstractSpider<T extends AbstractSpider.Data> implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Config.Site site;
    private final BlockingQueue<SaveDataTask> queue;

    AbstractSpider(Config.Site site, BlockingQueue<SaveDataTask> queue) {
        this.site = site;
        this.queue = queue;
    }

    @Override
    public void run() {
        logger.info("请求数据...");
        final Document document = requestData(site);

        logger.info("解析数据...");
        final List<T> dataList = parseData(document);

        logger.info("任务入队，等待保存...");
        SaveDataTask<T> task = newTask(dataList);
        try {
            queue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * <code>org.jsoup.Jsoup#parse(URL, int)</code> 请求数据
     */
    protected Document requestData(Config.Site site) {
        for (; ; ) {
            try {
                return Jsoup.connect(site.url)
                        .maxBodySize(10 * 1024 * 1024)
                        .timeout(10_000)
                        .get();
            } catch (IOException e) {
                if (e instanceof SocketTimeoutException) {
                    logger.info("请求超时，正在重试...");
                } else {
                    logger.info("请求异常，正在重试...");
                }
            }
        }
    }

    /**
     * <code>org.jsoup.nodes.Document#select(String)</code> 解析数据
     */
    abstract List<T> parseData(Document document);

    /**
     * 使用 JDBC 批处理模式保存数据
     */
    abstract SaveDataTask<T> newTask(List<T> dataList);

    interface Data {

    }
}
