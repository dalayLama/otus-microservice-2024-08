package userprofile;

import org.otus.microservice.userprofile.App;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProfileTestApp {

    public static void main(String[] args) {
        SpringApplication.from(App::main)
                .with(ContainersConfiguration.class)
                .run(args);
    }

}
