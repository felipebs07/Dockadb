package com.felipebs.dockadb_api;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Startup {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        System.out.println("Database Name: " + dotenv.get("MONGO_DB_DATABASE"));
        System.out.println("Username: " + dotenv.get("MONGO_DB_USERNAME"));
        System.out.println("Password: " + dotenv.get("MONGO_DB_PASSWORD"));
        System.out.println("Params: " + dotenv.get("MONGO_DB_PARAMS"));
        SpringApplication.run(Startup.class, args);
    }
}
