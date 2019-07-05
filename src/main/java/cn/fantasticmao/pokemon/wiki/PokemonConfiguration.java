package cn.fantasticmao.pokemon.wiki;

import com.mundo.web.filter.HttpFormatRequestLoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.Filter;

/**
 * PokemonConfiguration
 *
 * @author maomao
 * @since 2019-07-05
 */
@EnableSwagger2
@Configuration
public class PokemonConfiguration {

    @Bean
    public Docket callbackDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("pokemon-wiki")
                .apiInfo(new ApiInfoBuilder()
                        .title("Pokemon-Wiki Api Documentation")
                        .contact(new Contact("FantasticMao", "https://github.com/FantasticMao/pokemon-wiki", "maomao8017@gmail.com"))
                        .license("MIT License")
                        .licenseUrl("https://github.com/FantasticMao/pokemon-wiki/blob/master/LICENSE")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.fantasticmao.pokemon.wiki.web"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    Filter httpFormatRequestLoggingFilter() {
        return new HttpFormatRequestLoggingFilter();
    }
}
