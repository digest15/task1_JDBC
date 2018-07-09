import com.edu.task1_JDBC.dao.DaoFactory;
import com.edu.task1_JDBC.dao.GenericDao;
import com.edu.task1_JDBC.entity.Car;
import com.edu.task1_JDBC.entity.Identified;

import java.util.Date;

public class CarTest extends MachineTest {
    public CarTest(DaoFactory factory) throws Exception {
        super(factory);
    }

    @Override
    public Identified createEntity() {
        Car car = new Car();
        car.setNamePicking("Люкс");
        car.setColor(null);
        car.setNumberPassengerSeats(5);
        car.setMark(null);
        car.setReleaseYear(new Date());
        car.setVin("1234567891234567898");

        return car;
    }

    @Override
    public GenericDao<?> getDao() {
        if (dao == null) {
            return factory.getCarDao(connection, factory);
        } else {
            return dao;
        }
    }
}
