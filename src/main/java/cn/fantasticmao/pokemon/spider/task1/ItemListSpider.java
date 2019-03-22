package cn.fantasticmao.pokemon.spider.task1;

import cn.fantasticmao.pokemon.spider.Config;
import cn.fantasticmao.pokemon.spider.PokemonDataSource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.nodes.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * ItemListSpider
 *
 * @author maomao
 * @since 2019-03-20
 */
public class ItemListSpider extends AbstractTask1Spider<ItemListSpider.Data> {

    public ItemListSpider(CountDownLatch doneSignal) {
        super(Config.Site.ITEM_LIST, doneSignal);
    }

    @Override
    protected List<ItemListSpider.Data> parseData(Document document) {
        List<ItemListSpider.Data> dataList = new LinkedList<>();
        dataList.addAll(getData1(document));
        dataList.addAll(getData2(document));
        dataList.addAll(getData3(document));
        dataList.addAll(getData4(document));
        dataList.addAll(getData5(document));
        // TODO 解析数据
        return Collections.unmodifiableList(dataList);
    }

    @Override
    protected boolean saveData(List<ItemListSpider.Data> dataList) {
        final int batchSize = 100;
        final String sql = "INSERT INTO pw_item(type, imgUrl, nameZh, nameJa, nameEn, `desc`, generation) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = PokemonDataSource.INSTANCE.getConnection();
             PreparedStatement prep = connection.prepareCall(sql)) {
            ItemListSpider.Data tmpData = null;
            for (int i = batchSize, j = 0; ; i += batchSize) {
                for (; j < i && j < dataList.size(); j++) {
                    tmpData = dataList.get(j);
                    prep.setString(1, tmpData.getType());
                    prep.setString(2, tmpData.getImgUrl());
                    prep.setString(3, tmpData.getNameZh());
                    prep.setString(4, tmpData.getNameJa());
                    prep.setString(5, tmpData.getNameEn());
                    prep.setString(6, tmpData.getDesc());
                    prep.setInt(7, tmpData.getGeneration());
                    prep.addBatch();
                }
                prep.executeBatch();
                if (j >= dataList.size()) {
                    connection.commit();
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Data implements AbstractTask1Spider.Data {
        private final String type;
        private final String imgUrl;
        private final String nameZh;
        private final String nameJa;
        private final String nameEn;
        private final String desc;
        private final int generation;
    }

    // 野外使用和其它类别道具
    private List<ItemListSpider.Data> getData1(Document document) {
        final String type = "野外使用和其它类别道具";
        final int generation = 0;
        return document.selectFirst("#野外使用和其它类别道具").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> {
                    String imgUrl = element.child(0).selectFirst("img").attr("data-url").replace("//media.52poke.com", "https://s1.52poke.wiki");
                    String nameZh = element.child(1).text();
                    String nameJa = element.child(2).text();
                    String nameEn = element.child(3).text();
                    String desc = element.child(4).text();
                    return new ItemListSpider.Data(type, imgUrl, nameZh, nameJa, nameEn, desc, generation);
                })
                .collect(Collectors.toList());
    }

    // 化石
    private List<ItemListSpider.Data> getData2(Document document) {
        final String type = "化石";
        final int generation = 0;
        return document.selectFirst("#化石").parent().nextElementSibling().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> {
                    String imgUrl = element.child(0).selectFirst("img").attr("data-url").replace("//media.52poke.com", "https://s1.52poke.wiki");
                    String nameZh = element.child(1).text();
                    String nameJa = element.child(2).text();
                    String nameEn = element.child(3).text();
                    String desc = element.child(4).text();
                    return new ItemListSpider.Data(type, imgUrl, nameZh, nameJa, nameEn, desc, generation);
                })
                .collect(Collectors.toList());
    }

    // 进化石
    private List<ItemListSpider.Data> getData3(Document document) {
        final String type = "进化石";
        final int generation = 0;
        return document.selectFirst("#进化石").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> {
                    String imgUrl = element.child(0).selectFirst("img").attr("data-url").replace("//media.52poke.com", "https://s1.52poke.wiki");
                    String nameZh = element.child(1).text();
                    String nameJa = element.child(2).text();
                    String nameEn = element.child(3).text();
                    String desc = element.child(4).text();
                    return new ItemListSpider.Data(type, imgUrl, nameZh, nameJa, nameEn, desc, generation);
                })
                .collect(Collectors.toList());
    }

    // 贵重道具
    private List<ItemListSpider.Data> getData4(Document document) {
        final String type = "贵重道具";
        final int generation = 0;
        return document.selectFirst("#贵重道具").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> {
                    String imgUrl = element.child(0).selectFirst("img").attr("data-url").replace("//media.52poke.com", "https://s1.52poke.wiki");
                    String nameZh = element.child(1).text();
                    String nameJa = element.child(2).text();
                    String nameEn = element.child(3).text();
                    String desc = element.child(4).text();
                    return new ItemListSpider.Data(type, imgUrl, nameZh, nameJa, nameEn, desc, generation);
                })
                .collect(Collectors.toList());
    }

    // 携带物品 - 第二世代起
    private List<ItemListSpider.Data> getData5(Document document) {
        final String type = "携带物品";
        final int generation = 2;
        Queue<String> rowSpanNameZhQueue = new LinkedList<>();
        Queue<String> rowSpanNameJaQueue = new LinkedList<>();
        Queue<String> rowSpanNameEnQueue = new LinkedList<>();
        Queue<String> rowSpanDescQueue = new LinkedList<>();
        return document.selectFirst("#第二世代起").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> {
                    String imgUrl = element.child(0).selectFirst("img").attr("data-url").replace("//media.52poke.com", "https://s1.52poke.wiki");

                    // 处理中文名称 rowspan 跨行问题
                    String nameZh;
                    if (rowSpanNameZhQueue.isEmpty()) {
                        nameZh = element.child(1).text();
                        if (element.child(1).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(1).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanNameZhQueue.offer(nameZh);
                            }
                        } else {
                            logger.error("处理中文名称 rowspan 跨行问题");
                        }
                    } else {
                        nameZh = rowSpanNameZhQueue.poll();
                    }

                    // 处理日文名称 rowspan 跨行问题
                    String nameJa;
                    if (rowSpanNameJaQueue.isEmpty()) {
                        nameJa = element.child(2).text();
                        if (element.child(2).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(2).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanNameJaQueue.offer(nameJa);
                            }
                        } else {
                            logger.error("处理日文名称 rowspan 跨行问题");
                        }
                    } else {
                        nameJa = rowSpanNameJaQueue.poll();
                    }

                    // 处理英文名称 rowspan 跨行问题
                    String nameEn;
                    if (rowSpanNameEnQueue.isEmpty()) {
                        nameEn = element.child(3).text();
                        if (element.child(3).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(3).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanNameEnQueue.offer(nameEn);
                            }
                        } else {
                            logger.error("处理英文名称 rowspan 跨行问题");
                        }
                    } else {
                        nameEn = rowSpanNameEnQueue.poll();
                    }

                    // 处理道具描述 rowspan 跨行问题
                    String desc;
                    if (rowSpanDescQueue.isEmpty()) {
                        desc = element.child(4).text();
                        if (element.child(4).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(4).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanDescQueue.offer(desc);
                            }
                        } else {
                            logger.error("处理道具描述 rowspan 跨行问题");
                        }
                    } else {
                        desc = rowSpanDescQueue.poll();
                    }
                    return new ItemListSpider.Data(type, imgUrl, nameZh, nameJa, nameEn, desc, generation);
                })
                .collect(Collectors.toList());
    }

    // 携带物品 - 第三世代起
    private List<ItemListSpider.Data> getData6(Document document) {
        final String type = "携带物品";
        final int generation = 3;
        return Collections.emptyList();
    }

    // 携带物品 - 第四世代起
    private List<ItemListSpider.Data> getData7(Document document) {
        final String type = "携带物品";
        final int generation = 4;
        return Collections.emptyList();
    }

    // 携带物品 - 第五世代起
    private List<ItemListSpider.Data> getData8(Document document) {
        final String type = "携带物品";
        final int generation = 5;
        return Collections.emptyList();
    }

    // 携带物品 - 第六世代起
    private List<ItemListSpider.Data> getData9(Document document) {
        final String type = "携带物品";
        final int generation = 6;
        return Collections.emptyList();
    }

    // 携带物品 - 第七世代起
    private List<ItemListSpider.Data> getData10(Document document) {
        final String type = "携带物品";
        final int generation = 7;
        return Collections.emptyList();
    }
}
