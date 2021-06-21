package cn.fantasticmao.pokemon.wiki;

import cn.fantasticmao.mundo.web.filter.StandardFormatRequestLoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
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
public class PokemonConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/swagger-ui.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Bean
    public Docket callbackDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("pokemon-wiki")
            .apiInfo(new ApiInfoBuilder()
                .title("Pokemon-Wiki Api Documentation")
                .contact(new Contact("fantasticmao", "https://github.com/fantasticmao/pokemon-wiki", "maomao8017@gmail.com"))
                .license("MIT License")
                .licenseUrl("https://github.com/fantasticmao/pokemon-wiki/blob/master/LICENSE")
                .build())
            .select()
            .apis(RequestHandlerSelectors.basePackage("cn.fantasticmao.pokemon.wiki.web"))
            .paths(PathSelectors.any())
            .build();
    }

    @Bean
    Filter httpFormatRequestLoggingFilter() {
        return new StandardFormatRequestLoggingFilter();
    }
}
