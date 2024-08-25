package com.felipebs.dockadb_api;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Startup {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        String database = dotenv.get("MONGO_DB_DATABASE", System.getenv("MONGO_DB_DATABASE"));
        SpringApplication.run(Startup.class, args);
    }
}
