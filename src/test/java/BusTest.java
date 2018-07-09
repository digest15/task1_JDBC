import com.edu.task1_JDBC.dao.DaoFactory;
import com.edu.task1_JDBC.dao.GenericDao;
import com.edu.task1_JDBC.entity.Bus;
import com.edu.task1_JDBC.entity.Identified;
import com.google.common.base.Preconditions;

import java.util.Date;

public class BusTest extends MachineTest {
    public BusTest(DaoFactory factory) throws Exception {
        super(factory);
    }

    @Override
    public Identified createEntity() {
        Bus bus = new Bus();
        bus.setNamePicking("Люкс");
        bus.setColor(null);
        bus.setNumberPassengerSeats(100);
        bus.setNumberPassengerStanding(50);
        bus.setMark(null);
        bus.setReleaseYear(new Date());
        bus.setVin("1234567891234567898");

        return bus;
    }

    @Override
    public GenericDao<?> getDao() {
        if (dao == null) {
            return factory.getBusDao(connection, factory);
        } else {
            return dao;
        }
    }
}
