package cn.fantasticmao.pokemon.spider;

import cn.fantasticmao.mundo.core.support.Constant;
import org.apache.commons.dbcp2.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

/**
 * PokemonDataSource
 *
 * @author fantasticmao
 * @since 2018/8/9
 */
public enum PokemonDataSource {
    INSTANCE("jdbc:sqlite:pokemon_wiki.db", "schema.sql");

    private final PoolingDataSource<PoolableConnection> poolingDataSource;
    private final Logger logger = LoggerFactory.getLogger(PokemonDataSource.class);

    PokemonDataSource(String jdbcUrl, String schemaUrl) {
        GenericObjectPoolConfig<PoolableConnection> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxTotal(Config.TASK_POOLING_DATA_SOURCE_MAX_SIZE);
        poolConfig.setMaxIdle(Config.TASK_POOLING_DATA_SOURCE_MAX_SIZE);
        this.poolingDataSource = setupDataSource(jdbcUrl, poolConfig);
        this.initDatabase(schemaUrl);
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

    private PoolingDataSource<PoolableConnection> setupDataSource(String url,
                                                                  GenericObjectPoolConfig<PoolableConnection> poolConfig) {
        // First, we'll create a ConnectionFactory that the pool will use to create Connections.
        // We'll use the DriverManagerConnectionFactory, using the connect string passed in the command line arguments.
        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(url);

        // Next we'll create the PoolableConnectionFactory, which wraps the "real" Connections created by the ConnectionFactory with
        // the classes that implement the pooling functionality.
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);
        poolableConnectionFactory.setDefaultAutoCommit(false);

        // Now we'll need a ObjectPool that serves as the actual pool of connections.
        // We'll use a GenericObjectPool instance, although any ObjectPool implementation will suffice.
        ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory, poolConfig);

        // Set the factory's pool property to the owning pool
        poolableConnectionFactory.setPool(connectionPool);

        // Finally, we create the PoolingDriver itself, passing in the object pool we created.
        return new PoolingDataSource<>(connectionPool);
    }

    private void initDatabase(String schemaUrl) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL schemaResource = classLoader.getResource(schemaUrl);
        if (Objects.isNull(schemaResource)) {
            throw new IllegalArgumentException("schema file does not exist: '" + schemaUrl + "'");
        }

        final String initSql;
        try {
            Path schemaPath = Paths.get(schemaResource.toURI());
            List<String> lines = Files.readAllLines(schemaPath);
            initSql = String.join(Constant.Strings.EMPTY, lines);
        } catch (URISyntaxException | IOException e) {
            throw new IllegalArgumentException("read schema file error", e);
        }

        Objects.requireNonNull(initSql, "init sql in schema file error");
        try (Connection connection = this.getConnection();
             Statement statement = connection.createStatement()) {
            for (String sql : initSql.split(Constant.Strings.SEMICOLON)) {
                if (StringUtils.isBlank(sql)) {
                    continue;
                }

                sql = sql.trim();
                statement.executeUpdate(sql);
                logger.info("execute sql: {}", sql);
            }
            connection.commit();
        } catch (SQLException e) {
            logger.error("init database error", e);
            Runtime.getRuntime().exit(1);
        }
    }
}