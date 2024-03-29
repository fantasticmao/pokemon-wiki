package cn.fantasticmao.pokemon.spider;

/**
 * Config
 *
 * @author fantasticmao
 * @since 2018/7/29
 */
public interface Config {

    enum Site {
        BASE_URL("https://wiki.52poke.com"),
        POKEMON_LIST("https://wiki.52poke.com/zh-hans/宝可梦列表（按全国图鉴编号）"),
        POKEMON_ABILITY_LIST("https://wiki.52poke.com/zh-hans/特性列表（按全国图鉴编号）"),
        ABILITY_LIST("https://wiki.52poke.com/zh-hans/特性列表"),
        NATURE_LIST("https://wiki.52poke.com/zh-hans/性格"),
        MOVE_LIST("https://wiki.52poke.com/zh-hans/招式列表"),
        ITEM_LIST("https://wiki.52poke.com/zh-hans/道具列表");

        public final String url;

        Site(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return this.url;
        }
    }

    int DATA_SOURCE_POOL_SIZE = 20;

    int TASK2_CONCURRENCY_THRESHOLD = 100;
}
