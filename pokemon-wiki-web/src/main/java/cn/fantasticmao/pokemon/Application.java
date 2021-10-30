package cn.fantasticmao.pokemon;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder)
            .sources(Application.class);
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
            .run(args);
    }
}