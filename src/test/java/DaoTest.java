import com.edu.task1_JDBC.dao.DaoFactory;
import com.edu.task1_JDBC.dao.GenericDao;
import com.edu.task1_JDBC.entity.Identified;
import com.edu.task1_JDBC.hsql.DaoFactoryHSQL;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public abstract class DaoTest {
    protected DaoFactory factory;
    protected Connection connection;
    protected GenericDao dao;

    public abstract Identified createEntity();

    public abstract Identified editEntity(Identified entity);

    public abstract boolean compareEntity(Identified e1, Identified e2);

    public abstract GenericDao<?> getDao();

    @Parameterized.Parameters
    public static Collection getParameters() {
        return Arrays.asList(new Object[][]{
                {new DaoFactoryHSQL()},
        });
    }

    public DaoTest(DaoFactory factory) throws Exception {
        this.factory = factory;

        connection = factory.getConnection();
        dao = getDao();
    }

    @Before
    public void setUp() throws Exception {
        connection = factory.getConnection();
        connection.setAutoCommit(false);
    }

    @After
    public void tearDown() throws SQLException {
        connection.rollback();
        connection.close();
    }

    @Test
    public void testPersist() throws Exception {
        Identified entity = createEntity();
        Assert.assertNull("Ожидаем, что у нового объекта пустой id", entity.getId());

        dao.persist(entity);

        Assert.assertNotNull("Ожидаем, что после сохранения сущность получит новый id", entity.getId());
    }

    @Test
    public void testPersistAll() throws Exception {
        List<Identified> list = new ArrayList<>();
        list.add(createEntity());
        list.add(createEntity());

        dao.persistAll(list);

        Assert.assertNotNull("Ожидаем, что после сохранения сущность получит новый id. Объект №1", list.get(0).getId());
        Assert.assertNotNull("Ожидаем, что после сохранения сущность получит новый id. Объект №2", list.get(1).getId());
    }

    @Test
    public void testDelete() throws Exception {
        Identified entnity = createEntity();
        dao.persist(entnity);
        dao.delete(entnity);

        Assert.assertNull("Ожидаем, что после удаления прочитаем Null", dao.readById(entnity.getId()));
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteNotSave() throws Exception {
        Identified entity = createEntity();
        dao.delete(entity);
    }

    @Test
    public void testReadById() throws Exception {
        Identified entity = createEntity();
        dao.persist(entity);
        entity = dao.readById(entity.getId());

        Assert.assertNotNull("Ожидаем, что прочитали сохраненный объект", entity);
    }

    @Test
    public void testReadAll() throws Exception {
        List<Identified> list = new ArrayList<>();
        list.add(createEntity());
        list.add(createEntity());
        dao.persistAll(list);

        list = null;
        list = dao.readAll();

        Assert.assertTrue("Ожидали, что прочитаем больше двух элементов", list.size() > 0);
    }

    @Test
    public void testUpdate() throws Exception {
        Identified entity = createEntity();
        dao.persist(entity);
        entity = editEntity(entity);

        dao.update(entity);
        Identified entityUpdate = dao.readById(entity.getId());

        boolean compareResult = compareEntity(entity, entityUpdate);
        Assert.assertTrue("Ожидали, что прочитанный объект будет обновленным", compareResult);

    }
}
