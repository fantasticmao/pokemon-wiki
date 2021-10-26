package cn.fantasticmao.pokemon.wiki;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.sqlite.SQLiteConfig;

import javax.sql.DataSource;

/**
 * PokemonConfiguration
 *
 * @author maomao
 * @since 2019-07-05
 */
@Configuration
public class PokemonConfiguration implements WebMvcConfigurer {
    @Value("${app.dbfile:pokemon_wiki.db}")
    private String databaseFile;

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:sqlite:" + databaseFile);

        SQLiteConfig sqLiteConfig = new SQLiteConfig();
        sqLiteConfig.setDateStringFormat("yyyy-MM-dd HH:mm:ss");
        hikariConfig.setDataSourceProperties(sqLiteConfig.toProperties());
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(false);
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setDatabase(Database.MYSQL);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setPackagesToScan("cn.fantasticmao");
        return factoryBean;
    }

    @Bean
    PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

}
