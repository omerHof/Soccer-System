package IO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ActivateServer {

    public static void main(String[] args) {
        ServiceController serviceController= new ServiceController();
        SpringApplication.run(ActivateServer.class, args);

    }

}
