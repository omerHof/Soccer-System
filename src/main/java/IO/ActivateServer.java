package IO;

import DataBase.DataBase;
import SystemLogic.DBLocal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ActivateServer {

    public static void main(String[] args) {
        //ServiceController serviceController= new ServiceController();
        //DataBase dataBase = new DataBase();
        //DBLocal.getInstance().writeToMongo();
        SpringApplication.run(ActivateServer.class, args);
    }

}
