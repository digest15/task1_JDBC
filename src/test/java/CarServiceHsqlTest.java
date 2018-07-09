import com.edu.task1_JDBC.dao.GenericDao;
import com.edu.task1_JDBC.dao.PersistException;
import com.edu.task1_JDBC.entity.CarService;
import com.edu.task1_JDBC.entity.Identified;

import java.sql.SQLException;

public class CarServiceHsqlTest extends HsqlDaoTest {

    @Override
    protected Identified createEntity() {
        CarService carService = new CarService();
        carService.setAddress("г.Иркутск, Александровское шоссе 11 км");
        carService.setName("Александровское");
        carService.setOpeningTime(10);
        carService.setClosingTime(20);

        return carService;
    }

    @Override
    protected Identified editEntity(Identified entity) {
        CarService carService = (CarService) entity;
        carService.setName("Измененное имя");

        return carService;
    }

    @Override
    protected boolean compareEntity(Identified e1, Identified e2) {
        CarService entity1 = (CarService) e1;
        CarService entity2 = (CarService) e2;

        return entity1.getName().equals(entity2.getName());
    }

    @Override
    protected GenericDao getDaoTest() {
        return factory.getCarServiceDao(connection);
    }
}
