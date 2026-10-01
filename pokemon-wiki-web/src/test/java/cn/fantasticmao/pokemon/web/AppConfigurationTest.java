package cn.fantasticmao.pokemon.web;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * AppConfigurationTest
 *
 * @author fantasticmao
 * @see AppConfiguration
 * @since 2026/10/1
 */
public class AppConfigurationTest extends SpringTest {
    @Resource
    private DataSource dataSource;

    @Test
    public void dataSource_isReadOnly() {
        Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = dataSource.getConnection();
                 Statement statement = connection.createStatement()) {
                statement.execute("UPDATE t_pokemon SET name_zh = name_zh WHERE idx = 1");
            }
        });
    }
}
