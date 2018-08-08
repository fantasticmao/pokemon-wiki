package cn.fantasticmao.pokemon.spider;

import cn.fantasticmao.pokemon.spider.task1.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Starter
 *
 * @author maodh
 * @since 2018/7/29
 */
class Starter {
    private static final Logger LOGGER = LoggerFactory.getLogger(Starter.class);

    public static void main(String[] args) throws InterruptedException {
        Connection connection = initConnection();

        task1(connection);

        task2(connection);

        destroyConnection(connection);
    }

    static private void task1(Connection connection) throws InterruptedException {
        // 1. 初始化线程池
        AtomicInteger atomicInteger = new AtomicInteger(1);
        ExecutorService executorService = Executors.newCachedThreadPool(runnable -> {
            Thread thread = new Thread(runnable, "Spider-Task1-Thread-" + atomicInteger.getAndIncrement());
            thread.setUncaughtExceptionHandler((t, e) -> LOGGER.error(t.getName(), e));
            return thread;
        });

        // 2. 添加爬虫任务
        final BlockingQueue<SaveDataTask> queue = new LinkedBlockingQueue<>(Integer.MAX_VALUE);
        final CountDownLatch doneSignal = new CountDownLatch(5);
        executorService.execute(new PokemonListSpider(queue));
        executorService.execute(new PokemonAbilityListSpider(queue));
        executorService.execute(new PokemonNatureListSpider(queue));
        executorService.execute(new PokemonBaseStatListSpider(queue));
        executorService.execute(new PokemonMoveListSpider(queue));
        executorService.execute(new TaskConsumer(connection, queue, doneSignal));

        // 3. 结束任务
        doneSignal.await();
        executorService.shutdownNow();
    }

    static private void task2(Connection connection) throws InterruptedException {

    }

    static private Connection initConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pokemon_wiki", "pokemon", "I_Love_Pokemon");
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static private void destroyConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
