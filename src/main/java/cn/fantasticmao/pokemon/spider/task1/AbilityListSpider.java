package cn.fantasticmao.pokemon.spider.task1;

import cn.fantasticmao.pokemon.spider.Config;
import org.jsoup.nodes.Document;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * AbilityListSpider
 *
 * @author maodh
 * @since 2018/8/26
 */
public class AbilityListSpider extends AbstractTask1Spider<AbilityListSpider.Data> {

    public AbilityListSpider(CountDownLatch doneSignal) {
        super(Config.Site.ABILITY_LIST, doneSignal);
    }

    @Override
    protected List<Data> parseData(Document document) {
        return null;
    }

    @Override
    protected boolean saveData(List<Data> dataList) {
        return false;
    }

    public class Data implements AbstractTask1Spider.Data {

    }
}
