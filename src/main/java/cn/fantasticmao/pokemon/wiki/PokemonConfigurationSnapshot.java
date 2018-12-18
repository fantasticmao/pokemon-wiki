package cn.fantasticmao.pokemon.wiki;

import com.mundo.web.EnableMundoWeb;
import com.mundo.web.mvc.WeChatConfigController;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * PokemonConfigurationSnapshot
 *
 * @author maodh
 * @since 2018/7/29
 */
@Configuration
@Profile("snapshot")
@EnableMundoWeb
public class PokemonConfigurationSnapshot {

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/pokemon_wiki?characterEncoding=UTF8&connectTimeout=5000&useSSL=false&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true");
        config.setUsername("pokemon");
        config.setPassword("I_Love_Pokemon");
        config.setConnectionTimeout(5_000);
        config.setMaximumPoolSize(5);
        return new HikariDataSource(config);
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
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

    @Bean(name = WeChatConfigController.TOKEN_BEAN_NAME)
    String weChatToken() {
        return "I_Love_Pokemon";
    }
}
