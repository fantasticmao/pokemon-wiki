package cn.fantasticmao.pokemon.spider.task2;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.Callable;

/**
 * AbstractTask2Spider
 *
 * @author maodh
 * @since 2018/8/14
 */
abstract class AbstractTask2Spider<T extends AbstractTask2Spider.Data> implements Callable<T> {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String url;

    protected AbstractTask2Spider(String url) {
        this.url = url;
    }

    @Override
    public T call() throws Exception {
        logger.info("请求数据... {}", url);
        Document document = requestData(url);

        logger.info("解析数据... {}", url);
        return parseData(document);
    }

    /**
     * <code>org.jsoup.Jsoup#connect(String)</code> 请求数据
     */
    private Document requestData(String url) {
        for (; ; ) {
            try {
                return Jsoup.connect(url)
                    .maxBodySize(10 * 1024 * 1024)
                    .timeout(30_000)
                    .get();
            } catch (IOException e) {
                if (e instanceof HttpStatusException) {
                    logger.error("{} {}", e.getMessage(), url);
                    // 例如请求「https://wiki.52poke.com/zh-hans/究极无敌大冲撞（招式）」的 Status Code 是 404，
                    // 但 Response Body 却返回了真实数据，所以需要对其进行特殊处理
                    if (((HttpStatusException) e).getStatusCode() == 404) {
                        return null;
                    }
                } else if (e instanceof SocketTimeoutException) {
                    logger.info("请求超时，正在重试... " + url);
                } else {
                    logger.error("请求异常，正在重试... " + url, e);
                }
            }
        }
    }

    /**
     * <code>org.jsoup.nodes.Document#select(String)</code> 解析数据
     */
    protected abstract T parseData(Document document) throws Exception;

    interface Data {

    }
}
