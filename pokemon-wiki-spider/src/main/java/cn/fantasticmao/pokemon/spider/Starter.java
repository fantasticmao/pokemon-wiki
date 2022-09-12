package cn.fantasticmao.pokemon.spider;

import cn.fantasticmao.pokemon.spider.task1.*;
import cn.fantasticmao.pokemon.spider.task2.AbilityDetailSpiderScheduler;
import cn.fantasticmao.pokemon.spider.task2.MoveDetailSpiderScheduler;
import cn.fantasticmao.pokemon.spider.task2.PokemonDetailSpiderScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Starter
 *
 * @author fantasticmao
 * @since 2018/7/29
 */
class Starter {
    private static final Logger LOGGER = LoggerFactory.getLogger(Starter.class);

    public static void main(String[] args) throws Exception {
        task1();

        task2();

        PokemonDataSource.INSTANCE.shutDownDataSource();
    }

    private static void task1() throws InterruptedException {
        // 1. 初始化线程池
        final int threads = 6;
        SpiderTaskThreadFactory threadFactory = new SpiderTaskThreadFactory("task1");
        ExecutorService executorService = Executors.newFixedThreadPool(threads, threadFactory);

        // 2. 添加爬虫任务
        final CountDownLatch doneSignal = new CountDownLatch(threads);
        executorService.execute(new PokemonListSpider(doneSignal));
        executorService.execute(new PokemonAbilityListSpider(doneSignal));
        executorService.execute(new AbilityListSpider(doneSignal));
        executorService.execute(new NatureListSpider(doneSignal));
        executorService.execute(new MoveListSpider(doneSignal));
        executorService.execute(new ItemListSpider(doneSignal));

        // 3. 结束任务
        doneSignal.await();
        executorService.shutdownNow();
    }

    private static void task2() throws InterruptedException {
        // 1. 初始化线程池
        SpiderTaskThreadFactory threadFactory = new SpiderTaskThreadFactory("task2");
        ExecutorService executorService = Executors.newFixedThreadPool(Config.TASK2_CONCURRENCY_THRESHOLD, threadFactory);

        // 2. 添加爬虫任务
        new PokemonDetailSpiderScheduler(executorService).start();
        new AbilityDetailSpiderScheduler(executorService).start();
        new MoveDetailSpiderScheduler(executorService).start();

        // 3. 结束任务
        executorService.shutdownNow();
    }

    static class SpiderTaskThreadFactory implements ThreadFactory {
        private final String name;
        private final AtomicInteger num;

        SpiderTaskThreadFactory(String name) {
            this.name = name;
            this.num = new AtomicInteger(1);
        }

        @Override
        public Thread newThread(@Nonnull Runnable runnable) {
            Thread thread = new Thread(runnable, String.format("Spider-Thread-%s-%d",
                name, num.incrementAndGet()));
            thread.setDaemon(false);
            thread.setUncaughtExceptionHandler((t, e) ->
                LOGGER.error("an exception was thrown in thread '{}'", t.getName(), e));
            return thread;
        }
    }
}
