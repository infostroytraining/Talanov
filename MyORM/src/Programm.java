import entity.Car;
import entity.Dog;

import java.io.IOException;
import java.sql.SQLException;

public class Programm {

    public static void main(String[] args) throws IOException, SQLException {
        ORM.createTable(Dog.class);
        ORM.createTable(Car.class);
    }
}
