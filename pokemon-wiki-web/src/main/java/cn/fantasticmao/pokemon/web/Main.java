package cn.fantasticmao.pokemon.web;

import org.jspecify.annotations.NonNull;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Main extends SpringBootServletInitializer {

    @NonNull
    @Override
    protected SpringApplicationBuilder configure(@NonNull SpringApplicationBuilder builder) {
        return super.configure(builder)
            .sources(Main.class);
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(Main.class)
            .run(args);
    }
}
