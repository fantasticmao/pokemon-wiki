package cn.fantasticmao.pokemon.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;

/**
 * AppConfiguration
 *
 * @author fantasticmao
 * @since 2019-07-05
 */
@Configuration
public class AppConfiguration implements WebMvcConfigurer {
    @Value("${app.dbfile:pokemon_wiki.db}")
    private String databaseFile;

    @Bean
    public DataSource dataSource() {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setReadOnly(true);
        dataSource.setUrl("jdbc:sqlite:" + databaseFile);
        return dataSource;
    }

}
