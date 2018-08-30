package cn.fantasticmao.pokemon;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {

    /**
     * java -Xms100m -Xmx200m -XX:+HeapDumpOnOutOfMemoryError -XX:+UseConcMarkSweepGC -Dfile.encoding=UTF-8 -Dspring.profiles.active=master -jar pokemon-wiki-1.0.jar >> /var/log/pokemon-wiki/pokemon_wiki.log &
     */
    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .properties("spring.profiles.default=snapshot")
                .run(args);
    }
}