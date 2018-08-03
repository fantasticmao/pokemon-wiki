package cn.fantasticmao.pokemon.spider.task;

import cn.fantasticmao.pokemon.spider.Config;
import cn.fantasticmao.pokemon.spider.bean.PokemonType;
import lombok.Getter;
import lombok.ToString;
import org.jsoup.nodes.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * PokemonListSpider
 *
 * @author maodh
 * @since 2018/7/29
 */
public class PokemonListSpider extends AbstractSpider<PokemonListSpider.Data> {

    public PokemonListSpider() {
        super(Config.Site.POKEMON_LIST);
    }

    @Override
    List<PokemonListSpider.Data> parseData(Document document) {
        List<PokemonListSpider.Data> dataList = new LinkedList<>();
        dataList.addAll(getDataList1(document));
        dataList.addAll(getDataList2(document));
        dataList.addAll(getDataList3(document));
        dataList.addAll(getDataList4(document));
        dataList.addAll(getDataList5(document));
        dataList.addAll(getDataList6(document));
        dataList.addAll(getDataList7(document));
        return dataList;
    }

    @Override
    boolean saveData(Connection connection, List<PokemonListSpider.Data> dataList) {
        savePokemonType(connection, dataList);
        savePokemonList(connection, dataList);
        return true;
    }

    @Getter
    @ToString
    class Data implements AbstractSpider.Data {
        private final int index;
        private final String infoUrl;
        private final String nameZh;
        private final String nameJp;
        private final String nameEn;
        private final PokemonType type1;
        private final PokemonType type2;

        private Data(int index, String infoUrl, String nameZh, String nameJp, String nameEn, PokemonType type1, PokemonType type2) {
            this.index = index;
            this.infoUrl = infoUrl;
            this.nameZh = nameZh;
            this.nameJp = nameJp;
            this.nameEn = nameEn;
            this.type1 = type1;
            this.type2 = type2;
        }

        private List<PokemonType> getAllType() {
            return Arrays.asList(type1, type2);
        }

    }

    private void savePokemonType(Connection connection, List<PokemonListSpider.Data> dataList) {
        TreeSet<PokemonType> typeSet = dataList.stream()
                .flatMap(data -> data.getAllType().stream())
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(TreeSet::new));
        String sql = "INSERT INTO pw_pokemon_type (`name`, url) VALUES (?, ?)";
        try (PreparedStatement prep = connection.prepareStatement(sql)) {
            for (PokemonType type : typeSet) {
                prep.setString(1, type.getName());
                prep.setString(2, type.getUrl());
                prep.addBatch();
            }
            prep.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void savePokemonList(Connection connection, List<PokemonListSpider.Data> dataList) {
        final int batchSize = 100;
        final String sql = "INSERT INTO pw_pokemon (`index`, infoUrl, nameZh, nameJp, nameEn, type1, type2) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement prep = connection.prepareStatement(sql)) {
            PokemonListSpider.Data tempData = null;
            for (int i = batchSize, j = 0; ; i += batchSize) {
                for (; j < i && j < dataList.size(); j++) {
                    tempData = dataList.get(j);
                    prep.setInt(1, tempData.getIndex());
                    prep.setString(2, tempData.getInfoUrl());
                    prep.setString(3, tempData.getNameZh());
                    prep.setString(4, tempData.getNameJp());
                    prep.setString(5, tempData.getNameEn());
                    prep.setString(6, tempData.getType1().getName());
                    prep.setString(7, tempData.getType2() == null ? "" : tempData.getType2().getName());
                    prep.addBatch();
                }
                prep.executeBatch();
                if (j >= dataList.size()) {
                    connection.commit();
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // 关都地区
    private List<PokemonListSpider.Data> getDataList1(Document document) {
        return document.select(".s-关都 > tbody > tr").parallelStream()
                .skip(2)
                .map(element -> {
                    int index = Integer.parseInt(element.child(1).html().replace("#", ""));
                    String infoUrl = Config.Site.BASE_URL + element.child(3).child(0).attr("href");
                    String nameZh = element.child(3).child(0).html();
                    String nameJp = element.child(4).html();
                    String nameEn = element.child(5).html();
                    PokemonType type1 = new PokemonType(element.child(6).child(0).html(),
                            Config.Site.BASE_URL + element.child(6).child(0).attr("href"));
                    PokemonType type2 = element.child(7).hasClass("hide") ? null
                            : new PokemonType(element.child(7).child(0).html(), Config.Site.BASE_URL + element.child(7).child(0).attr("href"));
                    return new PokemonListSpider.Data(index, infoUrl, nameZh, nameJp, nameEn, type1, type2);
                })
                .collect(Collectors.toList());
    }

    // 城都地区
    private List<PokemonListSpider.Data> getDataList2(Document document) {
        return document.select(".s-城都 > tbody > tr").parallelStream()
                .skip(2)
                .map(element -> {
                    int index = Integer.parseInt(element.child(2).html().replace("#", ""));
                    String infoUrl = Config.Site.BASE_URL + element.child(4).child(0).attr("href");
                    String nameZh = element.child(4).child(0).html();
                    String nameJp = element.child(5).html();
                    String nameEn = element.child(6).html();
                    PokemonType type1 = new PokemonType(element.child(7).child(0).html(),
                            Config.Site.BASE_URL + element.child(7).child(0).attr("href"));
                    PokemonType type2 = element.child(8).hasClass("hide") ? null
                            : new PokemonType(element.child(8).child(0).html(), Config.Site.BASE_URL + element.child(8).child(0).attr("href"));
                    return new PokemonListSpider.Data(index, infoUrl, nameZh, nameJp, nameEn, type1, type2);
                })
                .collect(Collectors.toList());
    }

    // 丰缘地区
    private List<PokemonListSpider.Data> getDataList3(Document document) {
        return document.select(".s-豐緣 > tbody > tr").parallelStream()
                .skip(2)
                .map(element -> {
                    int index = Integer.parseInt(element.child(2).html().replace("#", ""));
                    String infoUrl = Config.Site.BASE_URL + element.child(4).child(0).attr("href");
                    String nameZh = element.child(4).child(0).html();
                    String nameJp = element.child(5).html();
                    String nameEn = element.child(6).html();
                    PokemonType type1 = new PokemonType(element.child(7).child(0).html(),
                            Config.Site.BASE_URL + element.child(7).child(0).attr("href"));
                    PokemonType type2 = element.child(8).hasClass("hide") ? null
                            : new PokemonType(element.child(8).child(0).html(), Config.Site.BASE_URL + element.child(8).child(0).attr("href"));
                    return new PokemonListSpider.Data(index, infoUrl, nameZh, nameJp, nameEn, type1, type2);
                })
                .collect(Collectors.toList());
    }

    // 神奥地区
    private List<PokemonListSpider.Data> getDataList4(Document document) {
        return document.select(".s-神奧 > tbody > tr").parallelStream()
                .skip(2)
                .map(element -> {
                    int index = Integer.parseInt(element.child(1).html().replace("#", ""));
                    String infoUrl = Config.Site.BASE_URL + element.child(3).child(0).attr("href");
                    String nameZh = element.child(3).child(0).html();
                    String nameJp = element.child(4).html();
                    String nameEn = element.child(5).html();
                    PokemonType type1 = new PokemonType(element.child(6).child(0).html(),
                            Config.Site.BASE_URL + element.child(6).child(0).attr("href"));
                    PokemonType type2 = element.child(7).hasClass("hide") ? null
                            : new PokemonType(element.child(7).child(0).html(), Config.Site.BASE_URL + element.child(7).child(0).attr("href"));
                    return new PokemonListSpider.Data(index, infoUrl, nameZh, nameJp, nameEn, type1, type2);
                })
                .collect(Collectors.toList());
    }

    // 合众地区
    private List<PokemonListSpider.Data> getDataList5(Document document) {
        return document.select(".s-合眾 > tbody > tr").parallelStream()
                .skip(2)
                .map(element -> {
                    int index = Integer.parseInt(element.child(2).html().replace("#", ""));
                    String infoUrl = Config.Site.BASE_URL + element.child(4).child(0).attr("href");
                    String nameZh = element.child(4).child(0).html();
                    String nameJp = element.child(5).html();
                    String nameEn = element.child(6).html();
                    PokemonType type1 = new PokemonType(element.child(7).child(0).html(),
                            Config.Site.BASE_URL + element.child(7).child(0).attr("href"));
                    PokemonType type2 = element.child(8).hasClass("hide") ? null
                            : new PokemonType(element.child(8).child(0).html(), Config.Site.BASE_URL + element.child(8).child(0).attr("href"));
                    return new PokemonListSpider.Data(index, infoUrl, nameZh, nameJp, nameEn, type1, type2);
                })
                .collect(Collectors.toList());
    }

    // 卡洛斯地区
    private List<PokemonListSpider.Data> getDataList6(Document document) {
        return document.select(".s-卡洛斯 > tbody > tr").parallelStream()
                .skip(2)
                .map(element -> {
                    int index = Integer.parseInt(element.child(3).html().replace("#", ""));
                    String infoUrl = Config.Site.BASE_URL + element.child(5).child(0).attr("href");
                    String nameZh = element.child(5).child(0).html();
                    String nameJp = element.child(6).html();
                    String nameEn = element.child(7).html();
                    PokemonType type1 = new PokemonType(element.child(8).child(0).html(),
                            Config.Site.BASE_URL + element.child(8).child(0).attr("href"));
                    PokemonType type2 = element.child(9).hasClass("hide") ? null
                            : new PokemonType(element.child(9).child(0).html(), Config.Site.BASE_URL + element.child(9).child(0).attr("href"));
                    return new PokemonListSpider.Data(index, infoUrl, nameZh, nameJp, nameEn, type1, type2);
                })
                .collect(Collectors.toList());
    }

    // 阿罗拉地区
    private List<PokemonListSpider.Data> getDataList7(Document document) {
        return document.select(".s-阿羅拉 > tbody > tr").parallelStream()
                .skip(2)
                .map(element -> {
                    int index = Integer.parseInt(element.child(2).html().replace("#", ""));
                    String infoUrl = Config.Site.BASE_URL + element.child(4).child(0).attr("href");
                    String nameZh = element.child(4).child(0).html();
                    String nameJp = element.child(5).html();
                    String nameEn = element.child(6).html();
                    PokemonType type1 = new PokemonType(element.child(7).child(0).html(),
                            Config.Site.BASE_URL + element.child(7).child(0).attr("href"));
                    PokemonType type2 = element.child(8).hasClass("hide") ? null
                            : new PokemonType(element.child(8).child(0).html(), Config.Site.BASE_URL + element.child(8).child(0).attr("href"));
                    return new PokemonListSpider.Data(index, infoUrl, nameZh, nameJp, nameEn, type1, type2);
                })
                .collect(Collectors.toList());
    }
}
