package co.com.ancas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@ComponentScan("co.com.ancas.*")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}