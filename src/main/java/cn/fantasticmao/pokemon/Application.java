package cn.fantasticmao.pokemon;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder)
                .sources(Application.class)
                .properties("spring.profiles.default=snapshot");
    }

    /**
     * java -Xms100m -Xmx200m -XX:+HeapDumpOnOutOfMemoryError -XX:+UseConcMarkSweepGC -Dfile.encoding=UTF-8 -Dspring.profiles.active=master -jar pokemon-wiki-1.0.jar >> /var/log/pokemon-wiki/tomcat.log &
     */
    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .properties("spring.profiles.default=snapshot")
                .run(args);
    }
}