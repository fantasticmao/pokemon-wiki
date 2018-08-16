package cn.fantasticmao.pokemon.spider.task1;

import cn.fantasticmao.pokemon.spider.Config;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * AbstractTask1Spider
 *
 * @author maodh
 * @since 2018/7/29
 */
abstract class AbstractTask1Spider<T extends AbstractTask1Spider.Data> implements Runnable {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Config.Site site;
    private final CountDownLatch doneSignal;

    protected AbstractTask1Spider(Config.Site site, CountDownLatch doneSignal) {
        this.site = site;
        this.doneSignal = doneSignal;
    }

    @Override
    public void run() {
        logger.info("请求数据...");
        final Document document = requestData(site);

        logger.info("解析数据...");
        final List<T> dataList = parseData(document);

        logger.info("保存数据...");
        final boolean result = saveData(dataList);
        logger.info("{} {}", this.getClass().getName(), result ? "保存数据成功" : "保存数据失败");

        doneSignal.countDown();
    }

    /**
     * <code>org.jsoup.Jsoup#connect(String)</code> 请求数据
     */
    private Document requestData(Config.Site site) {
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
    protected abstract List<T> parseData(Document document);

    /**
     * 使用 JDBC 批处理模式保存数据
     */
    protected abstract boolean saveData(List<T> dataList);

    interface Data {

    }
}
