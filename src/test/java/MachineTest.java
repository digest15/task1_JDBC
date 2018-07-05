import com.edu.task1_JDBC.dao.DaoFactory;
import com.edu.task1_JDBC.dao.GenericDao;
import com.edu.task1_JDBC.entity.Car;
import com.edu.task1_JDBC.entity.Identified;
import com.google.common.base.Preconditions;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class CarTest extends DaoTest {
    public CarTest(DaoFactory factory) throws Exception {
        super(factory);
    }

    @Override
    public GenericDao<?> getDao() {
        if (dao == null) {
            return factory.getCarDao(connection, factory);
        } else {
            return dao;
        }
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
    public Identified editEntity(Identified entity) {
        Preconditions.checkNotNull(entity);

        Car car = (Car) entity;
        car.setVin("999999999999999999");
        return car;
    }

    @Override
    public boolean compareEntity(Identified e1, Identified e2) {
        Car car1 = (Car) e1;
        Car car2 = (Car) e2;

        return car1.getVin().equals(car2.getVin());
    }

    @Test(expected = Exception.class)
    public void testTypeCarNotNull() throws Exception {
        Car car = (Car) createEntity();
        car.setTypeCar(null);
        dao.persist(car);
        Assert.fail("Ожидали, что поле typeCar сущности Car должно быть обязательным");
    }

    @Test(expected = Exception.class)
    public void testNamePikingNotNull() throws Exception {
        Car car = (Car) createEntity();
        car.setNamePicking(null);
        dao.persist(car);
        Assert.fail("Ожидали, что поле namePiking сущности Car должно быть обязательным");
    }

    @Test(expected = Exception.class)
    public void testVinNotNull() throws Exception {
        Car car = (Car) createEntity();
        car.setVin(null);
        dao.persist(car);
        Assert.fail("Ожидали, что поле vin сущности Car должно быть обязательным");
    }

    @Test(expected = Exception.class)
    public void TestReleaseYearNotNull() throws Exception {
        Car car = (Car) createEntity();
        car.setReleaseYear(null);
        dao.persist(car);
        Assert.fail("Ожидали, что поле releaseYear сущности Car должно быть обязательным");
    }
}
