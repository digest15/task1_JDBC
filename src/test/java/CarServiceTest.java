import com.edu.task1_JDBC.dao.DaoFactory;
import com.edu.task1_JDBC.dao.GenericDao;
import com.edu.task1_JDBC.entity.CarService;
import com.edu.task1_JDBC.entity.Identified;
import com.google.common.base.Preconditions;
import org.junit.Assert;
import org.junit.Test;

public class CarServiceTest extends DaoTest {

    public CarServiceTest(DaoFactory factory) throws Exception {
        super(factory);
    }

    @Override
    public GenericDao<?> getDao() {
        if (dao == null) {
            return factory.getCarServiceDao(connection);
        } else {
            return dao;
        }
    }

    @Override
    public Identified createEntity() {
        CarService carService = new CarService();
        carService.setAddress("г.Иркутск, Александровское шоссе 11 км");
        carService.setName("Александровское");
        carService.setOpeningTime(10);
        carService.setClosingTime(20);

        return carService;
    }

    @Override
    public Identified editEntity(Identified entity) {
        Preconditions.checkNotNull(entity);

        CarService carService = (CarService) entity;
        carService.setName("Измененное имя");

        return carService;
    }

    @Override
    public boolean compareEntity(Identified e1, Identified e2) {
        CarService entity1 = (CarService) e1;
        CarService entity2 = (CarService) e2;

        return entity1.getName().equals(entity2.getName());
    }

    @Test(expected = Exception.class)
    public void testNameNotNull() throws Exception {
        CarService carService = (CarService) createEntity();
        carService.setName(null);
        dao.persist(carService);
        Assert.fail("Ожидали, что поле name сущности CarService должно быть обязательным");
    }

    @Test(expected = Exception.class)
    public void testAddressNotNull() throws Exception {
        CarService carService = (CarService) createEntity();
        carService.setAddress(null);
        dao.persist(carService);
        Assert.fail("Ожидали, что поле address сущности CarService должно быть обязательным");
    }
}
