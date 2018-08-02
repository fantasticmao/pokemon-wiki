package cn.fantasticmao.pokemon;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {

    /**
     * -Xms100m -Xmx200m -XX:+HeapDumpOnOutOfMemoryError -XX:+UseConcMarkSweepGC -Dspring.profiles.active=master
     */
    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .properties("spring.profiles.default=snapshot")
                .run(args);
    }
}