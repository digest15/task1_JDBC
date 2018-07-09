import com.edu.task1_JDBC.dao.DaoFactory;
import com.edu.task1_JDBC.dao.GenericDao;
import com.edu.task1_JDBC.entity.Identified;
import com.edu.task1_JDBC.entity.Repair;
import com.edu.task1_JDBC.helpers.DateHelper;
import com.google.common.base.Preconditions;

public class RepairTest extends DaoTest {
    public RepairTest(DaoFactory factory) throws Exception {
        super(factory);
    }

    @Override
    public Identified createEntity() {
        Repair repair = new Repair();
        repair.setDateTime(DateHelper.randomDate());
        repair.setCarServise(null);
        repair.setMechanic(null);
        repair.setMachine(null);
        repair.setAmount(null);

        return repair;
    }

    @Override
    public Identified editEntity(Identified entity) {
        Preconditions.checkNotNull(entity);

        Repair repair = (Repair) entity;
        repair.setDateTime(DateHelper.randomDate());

        return repair;
    }

    @Override
    public boolean compareEntity(Identified e1, Identified e2) {
        Repair repair1 = (Repair) e1;
        Repair repair2 = (Repair) e2;

        return repair1.getDateTime().equals(repair2.getDateTime());
    }

    @Override
    public GenericDao<?> getDao() {
        if (dao == null) {
            return factory.getRepairDao(connection, factory);
        } else {
            return dao;
        }
    }
}
