import com.edu.task1_JDBC.dao.DaoFactory;
import com.edu.task1_JDBC.dao.GenericDao;
import com.edu.task1_JDBC.entity.Identified;
import com.edu.task1_JDBC.entity.Mechanic;
import com.google.common.base.Preconditions;

import java.math.BigDecimal;

public class MechanicTest extends DaoTest {
    public MechanicTest(DaoFactory factory) throws Exception {
        super(factory);
    }

    @Override
    public Identified createEntity() {
        Mechanic mechanic = new Mechanic();
        mechanic.setName("Иван");
        mechanic.setLastName("Иванов");
        mechanic.setSalary(new BigDecimal(10000));

        return mechanic;
    }

    @Override
    public Identified editEntity(Identified entity) {
        Preconditions.checkNotNull(entity);

        Mechanic mechanic = (Mechanic) entity;
        mechanic.setName("Новое имя");

        return mechanic;
    }

    @Override
    public boolean compareEntity(Identified e1, Identified e2) {
        Mechanic mechanic1 = (Mechanic) e1;
        Mechanic mechanic2 = (Mechanic) e2;

        return mechanic1.getName().equals(mechanic2.getName());
    }

    @Override
    public GenericDao<?> getDao() {
        if (dao == null) {
            return factory.getMehanicDao(connection);
        } else {
            return dao;
        }
    }
}
