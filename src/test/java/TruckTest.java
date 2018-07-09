import com.edu.task1_JDBC.dao.DaoFactory;
import com.edu.task1_JDBC.dao.GenericDao;
import com.edu.task1_JDBC.entity.Identified;
import com.edu.task1_JDBC.entity.Truck;

import java.util.Date;

public class TruckTest extends MachineTest {
    public TruckTest(DaoFactory factory) throws Exception {
        super(factory);
    }

    @Override
    public Identified createEntity() {
        Truck truck = new Truck();
        truck.setNamePicking("Люкс");
        truck.setNumberAxle(50);
        truck.setColor(null);
        truck.setMark(null);
        truck.setReleaseYear(new Date());
        truck.setVin("1234567891234567898");

        return truck;
    }

    @Override
    public GenericDao<?> getDao() {
        if (dao == null) {
            return factory.getTruckDao(connection, factory);
        } else {
            return dao;
        }
    }
}
