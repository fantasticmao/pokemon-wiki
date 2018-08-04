package cn.fantasticmao.pokemon.spider;

import cn.fantasticmao.pokemon.spider.task.PokemonAbilityListSpider;
import cn.fantasticmao.pokemon.spider.task.PokemonListSpider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Starter
 *
 * @author maodh
 * @since 2018/7/29
 */
class Starter {
    private static final Logger LOGGER = LoggerFactory.getLogger(Starter.class);

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        ExecutorService executorService = Executors.newFixedThreadPool(1, runnable -> {
            Thread thread = new Thread(runnable, "Spider-Thread-" + atomicInteger.getAndIncrement());
            thread.setUncaughtExceptionHandler((t, e) -> LOGGER.error(t.getName(), e));
            return thread;
        });
        executorService.execute(new PokemonListSpider());
        executorService.execute(new PokemonAbilityListSpider());
        executorService.shutdown();
    }
}
