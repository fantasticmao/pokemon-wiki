package cn.fantasticmao.pokemon.spider;

/**
 * Config
 *
 * @author maodh
 * @since 2018/7/29
 */
public interface Config {

    enum Site {
        BASE_URL("https://wiki.52poke.com"),
        POKEMON_LIST("https://wiki.52poke.com/zh-hans/宝可梦列表（按全国图鉴编号）"),
        POKEMON_ABILITY_LIST("https://wiki.52poke.com/zh-hans/特性列表（按全国图鉴编号）"),
        POKEMON_BASE_STAT_LIST("https://wiki.52poke.com/zh-hans/种族值列表（第七世代）"),
        ABILITY_LIST("https://wiki.52poke.com/zh-hans/特性列表"),
        NATURE_LIST("https://wiki.52poke.com/zh-hans/性格"),
        MOVE_LIST("https://wiki.52poke.com/zh-hans/招式列表"),
        ITEM_LIST("https://wiki.52poke.com/zh-hans/道具列表");

        public String url;

        Site(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return this.url;
        }
    }

    String SQL_INIT_TABLE = Config.class.getResource("/").getPath() + "schema.sql";

    String SQL_DATABASE = "~/database.sql";

    int TASK_POOLING_DATA_SOURCE_MAX_SIZE = 5;

    int TASK2_CONCURRENCY_THRESHOLD = 100;
}
