import com.edu.task1_JDBC.dao.DaoFactory;
import com.edu.task1_JDBC.dao.GenericDao;
import com.edu.task1_JDBC.entity.Color;
import com.edu.task1_JDBC.entity.Identified;
import com.google.common.base.Preconditions;
import org.junit.Assert;
import org.junit.Test;

public class ColorTest extends DaoTest {
    public ColorTest(DaoFactory factory) throws Exception {
        super(factory);
    }

    @Override
    public Identified createEntity() {
        Color color = new Color();
        color.setName("Красный");
        color.setRgb("255 0 0");

        return color;
    }

    @Override
    public Identified editEntity(Identified entity) {
        Preconditions.checkNotNull(entity);

        Color color = (Color) entity;
        color.setName("Зеленый");

        return color;
    }

    @Override
    public boolean compareEntity(Identified e1, Identified e2) {
        Color color1 = (Color) e1;
        Color color2 = (Color) e2;

        return color1.getName().equals(color2.getName());
    }

    @Override
    public GenericDao<?> getDao() {
        if (dao == null) {
            return factory.getColorDao(connection);
        } else {
            return dao;
        }
    }

    @Test(expected = Exception.class)
    public void TestNameNotNull() throws Exception {
        Color color = (Color) createEntity();
        color.setName(null);
        dao.persist(color);
        Assert.fail("Ожидали, что поле name сущности Color должно быть обязательным");
    }

    @Test(expected = Exception.class)
    public void TestRgbNotNull() throws Exception {
        Color color = (Color) createEntity();
        color.setRgb(null);
        dao.persist(color);
        Assert.fail("Ожидали, что поле rgb сущности Color должно быть обязательным");
    }
}
