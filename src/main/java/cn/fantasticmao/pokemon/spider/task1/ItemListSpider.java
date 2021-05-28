package cn.fantasticmao.pokemon.spider.task1;

import cn.fantasticmao.mundo.core.support.Constant;
import cn.fantasticmao.pokemon.spider.Config;
import cn.fantasticmao.pokemon.spider.PokemonDataSource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.function.Function;
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
        dataList.addAll(getData3_1(document));
        dataList.addAll(getData4_1(document));
        dataList.addAll(getData4_2(document));
        dataList.addAll(getData4_3(document));
        dataList.addAll(getData4_4(document));
        dataList.addAll(getData4_5(document));
        dataList.addAll(getData4_6(document));
        dataList.addAll(getData4_7(document));
        dataList.addAll(getData5_1(document));
        dataList.addAll(getData5_2(document));
        dataList.addAll(getData5_3(document));
        dataList.addAll(getData5_4(document));
        dataList.addAll(getData6(document));
        dataList.addAll(getData7(document));
        dataList.addAll(getData8_1(document));
        dataList.addAll(getData8_2(document));
        dataList.addAll(getData9(document));
        //dataList.addAll(getData10(document));
        dataList.addAll(getData11(document));
        dataList.addAll(getData12_1(document));
        dataList.addAll(getData12_2(document));
        dataList.addAll(getData13(document));
        dataList.addAll(getData14_1(document));
        dataList.addAll(getData14_3(document));
        dataList.addAll(getData14_4(document));
        dataList.addAll(getData14_5(document));
        dataList.addAll(getData15_1(document));
        dataList.addAll(getData15_2(document));
        dataList.addAll(getData15_3(document));
        dataList.addAll(getData15_4(document));
        dataList.addAll(getData15_5(document));
        dataList.addAll(getData15_5(document));
        dataList.addAll(getData15_6(document));
        dataList.addAll(getData15_7(document));
        dataList.addAll(getData15_8(document));
        dataList.addAll(getData15_9(document));
        dataList.addAll(getData16(document));
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
    @Builder
    @AllArgsConstructor
    static class Data implements AbstractTask1Spider.Data {
        private final String type;
        private final String imgUrl;
        private final String nameZh;
        private final String nameJa;
        private final String nameEn;
        private final String desc;
        private final int generation;
    }

    private static final Queue<String> ROW_SPAN_IMGURL_QUEUE = new LinkedList<>();
    private static final Queue<String> ROW_SPAN_NAMEZH_QUEUE = new LinkedList<>();
    private static final Queue<String> ROW_SPAN_NAMEJA_QUEUE = new LinkedList<>();
    private static final Queue<String> ROW_SPAN_NAMEEN_QUEUE = new LinkedList<>();
    private static final Queue<String> ROW_SPAN_DESC_QUEUE = new LinkedList<>();

    private static void clearQueue() {
        ROW_SPAN_IMGURL_QUEUE.clear();
        ROW_SPAN_NAMEZH_QUEUE.clear();
        ROW_SPAN_NAMEJA_QUEUE.clear();
        ROW_SPAN_NAMEEN_QUEUE.clear();
        ROW_SPAN_DESC_QUEUE.clear();
    }

    private static final Function<Element, Data.DataBuilder> PARSER = element -> {
        int offset = 0;
        String imgUrl;
        if (ROW_SPAN_IMGURL_QUEUE.isEmpty()) {
            imgUrl = element.child(0).selectFirst("img").attr("data-url").replace("//media.52poke.com", "https://s1.52poke.wiki");
            if (element.child(0).hasAttr("rowspan")) {
                int rowCount = Integer.parseInt(element.child(0).attr("rowspan")) - 1;
                for (int i = 0; i < rowCount; i++) {
                    ROW_SPAN_IMGURL_QUEUE.offer(imgUrl);
                }
            }
            offset++;
        } else {
            imgUrl = ROW_SPAN_IMGURL_QUEUE.poll();
        }

        // 处理中文名称 rowspan 跨行问题
        String nameZh;
        if (ROW_SPAN_NAMEZH_QUEUE.isEmpty()) {
            nameZh = element.child(offset).text();
            if (element.child(offset).hasAttr("rowspan")) {
                int rowCount = Integer.parseInt(element.child(offset).attr("rowspan")) - 1;
                for (int i = 0; i < rowCount; i++) {
                    ROW_SPAN_NAMEZH_QUEUE.offer(nameZh);
                }
            }
            offset++;
        } else {
            nameZh = ROW_SPAN_NAMEZH_QUEUE.poll();
        }

        // 处理日文名称 rowspan 跨行问题
        String nameJa;
        if (ROW_SPAN_NAMEJA_QUEUE.isEmpty()) {
            nameJa = element.child(offset).text();
            if (element.child(offset).hasAttr("rowspan")) {
                int rowCount = Integer.parseInt(element.child(offset).attr("rowspan")) - 1;
                for (int i = 0; i < rowCount; i++) {
                    ROW_SPAN_NAMEJA_QUEUE.offer(nameJa);
                }
            }
            offset++;
        } else {
            nameJa = ROW_SPAN_NAMEJA_QUEUE.poll();
        }

        // 处理英文名称 rowspan 跨行问题
        String nameEn;
        if (ROW_SPAN_NAMEEN_QUEUE.isEmpty()) {
            nameEn = element.child(offset).text();
            if (element.child(offset).hasAttr("rowspan")) {
                int rowCount = Integer.parseInt(element.child(offset).attr("rowspan")) - 1;
                for (int i = 0; i < rowCount; i++) {
                    ROW_SPAN_NAMEEN_QUEUE.offer(nameEn);
                }
            }
            offset++;
        } else {
            nameEn = ROW_SPAN_NAMEEN_QUEUE.poll();
        }

        // 处理道具描述 rowspan 跨行问题
        String desc;
        if (ROW_SPAN_DESC_QUEUE.isEmpty()) {
            desc = element.child(offset).text();
            if (element.child(offset).hasAttr("rowspan")) {
                int rowCount = Integer.parseInt(element.child(offset).attr("rowspan")) - 1;
                for (int i = 0; i < rowCount; i++) {
                    ROW_SPAN_DESC_QUEUE.offer(desc);
                }
            }
        } else {
            desc = ROW_SPAN_DESC_QUEUE.poll();
        }
        return new Data.DataBuilder().imgUrl(imgUrl)
            .nameZh(nameZh).nameJa(nameJa)
            .nameEn(nameEn).desc(desc);
    };

    // 道具 - 野外使用和其它类别道具
    private List<ItemListSpider.Data> getData1(Document document) {
        try {
            final String type = "野外使用和其它类别道具";
            return document.selectFirst("#" + type).parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(0)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 进化道具
    private List<ItemListSpider.Data> getData2(Document document) {
        try {
            final String type = "进化道具";
            return document.selectFirst("#" + type).parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(0)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 可交换道具
    private List<ItemListSpider.Data> getData3(Document document) {
        try {
            final String type = "可交换道具";
            return document.selectFirst("#" + type).parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(0)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 可交换道具 - 球果
    private List<ItemListSpider.Data> getData3_1(Document document) {
        try {
            final String type = "球果";
            return document.selectFirst("#" + type).parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(0)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 携带物品 - 第二世代起
    private List<ItemListSpider.Data> getData4_1(Document document) {
        try {
            final String type = "携带物品";
            final int generation = 2;
            return document.selectFirst("#第二世代起").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(generation)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 携带物品 - 第三世代起
    private List<ItemListSpider.Data> getData4_2(Document document) {
        try {
            final String type = "携带物品";
            final int generation = 3;
            return document.selectFirst("#第三世代起").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(generation)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 携带物品 - 第四世代起
    private List<ItemListSpider.Data> getData4_3(Document document) {
        try {
            final String type = "携带物品";
            final int generation = 4;
            return document.selectFirst("#第四世代起").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(generation)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 携带物品 - 第五世代起
    private List<ItemListSpider.Data> getData4_4(Document document) {
        try {
            final String type = "携带物品";
            final int generation = 5;
            return document.selectFirst("#第五世代起").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(generation)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 携带物品 - 第六世代起
    private List<ItemListSpider.Data> getData4_5(Document document) {
        try {
            final String type = "携带物品";
            final int generation = 6;
            return document.selectFirst("#第六世代起").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(generation)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 携带物品 - 第七世代起
    private List<ItemListSpider.Data> getData4_6(Document document) {
        try {
            final String type = "携带物品";
            final int generation = 7;
            return document.selectFirst("#第七世代起").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(generation)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 携带物品 - 第八世代起
    private List<ItemListSpider.Data> getData4_7(Document document) {
        try {
            final String type = "携带物品";
            final int generation = 8;
            return document.selectFirst("#第八世代起").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(generation)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 邮件 - 第二世代
    private List<ItemListSpider.Data> getData5_1(Document document) {
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

    // 道具 - 邮件 - 第三世代
    private List<ItemListSpider.Data> getData5_2(Document document) {
        try {
            final String type = "邮件";
            final int generation = 3;
            return document.selectFirst("#第三世代").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(generation)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 邮件 - 第四世代
    private List<ItemListSpider.Data> getData5_3(Document document) {
        try {
            final String type = "邮件";
            final int generation = 4;
            return document.selectFirst("#第四世代").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(generation)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 邮件 - 第五世代
    private List<ItemListSpider.Data> getData5_4(Document document) {
        try {
            final String type = "邮件";
            final int generation = 5;
            return document.selectFirst("#第五世代").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(generation)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 糖果
    private List<ItemListSpider.Data> getData6(Document document) {
        try {
            final String type = "糖果";
            return document.selectFirst("#" + type).parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(0)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 精灵球
    private List<ItemListSpider.Data> getData7(Document document) {
        try {
            final String type = "精灵球";
            return document.selectFirst("#" + type).parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(0)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 宝物 - 贵重道具
    private List<ItemListSpider.Data> getData8_1(Document document) {
        try {
            final String type = "贵重道具";
            return document.selectFirst("#" + type).parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(0)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 宝物 - 化石
    private List<ItemListSpider.Data> getData8_2(Document document) {
        try {
            final String type = "化石";
            return document.selectFirst("#" + type).parent().nextElementSibling().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(0)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 战斗道具
    private List<ItemListSpider.Data> getData9(Document document) {
        try {
            final String type = "战斗道具";
            return document.selectFirst("#" + type).parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(0)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // TODO 10 招式学习器

    // 回复道具
    private List<ItemListSpider.Data> getData11(Document document) {
        try {
            final String type = "回复道具";
            return document.selectFirst("#" + type).parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(0)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // Z纯晶 - 训练家使用
    private List<ItemListSpider.Data> getData12_1(Document document) {
        try {
            final String type = "Z纯晶-训练家使用";
            return document.selectFirst("#训练家使用的Ｚ纯晶").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(0)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // Z纯晶 - 宝可梦使用
    private List<ItemListSpider.Data> getData12_2(Document document) {
        try {
            final String type = "Z纯晶-宝可梦使用";
            return document.selectFirst("#宝可梦使用的Ｚ纯晶").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(0)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 食材
    private List<ItemListSpider.Data> getData13(Document document) {
        try {
            final String type = "食材";
            return document.selectFirst("#" + type).parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(0)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 树果 - 第三世代
    private List<ItemListSpider.Data> getData14_1(Document document) {
        try {
            final String type = "树果";
            final int generation = 3;
            return document.selectFirst("#第三世代起_2").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(generation)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 树果 - 第四世代
    private List<ItemListSpider.Data> getData14_3(Document document) {
        final String type = "树果";
        final int generation = 4;
        return document.selectFirst("#第四世代起_2").parent().nextElementSibling().select("tbody > tr").stream()
            .skip(1)
            .map(element -> PARSER.apply(element)
                .type(type).generation(generation)
                .build()
            )
            .collect(Collectors.toList());
    }

    // 树果 - 第六世代
    private List<ItemListSpider.Data> getData14_4(Document document) {
        try {
            final String type = "树果";
            final int generation = 6;
            return document.selectFirst("#第六世代起_2").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(generation)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 树果 - 第七世代
    private List<ItemListSpider.Data> getData14_5(Document document) {
        try {
            final String type = "树果";
            final int generation = 7;
            return document.selectFirst("#第七世代起_2").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(generation)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 重要物品 - 第一世代
    private List<ItemListSpider.Data> getData15_1(Document document) {
        try {
            final String type = "重要物品";
            final int generation = 1;
            return document.selectFirst("#第一世代起").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(generation)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 重要物品 - 第二世代
    private List<ItemListSpider.Data> getData15_2(Document document) {
        try {
            final String type = "重要物品";
            final int generation = 2;
            return document.selectFirst("#第二世代起_2").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(generation)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 重要物品 - 第三世代
    private List<ItemListSpider.Data> getData15_3(Document document) {
        try {
            final String type = "重要物品";
            final int generation = 3;
            return document.selectFirst("#第三世代起_3").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(generation)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 重要物品 - 第四世代
    private List<ItemListSpider.Data> getData15_4(Document document) {
        try {
            final String type = "重要物品";
            final int generation = 4;
            return document.selectFirst("#第四世代起_3").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(generation)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 重要物品 - 第五世代
    private List<ItemListSpider.Data> getData15_5(Document document) {
        try {
            final String type = "重要物品";
            final int generation = 5;
            return document.selectFirst("#第五世代起_2").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(generation)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 重要物品 - 第六世代
    private List<ItemListSpider.Data> getData15_6(Document document) {
        try {
            final String type = "重要物品";
            final int generation = 6;
            return document.selectFirst("#第六世代起_3").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(generation)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 重要物品 - 第七世代
    private List<ItemListSpider.Data> getData15_7(Document document) {
        try {
            final String type = "重要物品";
            final int generation = 7;
            return document.selectFirst("#第七世代起_2").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(generation)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 重要物品 - 第八世代
    private List<ItemListSpider.Data> getData15_8(Document document) {
        try {
            final String type = "重要物品";
            final int generation = 8;
            return document.selectFirst("#第八世代起_2").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(generation)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 重要物品 - 曾经是重要物品的道具
    private List<ItemListSpider.Data> getData15_9(Document document) {
        try {
            final String type = "重要物品";
            final int generation = 0;
            return document.selectFirst("#曾经是重要物品的道具").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(generation)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 洛托姆之力
    private List<ItemListSpider.Data> getData16(Document document) {
        try {
            final String type = "洛托姆之力";
            return document.selectFirst("#" + type).parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> PARSER.apply(element)
                    .type(type).generation(0)
                    .build()
                )
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }
}
