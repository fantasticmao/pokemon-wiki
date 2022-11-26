package cn.fantasticmao.pokemon.spider.task1;

import cn.fantasticmao.mundo.core.support.Constant;
import cn.fantasticmao.pokemon.spider.Config;
import cn.fantasticmao.pokemon.spider.PokemonDataSource;
import lombok.Getter;
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
import java.util.stream.Collectors;

/**
 * ItemListSpider
 *
 * @author fantasticmao
 * @since 2019-03-20
 */
public class ItemListSpider extends AbstractTask1Spider<ItemListSpider.Data> {

    public ItemListSpider(CountDownLatch doneSignal) {
        super(Config.Site.ITEM_LIST, doneSignal);
    }

    @Override
    protected List<ItemListSpider.Data> parseData(Document document) {
        List<ItemListSpider.Data> dataList = new LinkedList<>();
        dataList.addAll(getData1_1(document));
        dataList.addAll(getData1_2(document));
        dataList.addAll(getData1_3(document));
        dataList.addAll(getData1_3_1(document));
        dataList.addAll(getData1_3_2(document));
        dataList.addAll(getData1_4_1(document));
        dataList.addAll(getData1_4_2(document));
        dataList.addAll(getData1_4_3(document));
        dataList.addAll(getData1_4_4(document));
        dataList.addAll(getData1_4_5(document));
        dataList.addAll(getData1_4_6(document));
        dataList.addAll(getData1_4_7(document));
        dataList.addAll(getData1_4_8(document));
        dataList.addAll(getData1_5_1(document));
        dataList.addAll(getData1_5_2(document));
        dataList.addAll(getData1_5_3(document));
        dataList.addAll(getData1_5_4(document));
        dataList.addAll(getData1_6(document));
        dataList.addAll(getData1_7(document));
        dataList.addAll(getData2(document));
        dataList.addAll(getData3_1(document));
        dataList.addAll(getData3_2(document));
        dataList.addAll(getData4(document));
        dataList.addAll(getData5(document));
        dataList.addAll(getData6(document));
        dataList.addAll(getData7_1(document));
        dataList.addAll(getData7_2(document));
        dataList.addAll(getData8(document));
        dataList.addAll(getData9(document));
        dataList.addAll(getData10_1(document));
        dataList.addAll(getData10_2(document));
        dataList.addAll(getData11_1(document));
        dataList.addAll(getData11_2(document));
        dataList.addAll(getData11_3(document));
        dataList.addAll(getData11_4(document));
        dataList.addAll(getData11_5(document));
        dataList.addAll(getData11_6(document));
        dataList.addAll(getData12_1(document));
        dataList.addAll(getData12_2(document));
        dataList.addAll(getData12_4(document));
        dataList.addAll(getData12_5(document));
        dataList.addAll(getData12_6(document));
        dataList.addAll(getData12_7(document));
        dataList.addAll(getData12_8(document));
        dataList.addAll(getData12_9(document));
        dataList.addAll(getData12_10(document));
        dataList.addAll(getData13(document));
        return Collections.unmodifiableList(dataList);
    }

    @Override
    protected boolean saveData(List<ItemListSpider.Data> dataList) {
        final int batchSize = 100;
        final String sql = "INSERT INTO pw_item(type, img_url, name_zh, name_ja, name_en, desc, generation) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = PokemonDataSource.INSTANCE.getConnection();
             PreparedStatement prep = connection.prepareStatement(sql)) {
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
            logger.error("insert into pw_item error", e);
            return false;
        }
    }

    @Getter
    static class Data implements AbstractTask1Spider.Data {
        private final String type;
        private final String imgUrl;
        private final String nameZh;
        private final String nameJa;
        private final String nameEn;
        private final String desc;
        private final int generation;

        public Data(String type, String imgUrl, String nameZh, String nameJa, String nameEn, String desc,
                    int generation) {
            this.type = type;
            this.imgUrl = imgUrl;
            this.nameZh = nameZh;
            this.nameJa = nameJa;
            this.nameEn = nameEn;
            this.desc = desc;
            this.generation = generation;
        }
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

    private static Data parser(Element element, String type, int generation) {
        int offset = 0;

        // 处理预览图片 rowspan 跨行问题
        String imgUrl;
        if (ROW_SPAN_IMGURL_QUEUE.isEmpty()) {
            Element imgElement = element.child(0).selectFirst("img");
            if (imgElement != null) {
                imgUrl = imgElement.attr("data-url").replace("//media.52poke.com", "https://s1.52poke.wiki");
                if (element.child(0).hasAttr("rowspan")) {
                    int rowCount = Integer.parseInt(element.child(0).attr("rowspan")) - 1;
                    for (int i = 0; i < rowCount; i++) {
                        ROW_SPAN_IMGURL_QUEUE.offer(imgUrl);
                    }
                }
                offset++;
            } else {
                imgUrl = "";
            }
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
        return new Data(type, imgUrl, nameZh, nameJa, nameEn, desc, generation);
    }

    // 道具 - 野外使用和其它类别道具
    private List<ItemListSpider.Data> getData1_1(Document document) {
        try {
            return document.selectFirst("#野外使用和其它类别道具").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "野外使用和其它类别道具", 0))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 进化道具
    private List<ItemListSpider.Data> getData1_2(Document document) {
        try {
            return document.selectFirst("#进化道具").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "进化道具", 0))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 可交换道具
    private List<ItemListSpider.Data> getData1_3(Document document) {
        try {
            return document.selectFirst("#可交换道具").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "可交换道具", 0))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 可交换道具 - 球果
    private List<ItemListSpider.Data> getData1_3_1(Document document) {
        try {
            return document.selectFirst("#球果").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "可交换道具 - 球果", 0))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 可交换道具 - 太晶碎块
    private List<ItemListSpider.Data> getData1_3_2(Document document) {
        try {
            return document.selectFirst("#太晶碎块").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "可交换道具 - 太晶碎块", 0))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 携带物品 - 第二世代起
    private List<ItemListSpider.Data> getData1_4_1(Document document) {
        try {
            return document.selectFirst("#第二世代起").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "携带物品 - 第二世代起", 2))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 携带物品 - 第三世代起
    private List<ItemListSpider.Data> getData1_4_2(Document document) {
        try {
            return document.selectFirst("#第三世代起").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "携带物品 - 第三世代起", 3))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 携带物品 - 第四世代起
    private List<ItemListSpider.Data> getData1_4_3(Document document) {
        try {
            return document.selectFirst("#第四世代起").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "携带物品 - 第四世代起", 4))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 携带物品 - 第五世代起
    private List<ItemListSpider.Data> getData1_4_4(Document document) {
        try {
            return document.selectFirst("#第五世代起").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "携带物品 - 第五世代起", 5))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 携带物品 - 第六世代起
    private List<ItemListSpider.Data> getData1_4_5(Document document) {
        try {
            return document.selectFirst("#第六世代起").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "携带物品 - 第六世代起", 6))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 携带物品 - 第七世代起
    private List<ItemListSpider.Data> getData1_4_6(Document document) {
        try {
            return document.selectFirst("#第七世代起").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "携带物品 - 第七世代起", 7))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 携带物品 - 第八世代起
    private List<ItemListSpider.Data> getData1_4_7(Document document) {
        try {
            return document.selectFirst("#第八世代起").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "携带物品 - 第八世代起", 8))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 携带物品 - 第九世代起
    private List<ItemListSpider.Data> getData1_4_8(Document document) {
        try {
            return document.selectFirst("#第九世代起").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "携带物品 - 第九世代起", 9))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 邮件 - 第二世代
    private List<ItemListSpider.Data> getData1_5_1(Document document) {
        return document.selectFirst("#第二世代").parent().nextElementSibling().select("tbody > tr").stream()
            .skip(1)
            .map(element -> {
                String imgUrl = Constant.Strings.EMPTY;
                String nameZh = element.child(0).text();
                String nameJa = element.child(1).text();
                String nameEn = element.child(2).text();
                String desc = element.child(3).text();
                return new ItemListSpider.Data("邮件 - 第二世代", imgUrl, nameZh, nameJa, nameEn, desc, 2);
            })
            .collect(Collectors.toList());
    }

    // 道具 - 邮件 - 第三世代
    private List<ItemListSpider.Data> getData1_5_2(Document document) {
        try {
            return document.selectFirst("#第三世代").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "邮件 - 第三世代", 3))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 邮件 - 第四世代
    private List<ItemListSpider.Data> getData1_5_3(Document document) {
        try {
            return document.selectFirst("#第四世代").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "邮件 - 第四世代", 4))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 邮件 - 第五世代
    private List<ItemListSpider.Data> getData1_5_4(Document document) {
        try {
            return document.selectFirst("#第五世代").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "邮件 - 第五世代", 5))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 糖果
    private List<ItemListSpider.Data> getData1_6(Document document) {
        try {
            return document.selectFirst("#糖果").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "糖果", 0))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 道具 - 材料
    private List<ItemListSpider.Data> getData1_7(Document document) {
        try {
            return document.selectFirst("#材料").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "材料", 0))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 精灵球
    private List<ItemListSpider.Data> getData2(Document document) {
        try {
            return document.selectFirst("#精灵球").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "精灵球", 0))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 宝物 - 贵重道具
    private List<ItemListSpider.Data> getData3_1(Document document) {
        try {
            return document.selectFirst("#贵重道具").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "宝物 - 贵重道具", 0))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 宝物 - 化石
    private List<ItemListSpider.Data> getData3_2(Document document) {
        try {
            return document.selectFirst("#化石").parent().nextElementSibling().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "宝物 - 化石", 0))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 战斗道具
    private List<ItemListSpider.Data> getData4(Document document) {
        try {
            return document.selectFirst("#战斗道具").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "战斗道具", 0))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // TODO 招式学习器
    private List<ItemListSpider.Data> getData5(Document document) {
        return Collections.emptyList();
    }

    // 回复道具
    private List<ItemListSpider.Data> getData6(Document document) {
        try {
            return document.selectFirst("#回复道具").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "回复道具", 0))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // Z纯晶 - 训练家使用
    private List<ItemListSpider.Data> getData7_1(Document document) {
        try {
            return document.selectFirst("#训练家使用的Ｚ纯晶").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "Z纯晶 - 训练家使用", 0))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // Z纯晶 - 宝可梦使用
    private List<ItemListSpider.Data> getData7_2(Document document) {
        try {
            return document.selectFirst("#宝可梦使用的Ｚ纯晶").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "Z纯晶 - 宝可梦使用", 0))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 工艺制作
    private List<ItemListSpider.Data> getData8(Document document) {
        try {
            return document.selectFirst("#工艺制作").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "工艺制作", 0))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 掉落物
    private List<ItemListSpider.Data> getData9(Document document) {
        try {
            return document.selectFirst("#掉落物").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "掉落物", 0))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 野餐 - 食材
    private List<ItemListSpider.Data> getData10_1(Document document) {
        try {
            return document.selectFirst("#食材").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "野餐 - 食材", 0))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 野餐 - 其他
    private List<ItemListSpider.Data> getData10_2(Document document) {
        try {
            return document.selectFirst("#其他").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "野餐 - 其他", 0))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 树果 - 第三世代
    private List<ItemListSpider.Data> getData11_1(Document document) {
        try {
            return document.selectFirst("#第三世代起_2").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "树果 - 第三世代", 3))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // TODO 树果 - 只在《红宝石／蓝宝石》
    private List<ItemListSpider.Data> getData11_2(Document document) {
        return Collections.emptyList();
    }

    // 树果 - 第四世代
    private List<ItemListSpider.Data> getData11_3(Document document) {
        return document.selectFirst("#第四世代起_2").parent().nextElementSibling().select("tbody > tr").stream()
            .skip(1)
            .map(element -> parser(element, "树果 - 第四世代", 4))
            .collect(Collectors.toList());
    }

    // 树果 - 第六世代
    private List<ItemListSpider.Data> getData11_4(Document document) {
        try {
            return document.selectFirst("#第六世代起_2").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "树果 - 第六世代", 6))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 树果 - 第七世代
    private List<ItemListSpider.Data> getData11_5(Document document) {
        try {
            return document.selectFirst("#第七世代起_2").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "树果 - 第七世代", 7))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 树果 - 第八世代起
    private List<ItemListSpider.Data> getData11_6(Document document) {
        try {
            return document.selectFirst("#第八世代起_2").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "树果 - 第八世代", 8))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 重要物品 - 第一世代
    private List<ItemListSpider.Data> getData12_1(Document document) {
        try {
            return document.selectFirst("#第一世代起").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "重要物品 - 第一世代", 1))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 重要物品 - 第二世代
    private List<ItemListSpider.Data> getData12_2(Document document) {
        try {
            return document.selectFirst("#第二世代起_2").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "重要物品 - 第二世代", 2))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 重要物品 - 第三世代
    private List<ItemListSpider.Data> getData12_3(Document document) {
        try {
            return document.selectFirst("#第三世代起_3").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "重要物品 - 第三世代", 3))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 重要物品 - 第四世代
    private List<ItemListSpider.Data> getData12_4(Document document) {
        try {
            return document.selectFirst("#第四世代起_3").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "重要物品 - 第四世代", 4))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 重要物品 - 第五世代
    private List<ItemListSpider.Data> getData12_5(Document document) {
        try {
            return document.selectFirst("#第五世代起_2").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "重要物品 - 第五世代", 5))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 重要物品 - 第六世代
    private List<ItemListSpider.Data> getData12_6(Document document) {
        try {
            return document.selectFirst("#第六世代起_3").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "重要物品 - 第六世代", 6))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 重要物品 - 第七世代
    private List<ItemListSpider.Data> getData12_7(Document document) {
        try {
            return document.selectFirst("#第七世代起_3").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "重要物品 - 第七世代", 7))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 重要物品 - 第八世代
    private List<ItemListSpider.Data> getData12_8(Document document) {
        try {
            return document.selectFirst("#第八世代起_3").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "重要物品 - 第八世代", 8))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 重要物品 - 第九世代
    private List<ItemListSpider.Data> getData12_9(Document document) {
        try {
            return document.selectFirst("#第九世代起_2").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "重要物品 - 第九世代", 9))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 重要物品 - 曾经是重要物品的道具
    private List<ItemListSpider.Data> getData12_10(Document document) {
        try {
            return document.selectFirst("#曾经是重要物品的道具").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "重要物品 - 曾经是重要物品的道具", 0))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }

    // 洛托姆之力
    private List<ItemListSpider.Data> getData13(Document document) {
        try {
            return document.selectFirst("#洛托姆之力").parent().nextElementSibling().select("tbody > tr").stream()
                .skip(1)
                .map(element -> parser(element, "洛托姆之力", 0))
                .collect(Collectors.toList());
        } finally {
            clearQueue();
        }
    }
}
