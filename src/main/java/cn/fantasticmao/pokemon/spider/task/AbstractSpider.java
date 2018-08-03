package cn.fantasticmao.pokemon.spider.task;

import cn.fantasticmao.pokemon.spider.Config;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 * AbstractSpider
 *
 * @author maodh
 * @since 2018/7/29
 */
abstract class AbstractSpider<T extends AbstractSpider.Data> implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Config.Site site;

    AbstractSpider(Config.Site site) {
        this.site = site;
    }

    @Override
    public void run() {
        logger.info("请求数据...");
        final Document document = requestData(site);

        logger.info("解析数据...");
        final List<T> data = parseData(document);

        // TODO 分解为 生产者-消费者 模式
        logger.info("保存数据...");
        logger.info(saveData(Config.ConnectionHolder.INSTANCE.connection, data) ? "保存成功！" : "保存失败！");
    }

    /**
     * <code>org.jsoup.Jsoup#parse(URL, int)</code> 请求数据
     */
    Document requestData(Config.Site site) {
        try {
            return Jsoup.connect(site.url)
                    .maxBodySize(10 * 1024 * 1024)
                    .timeout(30_000)
                    .get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <code>org.jsoup.nodes.Document#select(String)</code> 解析数据
     */
    abstract List<T> parseData(Document document);

    /**
     * 使用 JDBC 批处理模式保存数据
     */
    abstract boolean saveData(Connection connection, List<T> dataList);

    interface Data {

    }
}
