package cn.fantasticmao.pokemon.spider.task2;

import cn.fantasticmao.mundo.core.support.Constant;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * PokemonDetailSpider
 *
 * @author fantasticmao
 * @since 2018/8/28
 */
class PokemonDetailSpider extends AbstractTask2Spider<PokemonDetailSpider.Data> {
    private final int index;
    private final String nameZh;

    PokemonDetailSpider(int index, String nameZh) {
        super("https://wiki.52poke.com/zh-hans/" + nameZh, "");
        this.index = index;
        this.nameZh = nameZh;
    }

    @Override
    protected PokemonDetailSpider.Data parseData(Document document) {
        try {
            return _parseData(document);
        } catch (RuntimeException e) {
            logger.error("parse data error by nameZh {}", nameZh, e);
            throw e;
        }
    }

    @Getter
    static class Data implements AbstractTask2Spider.Data {
        private final int index;
        private final String nameZh;
        private final String imgUrl; // 图片链接
        private final String type; // 属性
        private final String category; // 分类
        private final String ability; // 特性
        private final String height; // 身高
        private final String weight; // 体重
        private final String bodyStyle; // 体形
        private final String catchRate; // 捕获率
        private final String genderRatio; // 性别比例
        private final String eggGroup1; // 第一生蛋分组
        private final String eggGroup2; // 第二生蛋分组
        private final String hatchTime; // 孵化时间
        private final String effortValue; // 基础点数
        private final BaseStat baseStat;
        private final List<LearnSetByLevelingUp> learnSetByLevelingUpList; // 可学会的招式
        private final List<LearnSetByTechnicalMachine> learnSetByTechnicalMachineList; // 能使用的招式学习器
        private final List<LearnSetByBreeding> learnSetByBreedingList; // 蛋招式

        public Data(int index, String nameZh, String imgUrl, String type, String category, String ability, String height,
                    String weight, String bodyStyle, String catchRate, String genderRatio, String eggGroup1, String eggGroup2,
                    String hatchTime, String effortValue, Data.BaseStat baseStat,
                    List<LearnSetByLevelingUp> learnSetByLevelingUpList,
                    List<LearnSetByTechnicalMachine> learnSetByTechnicalMachineList,
                    List<LearnSetByBreeding> learnSetByBreedingList) {
            this.index = index;
            this.nameZh = nameZh;
            this.imgUrl = imgUrl;
            this.type = type;
            this.category = category;
            this.ability = ability;
            this.height = height;
            this.weight = weight;
            this.bodyStyle = bodyStyle;
            this.catchRate = catchRate;
            this.genderRatio = genderRatio;
            this.eggGroup1 = eggGroup1;
            this.eggGroup2 = eggGroup2;
            this.hatchTime = hatchTime;
            this.effortValue = effortValue;
            this.baseStat = baseStat;
            this.learnSetByLevelingUpList = learnSetByLevelingUpList;
            this.learnSetByTechnicalMachineList = learnSetByTechnicalMachineList;
            this.learnSetByBreedingList = learnSetByBreedingList;
        }

        @Getter
        static class BaseStat {
            private final int hp;
            private final int attack;
            private final int defense;
            private final int spAttack;
            private final int spDefense;
            private final int speed;
            private final int total;
            private final float average;

            public BaseStat(int hp, int attack, int defense, int spAttack, int spDefense, int speed, int total, float average) {
                this.hp = hp;
                this.attack = attack;
                this.defense = defense;
                this.spAttack = spAttack;
                this.spDefense = spDefense;
                this.speed = speed;
                this.total = total;
                this.average = average;
            }
        }

        @Getter
        static class LearnSetByLevelingUp {
            private final String level;
            private final String move;
            private final String type;
            private final String category;
            private final String power;
            private final String accuracy;
            private final String pp;

            public LearnSetByLevelingUp(String level, String move, String type, String category,
                                        String power, String accuracy, String pp) {
                this.level = level;
                this.move = move;
                this.type = type;
                this.category = category;
                this.power = power;
                this.accuracy = accuracy;
                this.pp = pp;
            }
        }

        @Getter
        static class LearnSetByTechnicalMachine {
            private final String imgUrl;
            private final String technicalMachine;
            private final String move;
            private final String type;
            private final String category;
            private final String power;
            private final String accuracy;
            private final String pp;

            public LearnSetByTechnicalMachine(String imgUrl, String technicalMachine, String move, String type,
                                              String category, String power, String accuracy, String pp) {
                this.imgUrl = imgUrl;
                this.technicalMachine = technicalMachine;
                this.move = move;
                this.type = type;
                this.category = category;
                this.power = power;
                this.accuracy = accuracy;
                this.pp = pp;
            }
        }

        @Getter
        static class LearnSetByBreeding {
            private final String parent;
            private final String move;
            private final String type;
            private final String category;
            private final String power;
            private final String accuracy;
            private final String pp;

            public LearnSetByBreeding(String parent, String move, String type, String category, String power,
                                      String accuracy, String pp) {
                this.parent = parent;
                this.move = move;
                this.type = type;
                this.category = category;
                this.power = power;
                this.accuracy = accuracy;
                this.pp = pp;
            }
        }
    }

    private PokemonDetailSpider.Data _parseData(Document document) {
        final Element table = document.select("#mw-content-text > .mw-parser-output > table").get(1);

        final String indexStr = String.format("%03d", index);

        final String imgUrl = table.selectFirst("img[alt^=" + indexStr + "]") != null
            ? table.selectFirst("img[alt^=" + indexStr + "]").attr("data-url").replace("//media.52poke.com", "https://s1.52poke.wiki")
            : Constant.Strings.EMPTY;

        final String type = table.selectFirst("[title=属性]").parent().nextElementSibling().select("span[class=type-box-9-text]").stream()
            .map(element -> element.text().trim())
            .collect(Collectors.joining(Constant.Strings.COMMA));

        final String category = table.selectFirst("[title=分类]").parent().nextElementSibling().text().trim();

        List<String> abilityList = table.selectFirst("[title=特性]").parent().nextElementSibling().select("td").get(0).select("a").stream()
            .map(element -> element.text().trim())
            .collect(Collectors.toList());
        if (table.selectFirst("[title=特性]").parent().nextElementSibling().select("td").size() > 1) {
            String abilityHide = table.selectFirst("[title=特性]").parent().nextElementSibling().select("td").get(1).select("a").text().trim() + "（隐藏特性）";
            abilityList.add(abilityHide);
        }
        final String ability = String.join(Constant.Strings.COMMA, abilityList);

        final String height = table.select("[title=宝可梦列表（按身高和体重排序）]").get(0).parent().nextElementSibling().text().trim();

        final String weight = table.select("[title=宝可梦列表（按身高和体重排序）]").get(1).parent().nextElementSibling().text().trim();

        Element bodyStyleElement = table.selectFirst("[title=宝可梦列表（按体形分类）]").parent().nextElementSibling();
        final String bodyStyle = bodyStyleElement.select("img").size() == 0 ? "无" : bodyStyleElement.
            selectFirst("img").attr("data-url").replace("//media.52poke.com", "https://s1.52poke.wiki");

        final String catchRate = table.selectFirst("[title=捕获率]").parent().nextElementSibling().select("span[class=explain]").text().trim();

        List<String> genderRatioList = table.selectFirst("[title=宝可梦列表（按性别比例分类）]").parent().nextElementSibling().select("span").stream()
            .map(element -> element.text().trim())
            .collect(Collectors.toList());
        final String genderRatio = CollectionUtils.isEmpty(genderRatioList) ? "无性别" : String.join(Constant.Strings.COMMA, genderRatioList);

        List<String> eggGroupList = table.selectFirst("[title=宝可梦培育]").parent().nextElementSibling().select("td").get(0).select("a").stream()
            .map(element -> element.attr("title").trim().replaceAll("（.*）", ""))
            .collect(Collectors.toList());
        final String eggGroup1 = eggGroupList.size() >= 1 ? eggGroupList.get(0) : Constant.Strings.EMPTY;
        final String eggGroup2 = eggGroupList.size() >= 2 ? eggGroupList.get(1) : Constant.Strings.EMPTY;

        String hatchTime = table.selectFirst("[title=宝可梦培育]").parent().nextElementSibling().select("td").get(1).select("small").text().trim();
        hatchTime = hatchTime.substring(1, hatchTime.length() - 1);

        final String effortValue = table.selectFirst("[title=基础点数]").parent().nextElementSibling().selectFirst("tr").select("td").stream()
            .map(element -> element.text().trim())
            .collect(Collectors.joining(Constant.Strings.COMMA));

        // 种族值
        final Data.BaseStat baseStat = parseBaseStat(document);
        // 可学会的招式
        List<Data.LearnSetByLevelingUp> learnSetByLevelingUpList = parseLearnSetByLevelingUpList(document);
        // 能使用的招式学习器
        List<Data.LearnSetByTechnicalMachine> learnSetByTechnicalMachineList = parseLearnSetByTechnicalMachineList(document);
        // 蛋招式
        List<Data.LearnSetByBreeding> learnSetByBreedingList = parseLearnSetByBreedingList(document);

        return new PokemonDetailSpider.Data(index, nameZh, imgUrl, type, category, ability,
            height, weight, bodyStyle, catchRate, genderRatio, eggGroup1, eggGroup2, hatchTime, effortValue,
            baseStat, learnSetByLevelingUpList, learnSetByTechnicalMachineList, learnSetByBreedingList);
    }

    private Data.BaseStat parseBaseStat(Document document) {
        Element baseStatSpan = document.selectFirst("#种族值");
        Element baseStatTable = baseStatSpan.parent().nextElementSiblings().select("table").stream()
            .filter(element -> element.hasClass("roundy"))
            .findFirst()
            .orElseThrow();

        final int hp = Integer.parseInt(baseStatTable.selectFirst("tr[class=bgl-HP]").select("div[style*='float:right']").text());
        final int attack = Integer.parseInt(baseStatTable.selectFirst("tr[class=bgl-攻击]").select("div[style*='float:right']").text());
        final int defense = Integer.parseInt(baseStatTable.selectFirst("tr[class=bgl-防御]").select("div[style*='float:right']").text());
        final int spAttack = Integer.parseInt(baseStatTable.selectFirst("tr[class=bgl-特攻]").select("div[style*='float:right']").text());
        final int spDefense = Integer.parseInt(baseStatTable.selectFirst("tr[class=bgl-特防]").select("div[style*='float:right']").text());
        final int speed = Integer.parseInt(baseStatTable.selectFirst("tr[class=bgl-速度]").select("div[style*='float:right']").text());
        final int total = hp + attack + defense + spAttack + spDefense + speed;
        final float average = total / 6.0F;
        return new Data.BaseStat(hp, attack, defense, spAttack, spDefense, speed, total, average);
    }

    private List<Data.LearnSetByLevelingUp> parseLearnSetByLevelingUpList(Document document) {
        Element learnSetByLevelingUpSpan = document.selectFirst("#可学会的招式");
        if (learnSetByLevelingUpSpan == null) {
            return Collections.emptyList();
        }

        final Element learnSetByLevelingUpTable = learnSetByLevelingUpSpan.parent().nextElementSibling();
        if (learnSetByLevelingUpTable == null
            || !"table".equals(learnSetByLevelingUpTable.tagName())) {
            return Collections.emptyList();
        }

        return learnSetByLevelingUpTable.select("> tbody > tr").stream()
            .filter(element -> element.hasClass("bgwhite"))
            .map(element -> {
                Elements tdList = element.select("td[style!=display: none]");
                final String _level = tdList.get(0).text();
                final String _move = tdList.get(1).select("a").text();
                final String _type = tdList.get(2).select("a").text();
                final String _category = tdList.get(3).select("a").text();
                final String _power = tdList.get(4).text();
                final String _accuracy = tdList.get(5).text();
                final String _pp = tdList.get(6).text();
                return new Data.LearnSetByLevelingUp(_level, _move, _type, _category, _power, _accuracy, _pp);
            })
            .collect(Collectors.toList());
    }

    private List<Data.LearnSetByTechnicalMachine> parseLearnSetByTechnicalMachineList(Document document) {
        Element learnSetByTechnicalMachineSpan = document.selectFirst("#能使用的招式学习器");
        if (learnSetByTechnicalMachineSpan == null) {
            // 兼容 https://wiki.52poke.com/zh-hans/铝钢龙
            learnSetByTechnicalMachineSpan = document.selectFirst("#能使用的招式学习器和招式记录");
        }
        if (learnSetByTechnicalMachineSpan == null) {
            return Collections.emptyList();
        }

        final Element learnSetByTechnicalMachineTable = learnSetByTechnicalMachineSpan.parent().nextElementSibling();
        if (learnSetByTechnicalMachineTable == null
            || !"table".equals(learnSetByTechnicalMachineTable.tagName())) {
            return Collections.emptyList();
        }
        return learnSetByTechnicalMachineTable.select("> tbody > tr").stream()
            .filter(element -> element.hasClass("bgwhite"))
            .map(element -> {
                final String _imgUrl = element.selectFirst("img").attr("data-url").replace("//media.52poke.com", "https://s1.52poke.wiki");
                final String _technicalMachine = element.child(1).text();
                final String _move = element.child(2).select("a").text();
                final String _type = element.child(3).text();
                final String _category = element.child(4).text();
                final String _power = element.child(5).text();
                final String _accuracy = element.child(6).text();
                final String _pp = element.child(7).text();
                return new Data.LearnSetByTechnicalMachine(_imgUrl, _technicalMachine, _move, _type, _category, _power, _accuracy, _pp);
            })
            .collect(Collectors.toList());
    }

    private List<Data.LearnSetByBreeding> parseLearnSetByBreedingList(Document document) {
        Element learnSetByBreedingSpan = document.selectFirst("#蛋招式");
        if (learnSetByBreedingSpan == null) {
            return Collections.emptyList();
        }
        return learnSetByBreedingSpan.parent().nextElementSibling().select("> tbody > tr").stream()
            .filter(element -> element.hasClass("bgwhite"))
            .map(element -> {
                final String _parent = element.child(0).select("> span, a").stream()
                    .map(e -> {
                        switch (e.tagName().toLowerCase()) {
                            case "a": {
                                if (e.hasClass("selflink")) {
                                    // 自身
                                    return nameZh;
                                } else {
                                    // 抛弃「模仿香草」，选择「模仿香草（道具）」
                                    String title = e.attr("title");
                                    return "模仿香草".equals(title) ? null : title;
                                }
                            }
                            case "span": {
                                if (e.hasAttr("data-msp")) {
                                    // 解析「130\暴鲤龙,246\幼基拉斯,247\沙基拉斯」格式的数据
                                    String msp = e.attr("data-msp");
                                    return Arrays.stream(msp.split(Constant.Strings.COMMA))
                                        .filter(s -> s.contains("\\"))
                                        .map(s -> s.substring(s.indexOf("\\") + 1))
                                        .collect(Collectors.joining(Constant.Strings.COMMA));
                                } else {
                                    logger.warn("parseLearnSetByBreedingList for {}, does not contains attr: \"data-msp\" in <span>", nameZh);
                                    return null;
                                }
                            }
                            default:
                                logger.warn("parseLearnSetByBreedingList for {}, meets an unknown element: <{}>", nameZh, e.tagName());
                                return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.joining(Constant.Strings.COMMA));
                final String _move = element.child(1).select("a").text();
                final String _type = element.child(2).text();
                final String _category = element.child(3).text();
                final String _power = element.child(4).text();
                final String _accuracy = element.child(5).text();
                final String _pp = element.child(6).text();
                return new Data.LearnSetByBreeding(_parent, _move, _type, _category, _power, _accuracy, _pp);
            })
            .collect(Collectors.toList());
    }
}
