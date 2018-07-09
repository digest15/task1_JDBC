import com.edu.task1_JDBC.dao.DaoFactory;
import com.edu.task1_JDBC.dao.GenericDao;
import com.edu.task1_JDBC.entity.Identified;
import com.edu.task1_JDBC.entity.Mark;
import com.google.common.base.Preconditions;
import org.junit.Assert;
import org.junit.Test;

public class MarkTest extends DaoTest {
    public MarkTest(DaoFactory factory) throws Exception {
        super(factory);
    }

    @Override
    public Identified createEntity() {
        Mark mark = new Mark();
        mark.setManufacturer("Audi");
        return mark;
    }

    @Override
    public Identified editEntity(Identified entity) {
        Preconditions.checkNotNull(entity);

        Mark mark = (Mark) entity;
        mark.setManufacturer("ЛуАЗ");
        return mark;
    }

    @Override
    public boolean compareEntity(Identified e1, Identified e2) {
        Mark mark1 = (Mark) e1;
        Mark mark2 = (Mark) e2;
        return mark1.getManufacturer().equals(mark2.getManufacturer());
    }

    @Override
    public GenericDao<?> getDao() {
        if (dao == null) {
            return factory.getMarkDao(connection);
        } else {
            return dao;
        }
    }

    @Test(expected = Exception.class)
    public void testManufacturerNotNull() throws Exception {
        Mark ma = (Mark) createEntity();
        ma.setManufacturer(null);
        dao.persist(ma);
        Assert.fail("Ожидали, что поле manufacturer сущности Mark должно быть обязательным");
    }

}
