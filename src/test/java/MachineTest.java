import com.edu.task1_JDBC.dao.DaoFactory;
import com.edu.task1_JDBC.dao.GenericDao;
import com.edu.task1_JDBC.dao.PersistException;
import com.edu.task1_JDBC.entity.Car;
import com.edu.task1_JDBC.entity.Identified;
import com.edu.task1_JDBC.entity.Machine;
import com.google.common.base.Preconditions;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public abstract class MachineTest extends DaoTest {
    public MachineTest(DaoFactory factory) throws Exception {
        super(factory);
    }

    @Override
    public Identified editEntity(Identified entity) {
        Preconditions.checkNotNull(entity);

        Machine machine = (Machine) entity;
        machine.setVin("999999999999999999");
        return machine;
    }

    @Override
    public boolean compareEntity(Identified e1, Identified e2) {
        Machine machine1 = (Machine) e1;
        Machine machine2 = (Machine) e2;

        return machine1.getVin().equals(machine2.getVin());
    }

    @Test(expected = Exception.class)
    public void testTypeCarNotNull() throws Exception {
        Identified entity = createEntity();
        Machine machine = (Machine) entity;
        machine.setTypeCar(null);
        dao.persist(machine);

        String errMsg = String.format("Ожидали, что поле typeCar сущности %s должно быть обязательным", entity.getClass());
        Assert.fail(errMsg);
    }

    @Test(expected = PersistException.class)
    public void testNamePikingNotNull() throws Exception {
        Identified entity = createEntity();
        Machine machine = (Machine) entity;
        machine.setNamePicking(null);
        dao.persist(machine);

        String errMsg = String.format("Ожидали, что поле namePiking сущности %s должно быть обязательным", entity.getClass());
        Assert.fail(errMsg);
    }

    @Test(expected = PersistException.class)
    public void testVinNotNull() throws Exception {
        Identified entity = createEntity();
        Machine machine = (Machine) entity;
        machine.setVin(null);
        dao.persist(machine);

        String errMsg = String.format("Ожидали, что поле vin сущности %s должно быть обязательным", entity.getClass());
        Assert.fail(errMsg);
    }

    @Test(expected = Exception.class)
    public void TestReleaseYearNotNull() throws Exception {
        Identified entity = createEntity();
        Machine machine = (Machine) entity;
        machine.setReleaseYear(null);
        dao.persist(machine);

        String errMsg = String.format("Ожидали, что поле releaseYear сущности %s должно быть обязательным", entity.getClass());
        Assert.fail(errMsg);
    }
}
