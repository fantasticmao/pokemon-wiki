package cn.fantasticmao.pokemon.wiki;

import com.mundo.web.EnableMundoWeb;
import com.mundo.web.mvc.WeChatConfigController;
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
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/pokemon_wiki");
        dataSource.setUsername("pokemon");
        dataSource.setPassword("I_Love_Pokemon");
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(5);
        dataSource.setMinIdle(20);
        dataSource.setMaxWait(3_000);
        dataSource.setConnectionProperties("characterEncoding=UTF8;connectTimeout=5000;useSSL=false;zeroDateTimeBehavior=convertToNull;rewriteBatchedStatements=true");
        dataSource.setTestOnBorrow(true);
        dataSource.setValidationQuery("SELECT 1");
        return dataSource;
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
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }

    @Bean(name = WeChatConfigController.TOKEN_BEAN_NAME)
    String weChatToken() {
        return "I_Love_Pokemon";
    }
}
