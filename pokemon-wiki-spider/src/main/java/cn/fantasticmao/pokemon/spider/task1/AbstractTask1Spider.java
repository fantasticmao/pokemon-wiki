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
 * @author fantasticmao
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
        logger.info("request data... {}", site.url);
        final Document document = requestData(site);

        logger.info("parse data...");
        final List<T> dataList = parseData(document);

        logger.info("save data...");
        final boolean result = saveData(dataList);
        logger.info("{} execute {}", this.getClass().getName(), result ? "success" : "failure");

        doneSignal.countDown();
    }

    /**
     * 请求数据
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
                    logger.info("request timeout, retrying... {}", site.url);
                } else {
                    logger.info("request error, retrying... {}", site.url);
                }
            }
        }
    }

    /**
     * 解析数据
     */
    protected abstract List<T> parseData(Document document);

    /**
     * 保存数据
     */
    protected abstract boolean saveData(List<T> dataList);

    interface Data {

    }
}
