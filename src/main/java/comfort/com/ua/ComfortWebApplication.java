package comfort.com.ua;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ComfortWebApplication {

    public static void main(String[] args) {

        SpringApplication.run(ComfortWebApplication.class, args);

    }

}
