package cn.fantasticmao.pokemon.wiki;

import com.mundo.web.mvc.WeChatConfigController;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * PokemonConfigurationSnapshot
 *
 * @author maodh
 * @since 2018/7/29
 */
@Configuration
@Profile("snapshot")
public class PokemonConfigurationSnapshot {
    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonConfigurationSnapshot.class);
    @javax.annotation.Resource
    private ResourceLoader resourceLoader;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.h2.Driver");
        String jdbcUrl = "jdbc:h2:mem:pokemon_wiki;";
        final Resource resource = resourceLoader.getResource("classpath:database.sql");
        if (resource.exists()) {
            try {
                jdbcUrl = jdbcUrl + String.format("INIT=RUNSCRIPT FROM '%s'", resource.getFile().getPath());
            } catch (IOException e) {
                LOGGER.error("could not load run script from 'database.sql'", e);
            }
        }
        config.setJdbcUrl(jdbcUrl);
        config.setUsername("sa");
        config.setPassword("");
        config.setReadOnly(true);
        config.setConnectionTimeout(5_000);
        config.setMaximumPoolSize(5);
        return new HikariDataSource(config);
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setDatabase(Database.H2);

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

    @Bean(name = WeChatConfigController.TOKEN_BEAN_NAME)
    String weChatToken() {
        return "I_Love_Pokemon";
    }
}
