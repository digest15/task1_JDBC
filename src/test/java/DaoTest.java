import com.edu.task1_JDBC.dao.DaoFactory;
import com.edu.task1_JDBC.dao.GenericDao;
import com.edu.task1_JDBC.dao.PersistException;
import com.edu.task1_JDBC.entity.Identified;
import com.edu.task1_JDBC.hsql.DaoFactoryHSQL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoTest {
    private DaoFactory factory;
    private Connection connection;
    private GenericDao dao;
    private EntityTester tester;



    @Before
    public void setUp() throws SQLException {
        connection.setAutoCommit(false);
    }

    @After
    public void tearDown() throws SQLException {
        connection.rollback();
        connection.close();
    }

    @Test
    public void testPersist() throws Exception {
        Identified entity = tester.createEntity();
        Assert.assertNull("Ожидаем, что у нового объекта пустой id", entity.getId());

        dao.persist(entity);

        Assert.assertNotNull("Ожидаем, что после сохранения сущность получит новый id", entity.getId());
    }

    @Test
    public void testPersistAll() throws Exception {
        List<Identified> list = new ArrayList<>();
        list.add(tester.createEntity());
        list.add(tester.createEntity());

        dao.persistAll(list);

        Assert.assertNotNull("Ожидаем, что после сохранения сущность получит новый id. Объект №1", list.get(0).getId());
        Assert.assertNotNull("Ожидаем, что после сохранения сущность получит новый id. Объект №2", list.get(1).getId());
    }

    @Test
    public void testDelete() throws Exception {
        Identified entnity = tester.createEntity();
        dao.persist(entnity);
        dao.delete(entnity);

        Assert.assertNull("Ожидаем, что после удаления прочитаем Null", dao.readById(entnity.getId()));
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteNotSave() throws Exception {
        Identified entity = tester.createEntity();
        dao.delete(entity);
    }

    @Test
    public void testReadById() throws Exception{
        Identified entity = tester.createEntity();
        dao.persist(entity);
        entity = dao.readById(entity.getId());

        Assert.assertNotNull("Ожидаем, что прочитали сохраненный объект", entity);
    }

    @Test
    public void testReadAll() throws Exception {
        List<Identified> list = new ArrayList<>();
        list.add(tester.createEntity());
        list.add(tester.createEntity());
        dao.persistAll(list);

        list = null;
        list = dao.readAll();

        Assert.assertTrue("Ожидали, что прочитаем больше двух элементов", list.size() > 0);
    }

    @Test
    public void testUpdate() throws Exception {
        Identified entity = tester.createEntity();
        dao.persist(entity);
        entity = tester.editEntity(entity);

        dao.update(entity);
        Identified entityUpdate = dao.readById(entity.getId());

        boolean compareResult = tester.compareEntity(entity, entityUpdate);
        Assert.assertTrue("Ожидали, что прочитанный объект будет обновленным", compareResult);

    }
}
