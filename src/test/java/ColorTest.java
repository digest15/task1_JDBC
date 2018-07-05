import com.edu.task1_JDBC.dao.GenericDao;
import com.edu.task1_JDBC.entity.Color;
import com.edu.task1_JDBC.entity.Identified;
import com.google.common.base.Preconditions;

public class ColorHsqlTest extends HsqlDaoTest {
    @Override
    protected Identified createEntity() {
        Color color = new Color();
        color.setName("Красный");
        color.setRgb("255 0 0");

        return color;
    }

    @Override
    protected GenericDao getDaoTest() {
        return factory.getColorDao(connection);
    }

    @Override
    protected Identified editEntity(Identified entity) {
        Preconditions.checkNotNull(entity);

        Color color = (Color) entity;
        color.setName("Зеленый");

        return color;
    }

    @Override
    protected boolean compareEntity(Identified e1, Identified e2) {
        Color color1 = (Color) e1;
        Color color2 = (Color) e2;

        return color1.getName().equals(color2.getName());
    }
}
