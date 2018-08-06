package cn.fantasticmao.pokemon.spider;

import cn.fantasticmao.pokemon.spider.task.SaveDataTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

/**
 * TaskConsumer
 *
 * @author maodh
 * @since 2018/8/6
 */
public class TaskConsumer implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskConsumer.class);

    private final Connection connection;
    private final BlockingQueue<SaveDataTask> queue;
    private final CountDownLatch doneSignal;

    public TaskConsumer(Connection connection, BlockingQueue<SaveDataTask> queue, CountDownLatch doneSignal) {
        this.connection = connection;
        this.queue = queue;
        this.doneSignal = doneSignal;
    }

    @Override
    public void run() {
        while (true) {
            try {
                SaveDataTask task = queue.take();
                boolean result = task.save(connection);
                doneSignal.countDown();
                LOGGER.info("{} {}", task.getClass().getName(), result ? "保存成功" : "保存失败");
            } catch (InterruptedException e) {
                // 结束任务
                break;
            }
        }
    }
}