package auth;

import org.otus.microservice.auth.App;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthTestApp {

    public static void main(String[] args) {
        SpringApplication.from(App::main)
                .with(ContainersConfiguration.class)
                .run(args);
    }

}
