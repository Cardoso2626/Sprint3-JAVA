package br.com.fiap.sptrint1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class Sptrint1JavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sptrint1JavaApplication.class, args);
    }

}
