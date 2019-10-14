package cn.fantasticmao.pokemon.spider;

import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * PokemonDataSource
 *
 * @author maodh
 * @since 2018/8/9
 */
public enum PokemonDataSource {
    INSTANCE("jdbc:h2:mem:pokemon_wiki;INIT=RUNSCRIPT FROM '" + Config.SQL_INIT_TABLE + "'", "sa", "");

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private PoolingDataSource poolingDataSource;

    PokemonDataSource(String url, String userName, String userPassword) {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(Config.TASK_POOLING_DATA_SOURCE_MAX_SIZE);
        config.setMaxIdle(Config.TASK_POOLING_DATA_SOURCE_MAX_SIZE);
        this.poolingDataSource = setupDataSource(url, userName, userPassword, config);
    }


    /**
     * 从连接池中，获取数据库连接
     */
    public Connection getConnection() throws SQLException {
        return poolingDataSource.getConnection();
    }

    public void shutDownDataSource() throws Exception {
        poolingDataSource.close();
    }

    private PoolingDataSource setupDataSource(String url, String userName, String userPassword, GenericObjectPoolConfig config) {
        // First, we'll create a ConnectionFactory that the pool will use to create Connections.
        // We'll use the DriverManagerConnectionFactory, using the connect string passed in the command line arguments.
        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(url, userName, userPassword);

        // Next we'll create the PoolableConnectionFactory, which wraps the "real" Connections created by the ConnectionFactory with
        // the classes that implement the pooling functionality.
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);
        poolableConnectionFactory.setDefaultAutoCommit(false);

        // Now we'll need a ObjectPool that serves as the actual pool of connections.
        // We'll use a GenericObjectPool instance, although any ObjectPool implementation will suffice.
        ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory, config);

        // Set the factory's pool property to the owning pool
        poolableConnectionFactory.setPool(connectionPool);

        // Finally, we create the PoolingDriver itself, passing in the object pool we created.
        return new PoolingDataSource<>(connectionPool);
    }

}