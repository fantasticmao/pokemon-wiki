package cn.fantasticmao.pokemon.spider.task1;

import cn.fantasticmao.pokemon.spider.Config;
import cn.fantasticmao.pokemon.spider.PokemonDataSource;
import com.mundo.core.support.Constant;
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
        dataList.addAll(getData6(document));
        dataList.addAll(getData7(document));
        dataList.addAll(getData8(document));
        dataList.addAll(getData9(document));
        dataList.addAll(getData10(document));
        dataList.addAll(getData11(document));
        dataList.addAll(getData12(document));
        dataList.addAll(getData13(document));
        dataList.addAll(getData14(document));
        dataList.addAll(getData15(document));
        dataList.addAll(getData16(document));
        dataList.addAll(getData17(document));
        dataList.addAll(getData18(document));
        dataList.addAll(getData19(document));
        dataList.addAll(getData20(document));
        dataList.addAll(getData21(document));
        dataList.addAll(getData22(document));
        dataList.addAll(getData23(document));
        dataList.addAll(getData24(document));
        dataList.addAll(getData25(document));
        dataList.addAll(getData26(document));
        dataList.addAll(getData27(document));
        dataList.addAll(getData28(document));
        dataList.addAll(getData29(document));
        dataList.addAll(getData30(document));
        dataList.addAll(getData31(document));
        dataList.addAll(getData32(document));
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
        Queue<String> rowSpanImgUrlQueue = new LinkedList<>();
        Queue<String> rowSpanNameZhQueue = new LinkedList<>();
        Queue<String> rowSpanNameJaQueue = new LinkedList<>();
        Queue<String> rowSpanNameEnQueue = new LinkedList<>();
        Queue<String> rowSpanDescQueue = new LinkedList<>();
        return document.selectFirst("#野外使用和其它类别道具").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> {
                    int offset = 0;
                    String imgUrl;
                    if (rowSpanImgUrlQueue.isEmpty()) {
                        imgUrl = element.child(0).selectFirst("img").attr("data-url").replace("//media.52poke.com", "https://s1.52poke.wiki");
                        if (element.child(0).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(0).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanImgUrlQueue.offer(imgUrl);
                            }
                        }
                        offset++;
                    } else {
                        imgUrl = rowSpanImgUrlQueue.poll();
                    }

                    // 处理中文名称 rowspan 跨行问题
                    String nameZh;
                    if (rowSpanNameZhQueue.isEmpty()) {
                        nameZh = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanNameZhQueue.offer(nameZh);
                            }
                        }
                        offset++;
                    } else {
                        nameZh = rowSpanNameZhQueue.poll();
                    }

                    // 处理日文名称 rowspan 跨行问题
                    String nameJa;
                    if (rowSpanNameJaQueue.isEmpty()) {
                        nameJa = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanNameJaQueue.offer(nameJa);
                            }
                        }
                        offset++;
                    } else {
                        nameJa = rowSpanNameJaQueue.poll();
                    }

                    // 处理英文名称 rowspan 跨行问题
                    String nameEn;
                    if (rowSpanNameEnQueue.isEmpty()) {
                        nameEn = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanNameEnQueue.offer(nameEn);
                            }
                        }
                        offset++;
                    } else {
                        nameEn = rowSpanNameEnQueue.poll();
                    }

                    // 处理道具描述 rowspan 跨行问题
                    String desc;
                    if (rowSpanDescQueue.isEmpty()) {
                        desc = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanDescQueue.offer(desc);
                            }
                        }
                    } else {
                        desc = rowSpanDescQueue.poll();
                    }
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
        return document.selectFirst("#第三世代起").parent().nextElementSibling().select("tbody > tr").stream()
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

    // 携带物品 - 第四世代起
    private List<ItemListSpider.Data> getData7(Document document) {
        final String type = "携带物品";
        final int generation = 4;
        return document.selectFirst("#第四世代起").parent().nextElementSibling().select("tbody > tr").stream()
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

    // 携带物品 - 第五世代起
    private List<ItemListSpider.Data> getData8(Document document) {
        final String type = "携带物品";
        final int generation = 5;
        return document.selectFirst("#第五世代起").parent().nextElementSibling().select("tbody > tr").stream()
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

    // 携带物品 - 第六世代起
    private List<ItemListSpider.Data> getData9(Document document) {
        final String type = "携带物品";
        final int generation = 6;
        return document.selectFirst("#第六世代起").parent().nextElementSibling().select("tbody > tr").stream()
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

    // 携带物品 - 第七世代起
    private List<ItemListSpider.Data> getData10(Document document) {
        final String type = "携带物品";
        final int generation = 7;
        return document.selectFirst("#第七世代起").parent().nextElementSibling().select("tbody > tr").stream()
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

    // 对战道具
    private List<ItemListSpider.Data> getData11(Document document) {
        final String type = "对战道具";
        final int generation = 0;
        return document.selectFirst("#对战道具").parent().nextElementSibling().select("tbody > tr").stream()
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

    // 精灵球
    private List<ItemListSpider.Data> getData12(Document document) {
        final String type = "精灵球";
        final int generation = 0;
        Queue<String> rowSpanNameZhQueue = new LinkedList<>();
        Queue<String> rowSpanNameJaQueue = new LinkedList<>();
        Queue<String> rowSpanNameEnQueue = new LinkedList<>();
        Queue<String> rowSpanDescQueue = new LinkedList<>();
        return document.selectFirst("#精灵球").parent().nextElementSibling().select("tbody > tr").stream()
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
                        }
                    } else {
                        desc = rowSpanDescQueue.poll();
                    }
                    return new ItemListSpider.Data(type, imgUrl, nameZh, nameJa, nameEn, desc, generation);
                })
                .collect(Collectors.toList());
    }

    // 球果
    private List<ItemListSpider.Data> getData13(Document document) {
        final String type = "球果";
        final int generation = 0;
        return document.selectFirst("#球果").parent().nextElementSibling().select("tbody > tr").stream()
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

    // 邮件 - 第二世代
    private List<ItemListSpider.Data> getData14(Document document) {
        final String type = "邮件";
        final int generation = 2;
        return document.selectFirst("#第二世代").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> {
                    String imgUrl = Constant.Strings.EMPTY;
                    String nameZh = element.child(0).text();
                    String nameJa = element.child(1).text();
                    String nameEn = element.child(2).text();
                    String desc = element.child(3).text();
                    return new ItemListSpider.Data(type, imgUrl, nameZh, nameJa, nameEn, desc, generation);
                })
                .collect(Collectors.toList());
    }

    // 邮件 - 第三世代
    private List<ItemListSpider.Data> getData15(Document document) {
        final String type = "邮件";
        final int generation = 3;
        return document.selectFirst("#第三世代").parent().nextElementSibling().select("tbody > tr").stream()
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

    // 邮件 - 第四世代
    private List<ItemListSpider.Data> getData16(Document document) {
        final String type = "邮件";
        final int generation = 4;
        return document.selectFirst("#第四世代").parent().nextElementSibling().select("tbody > tr").stream()
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

    // 邮件 - 第五世代
    private List<ItemListSpider.Data> getData17(Document document) {
        final String type = "邮件";
        final int generation = 5;
        return document.selectFirst("#第五世代").parent().nextElementSibling().select("tbody > tr").stream()
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

    // 回复道具
    private List<ItemListSpider.Data> getData18(Document document) {
        final String type = "回复道具";
        final int generation = 0;
        Queue<String> rowSpanNameZhQueue = new LinkedList<>();
        Queue<String> rowSpanNameJaQueue = new LinkedList<>();
        Queue<String> rowSpanNameEnQueue = new LinkedList<>();
        Queue<String> rowSpanDescQueue = new LinkedList<>();
        return document.selectFirst("#回复道具").parent().nextElementSibling().select("tbody > tr").stream()
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
                        }
                    } else {
                        desc = rowSpanDescQueue.poll();
                    }
                    return new ItemListSpider.Data(type, imgUrl, nameZh, nameJa, nameEn, desc, generation);
                })
                .collect(Collectors.toList());
    }

    // Z纯晶 - 训练家使用
    private List<ItemListSpider.Data> getData19(Document document) {
        final String type = "Z纯晶-训练家使用";
        final int generation = 0;
        return document.selectFirst("#训练家使用的Ｚ纯晶").parent().nextElementSibling().select("tbody > tr").stream()
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

    // Z纯晶 - 宝可梦使用
    private List<ItemListSpider.Data> getData20(Document document) {
        final String type = "Z纯晶-宝可梦使用";
        final int generation = 0;
        return document.selectFirst("#宝可梦使用的Ｚ纯晶").parent().nextElementSibling().select("tbody > tr").stream()
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

    // 树果 - 第二世代
    private List<ItemListSpider.Data> getData21(Document document) {
        final String type = "树果";
        final int generation = 2;
        return document.getElementById("果实（第二世代）").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> {
                    String imgUrl = Constant.Strings.EMPTY;
                    String nameZh = element.child(0).text();
                    String nameJa = element.child(1).text();
                    String nameEn = element.child(2).text();
                    String desc = element.child(3).text();
                    return new ItemListSpider.Data(type, imgUrl, nameZh, nameJa, nameEn, desc, generation);
                })
                .collect(Collectors.toList());
    }

    // 树果 - 第三世代
    private List<ItemListSpider.Data> getData22(Document document) {
        final String type = "树果";
        final int generation = 3;
        return document.selectFirst("#第三世代起_2").parent().nextElementSibling().select("tbody > tr").stream()
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

    // 树果 - 第四世代
    private List<ItemListSpider.Data> getData23(Document document) {
        final String type = "树果";
        final int generation = 4;
        return document.selectFirst("#第四世代起_2").parent().nextElementSibling().select("tbody > tr").stream()
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

    // 树果 - 第六世代
    private List<ItemListSpider.Data> getData24(Document document) {
        final String type = "树果";
        final int generation = 6;
        return document.selectFirst("#第六世代起_2").parent().nextElementSibling().select("tbody > tr").stream()
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

    // 重要物品 - 第一世代
    private List<ItemListSpider.Data> getData25(Document document) {
        final String type = "重要物品";
        final int generation = 1;
        Queue<String> rowSpanImgUrlQueue = new LinkedList<>();
        Queue<String> rowSpanNameZhQueue = new LinkedList<>();
        Queue<String> rowSpanNameJaQueue = new LinkedList<>();
        Queue<String> rowSpanNameEnQueue = new LinkedList<>();
        Queue<String> rowSpanDescQueue = new LinkedList<>();
        return document.selectFirst("#第一世代起").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> {
                    int offset = 0;
                    String imgUrl;
                    if (rowSpanImgUrlQueue.isEmpty()) {
                        imgUrl = element.child(0).selectFirst("img").attr("data-url").replace("//media.52poke.com", "https://s1.52poke.wiki");
                        if (element.child(0).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(0).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanImgUrlQueue.offer(imgUrl);
                            }
                        }
                        offset++;
                    } else {
                        imgUrl = rowSpanImgUrlQueue.poll();
                    }

                    // 处理中文名称 rowspan 跨行问题
                    String nameZh;
                    if (rowSpanNameZhQueue.isEmpty()) {
                        nameZh = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanNameZhQueue.offer(nameZh);
                            }
                        }
                        offset++;
                    } else {
                        nameZh = rowSpanNameZhQueue.poll();
                    }

                    // 处理日文名称 rowspan 跨行问题
                    String nameJa;
                    if (rowSpanNameJaQueue.isEmpty()) {
                        nameJa = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanNameJaQueue.offer(nameJa);
                            }
                        }
                        offset++;
                    } else {
                        nameJa = rowSpanNameJaQueue.poll();
                    }

                    // 处理英文名称 rowspan 跨行问题
                    String nameEn;
                    if (rowSpanNameEnQueue.isEmpty()) {
                        nameEn = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanNameEnQueue.offer(nameEn);
                            }
                        }
                        offset++;
                    } else {
                        nameEn = rowSpanNameEnQueue.poll();
                    }

                    // 处理道具描述 rowspan 跨行问题
                    String desc;
                    if (rowSpanDescQueue.isEmpty()) {
                        desc = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanDescQueue.offer(desc);
                            }
                        }
                    } else {
                        desc = rowSpanDescQueue.poll();
                    }
                    return new ItemListSpider.Data(type, imgUrl, nameZh, nameJa, nameEn, desc, generation);
                })
                .collect(Collectors.toList());
    }

    // 重要物品 - 第二世代
    private List<ItemListSpider.Data> getData26(Document document) {
        final String type = "重要物品";
        final int generation = 2;
        Queue<String> rowSpanImgUrlQueue = new LinkedList<>();
        Queue<String> rowSpanNameZhQueue = new LinkedList<>();
        Queue<String> rowSpanNameJaQueue = new LinkedList<>();
        Queue<String> rowSpanNameEnQueue = new LinkedList<>();
        Queue<String> rowSpanDescQueue = new LinkedList<>();
        return document.selectFirst("#第二世代起_2").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> {
                    int offset = 0;
                    String imgUrl;
                    if (rowSpanImgUrlQueue.isEmpty()) {
                        imgUrl = element.child(0).selectFirst("img").attr("data-url").replace("//media.52poke.com", "https://s1.52poke.wiki");
                        if (element.child(0).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(0).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanImgUrlQueue.offer(imgUrl);
                            }
                        }
                        offset++;
                    } else {
                        imgUrl = rowSpanImgUrlQueue.poll();
                    }

                    // 处理中文名称 rowspan 跨行问题
                    String nameZh;
                    if (rowSpanNameZhQueue.isEmpty()) {
                        nameZh = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanNameZhQueue.offer(nameZh);
                            }
                        }
                        offset++;
                    } else {
                        nameZh = rowSpanNameZhQueue.poll();
                    }

                    // 处理日文名称 rowspan 跨行问题
                    String nameJa;
                    if (rowSpanNameJaQueue.isEmpty()) {
                        nameJa = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanNameJaQueue.offer(nameJa);
                            }
                        }
                        offset++;
                    } else {
                        nameJa = rowSpanNameJaQueue.poll();
                    }

                    // 处理英文名称 rowspan 跨行问题
                    String nameEn;
                    if (rowSpanNameEnQueue.isEmpty()) {
                        nameEn = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanNameEnQueue.offer(nameEn);
                            }
                        }
                        offset++;
                    } else {
                        nameEn = rowSpanNameEnQueue.poll();
                    }

                    // 处理道具描述 rowspan 跨行问题
                    String desc;
                    if (rowSpanDescQueue.isEmpty()) {
                        desc = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanDescQueue.offer(desc);
                            }
                        }
                    } else {
                        desc = rowSpanDescQueue.poll();
                    }
                    return new ItemListSpider.Data(type, imgUrl, nameZh, nameJa, nameEn, desc, generation);
                })
                .collect(Collectors.toList());
    }

    // 重要物品 - 第三世代
    private List<ItemListSpider.Data> getData27(Document document) {
        final String type = "重要物品";
        final int generation = 3;
        Queue<String> rowSpanImgUrlQueue = new LinkedList<>();
        Queue<String> rowSpanNameZhQueue = new LinkedList<>();
        Queue<String> rowSpanNameJaQueue = new LinkedList<>();
        Queue<String> rowSpanNameEnQueue = new LinkedList<>();
        Queue<String> rowSpanDescQueue = new LinkedList<>();
        return document.selectFirst("#第三世代起_3").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> {
                    int offset = 0;
                    String imgUrl;
                    if (rowSpanImgUrlQueue.isEmpty()) {
                        imgUrl = element.child(0).selectFirst("img").attr("data-url").replace("//media.52poke.com", "https://s1.52poke.wiki");
                        if (element.child(0).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(0).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanImgUrlQueue.offer(imgUrl);
                            }
                        }
                        offset++;
                    } else {
                        imgUrl = rowSpanImgUrlQueue.poll();
                    }

                    // 处理中文名称 rowspan 跨行问题
                    String nameZh;
                    if (rowSpanNameZhQueue.isEmpty()) {
                        nameZh = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanNameZhQueue.offer(nameZh);
                            }
                        }
                        offset++;
                    } else {
                        nameZh = rowSpanNameZhQueue.poll();
                    }

                    // 处理日文名称 rowspan 跨行问题
                    String nameJa;
                    if (rowSpanNameJaQueue.isEmpty()) {
                        nameJa = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanNameJaQueue.offer(nameJa);
                            }
                        }
                        offset++;
                    } else {
                        nameJa = rowSpanNameJaQueue.poll();
                    }

                    // 处理英文名称 rowspan 跨行问题
                    String nameEn;
                    if (rowSpanNameEnQueue.isEmpty()) {
                        nameEn = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanNameEnQueue.offer(nameEn);
                            }
                        }
                        offset++;
                    } else {
                        nameEn = rowSpanNameEnQueue.poll();
                    }

                    // 处理道具描述 rowspan 跨行问题
                    String desc;
                    if (rowSpanDescQueue.isEmpty()) {
                        desc = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanDescQueue.offer(desc);
                            }
                        }
                    } else {
                        desc = rowSpanDescQueue.poll();
                    }
                    return new ItemListSpider.Data(type, imgUrl, nameZh, nameJa, nameEn, desc, generation);
                })
                .collect(Collectors.toList());
    }

    // 重要物品 - 第四世代
    private List<ItemListSpider.Data> getData28(Document document) {
        final String type = "重要物品";
        final int generation = 4;
        Queue<String> rowSpanImgUrlQueue = new LinkedList<>();
        Queue<String> rowSpanNameZhQueue = new LinkedList<>();
        Queue<String> rowSpanNameJaQueue = new LinkedList<>();
        Queue<String> rowSpanNameEnQueue = new LinkedList<>();
        Queue<String> rowSpanDescQueue = new LinkedList<>();
        return document.selectFirst("#第四世代起_3").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> {
                    int offset = 0;
                    String imgUrl;
                    if (rowSpanImgUrlQueue.isEmpty()) {
                        imgUrl = element.child(0).selectFirst("img").attr("data-url").replace("//media.52poke.com", "https://s1.52poke.wiki");
                        if (element.child(0).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(0).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanImgUrlQueue.offer(imgUrl);
                            }
                        }
                        offset++;
                    } else {
                        imgUrl = rowSpanImgUrlQueue.poll();
                    }

                    // 处理中文名称 rowspan 跨行问题
                    String nameZh;
                    if (rowSpanNameZhQueue.isEmpty()) {
                        nameZh = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanNameZhQueue.offer(nameZh);
                            }
                        }
                        offset++;
                    } else {
                        nameZh = rowSpanNameZhQueue.poll();
                    }

                    // 处理日文名称 rowspan 跨行问题
                    String nameJa;
                    if (rowSpanNameJaQueue.isEmpty()) {
                        nameJa = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanNameJaQueue.offer(nameJa);
                            }
                        }
                        offset++;
                    } else {
                        nameJa = rowSpanNameJaQueue.poll();
                    }

                    // 处理英文名称 rowspan 跨行问题
                    String nameEn;
                    if (rowSpanNameEnQueue.isEmpty()) {
                        nameEn = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanNameEnQueue.offer(nameEn);
                            }
                        }
                        offset++;
                    } else {
                        nameEn = rowSpanNameEnQueue.poll();
                    }

                    // 处理道具描述 rowspan 跨行问题
                    String desc;
                    if (rowSpanDescQueue.isEmpty()) {
                        desc = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanDescQueue.offer(desc);
                            }
                        }
                    } else {
                        desc = rowSpanDescQueue.poll();
                    }
                    return new ItemListSpider.Data(type, imgUrl, nameZh, nameJa, nameEn, desc, generation);
                })
                .collect(Collectors.toList());
    }

    // 重要物品 - 第五世代
    private List<ItemListSpider.Data> getData29(Document document) {
        final String type = "重要物品";
        final int generation = 5;
        Queue<String> rowSpanImgUrlQueue = new LinkedList<>();
        Queue<String> rowSpanNameZhQueue = new LinkedList<>();
        Queue<String> rowSpanNameJaQueue = new LinkedList<>();
        Queue<String> rowSpanNameEnQueue = new LinkedList<>();
        Queue<String> rowSpanDescQueue = new LinkedList<>();
        return document.selectFirst("#第五世代起_2").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> {
                    int offset = 0;
                    String imgUrl;
                    if (rowSpanImgUrlQueue.isEmpty()) {
                        imgUrl = element.child(0).selectFirst("img").attr("data-url").replace("//media.52poke.com", "https://s1.52poke.wiki");
                        if (element.child(0).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(0).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanImgUrlQueue.offer(imgUrl);
                            }
                        }
                        offset++;
                    } else {
                        imgUrl = rowSpanImgUrlQueue.poll();
                    }

                    // 处理中文名称 rowspan 跨行问题
                    String nameZh;
                    if (rowSpanNameZhQueue.isEmpty()) {
                        nameZh = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanNameZhQueue.offer(nameZh);
                            }
                        }
                        offset++;
                    } else {
                        nameZh = rowSpanNameZhQueue.poll();
                    }

                    // 处理日文名称 rowspan 跨行问题
                    String nameJa;
                    if (rowSpanNameJaQueue.isEmpty()) {
                        nameJa = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanNameJaQueue.offer(nameJa);
                            }
                        }
                        offset++;
                    } else {
                        nameJa = rowSpanNameJaQueue.poll();
                    }

                    // 处理英文名称 rowspan 跨行问题
                    String nameEn;
                    if (rowSpanNameEnQueue.isEmpty()) {
                        nameEn = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanNameEnQueue.offer(nameEn);
                            }
                        }
                        offset++;
                    } else {
                        nameEn = rowSpanNameEnQueue.poll();
                    }

                    // 处理道具描述 rowspan 跨行问题
                    String desc;
                    if (rowSpanDescQueue.isEmpty()) {
                        desc = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanDescQueue.offer(desc);
                            }
                        }
                    } else {
                        desc = rowSpanDescQueue.poll();
                    }
                    return new ItemListSpider.Data(type, imgUrl, nameZh, nameJa, nameEn, desc, generation);
                })
                .collect(Collectors.toList());
    }

    // 重要物品 - 第六世代
    private List<ItemListSpider.Data> getData30(Document document) {
        final String type = "重要物品";
        final int generation = 6;
        Queue<String> rowSpanImgUrlQueue = new LinkedList<>();
        Queue<String> rowSpanNameZhQueue = new LinkedList<>();
        Queue<String> rowSpanNameJaQueue = new LinkedList<>();
        Queue<String> rowSpanNameEnQueue = new LinkedList<>();
        Queue<String> rowSpanDescQueue = new LinkedList<>();
        return document.selectFirst("#第六世代起_3").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> {
                    int offset = 0;
                    String imgUrl;
                    if (rowSpanImgUrlQueue.isEmpty()) {
                        imgUrl = element.child(0).selectFirst("img").attr("data-url").replace("//media.52poke.com", "https://s1.52poke.wiki");
                        if (element.child(0).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(0).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanImgUrlQueue.offer(imgUrl);
                            }
                        }
                        offset++;
                    } else {
                        imgUrl = rowSpanImgUrlQueue.poll();
                    }

                    // 处理中文名称 rowspan 跨行问题
                    String nameZh;
                    if (rowSpanNameZhQueue.isEmpty()) {
                        nameZh = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanNameZhQueue.offer(nameZh);
                            }
                        }
                        offset++;
                    } else {
                        nameZh = rowSpanNameZhQueue.poll();
                    }

                    // 处理日文名称 rowspan 跨行问题
                    String nameJa;
                    if (rowSpanNameJaQueue.isEmpty()) {
                        nameJa = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanNameJaQueue.offer(nameJa);
                            }
                        }
                        offset++;
                    } else {
                        nameJa = rowSpanNameJaQueue.poll();
                    }

                    // 处理英文名称 rowspan 跨行问题
                    String nameEn;
                    if (rowSpanNameEnQueue.isEmpty()) {
                        nameEn = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanNameEnQueue.offer(nameEn);
                            }
                        }
                        offset++;
                    } else {
                        nameEn = rowSpanNameEnQueue.poll();
                    }

                    // 处理道具描述 rowspan 跨行问题
                    String desc;
                    if (rowSpanDescQueue.isEmpty()) {
                        desc = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanDescQueue.offer(desc);
                            }
                        }
                    } else {
                        desc = rowSpanDescQueue.poll();
                    }
                    return new ItemListSpider.Data(type, imgUrl, nameZh, nameJa, nameEn, desc, generation);
                })
                .collect(Collectors.toList());
    }

    // 重要物品 - 第七世代
    private List<ItemListSpider.Data> getData31(Document document) {
        final String type = "重要物品";
        final int generation = 7;
        Queue<String> rowSpanImgUrlQueue = new LinkedList<>();
        Queue<String> rowSpanNameZhQueue = new LinkedList<>();
        Queue<String> rowSpanNameJaQueue = new LinkedList<>();
        Queue<String> rowSpanNameEnQueue = new LinkedList<>();
        Queue<String> rowSpanDescQueue = new LinkedList<>();
        return document.selectFirst("#第七世代起_2").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> {
                    int offset = 0;
                    String imgUrl;
                    if (rowSpanImgUrlQueue.isEmpty()) {
                        imgUrl = element.child(0).selectFirst("img").attr("data-url").replace("//media.52poke.com", "https://s1.52poke.wiki");
                        if (element.child(0).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(0).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanImgUrlQueue.offer(imgUrl);
                            }
                        }
                        offset++;
                    } else {
                        imgUrl = rowSpanImgUrlQueue.poll();
                    }

                    // 处理中文名称 rowspan 跨行问题
                    String nameZh;
                    if (rowSpanNameZhQueue.isEmpty()) {
                        nameZh = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanNameZhQueue.offer(nameZh);
                            }
                        }
                        offset++;
                    } else {
                        nameZh = rowSpanNameZhQueue.poll();
                    }

                    // 处理日文名称 rowspan 跨行问题
                    String nameJa;
                    if (rowSpanNameJaQueue.isEmpty()) {
                        nameJa = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanNameJaQueue.offer(nameJa);
                            }
                        }
                        offset++;
                    } else {
                        nameJa = rowSpanNameJaQueue.poll();
                    }

                    // 处理英文名称 rowspan 跨行问题
                    String nameEn;
                    if (rowSpanNameEnQueue.isEmpty()) {
                        nameEn = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanNameEnQueue.offer(nameEn);
                            }
                        }
                        offset++;
                    } else {
                        nameEn = rowSpanNameEnQueue.poll();
                    }

                    // 处理道具描述 rowspan 跨行问题
                    String desc;
                    if (rowSpanDescQueue.isEmpty()) {
                        desc = element.child(offset).text();
                        if (element.child(offset).hasAttr("rowspan")) {
                            int rowCount = Integer.valueOf(element.child(offset).attr("rowspan")) - 1;
                            for (int i = 0; i < rowCount; i++) {
                                rowSpanDescQueue.offer(desc);
                            }
                        }
                    } else {
                        desc = rowSpanDescQueue.poll();
                    }
                    return new ItemListSpider.Data(type, imgUrl, nameZh, nameJa, nameEn, desc, generation);
                })
                .collect(Collectors.toList());
    }

    // 洛托姆之力
    private List<ItemListSpider.Data> getData32(Document document) {
        final String type = "洛托姆之力";
        final int generation = 0;
        return document.selectFirst("#洛托姆之力").parent().nextElementSibling().select("tbody > tr").stream()
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
}
