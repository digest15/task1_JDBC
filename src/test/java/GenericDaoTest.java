import com.edu.task1_JDBC.dao.GenericDao;
import com.edu.task1_JDBC.dao.PersistException;
import com.edu.task1_JDBC.entity.Identified;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericDaoTest {
    protected GenericDao dao;

    protected abstract Identified createEntity();

    protected abstract GenericDao getDaoTest();

    protected abstract Identified editEntity(Identified entity);

    protected abstract boolean compareEntity(Identified e1, Identified e2);

    private GenericDao getDao() {
        if (dao == null) {
            return getDaoTest();
        } else {
            return dao;
        }
    }

    @Test
    public void testPersist() throws Exception {
        Identified entity = createEntity();
        Assert.assertNull("Ожидаем, что у нового объекта пустой id", entity.getId());

        getDao().persist(entity);

        Assert.assertNotNull("Ожидаем, что после сохранения сущность получит новый id", entity.getId());
    }

    @Test
    public void testPersistAll() throws Exception {
        List<Identified> list = new ArrayList<>();
        list.add(createEntity());
        list.add(createEntity());

        getDao().persistAll(list);

        Assert.assertNotNull("Ожидаем, что после сохранения сущность получит новый id. Объект №1", list.get(0).getId());
        Assert.assertNotNull("Ожидаем, что после сохранения сущность получит новый id. Объект №2", list.get(1).getId());
    }

    @Test
    public void testDelete() throws Exception {
        Identified entnity = createEntity();
        getDao().persist(entnity);
        getDao().delete(entnity);

        Assert.assertNull("Ожидаем, что после удаления прочитаем Null", getDao().readById(entnity.getId()));
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteNotSave() throws Exception {
        Identified entity = createEntity();
        getDao().delete(entity);
    }

    @Test
    public void testReadById() throws Exception{
        Identified entity = createEntity();
        getDao().persist(entity);
        entity = getDao().readById(entity.getId());

        Assert.assertNotNull("Ожидаем, что прочитали сохраненный объект", entity);
    }

    @Test
    public void testReadAll() throws Exception {
        List<Identified> list = new ArrayList<>();
        list.add(createEntity());
        list.add(createEntity());
        getDao().persistAll(list);

        list = null;
        list = getDao().readAll();

        Assert.assertTrue("Ожидали, что прочитаем больше двух элементов", list.size() > 0);
    }

    @Test
    public void testUpdate() throws Exception {
        Identified entity = createEntity();
        getDao().persist(entity);
        entity = editEntity(entity);

        getDao().update(entity);
        Identified entityUpdate = getDao().readById(entity.getId());

        boolean compareResult = compareEntity(entity, entityUpdate);
        Assert.assertTrue("Ожидали, что прочитанный объект будет обновленным", compareResult);

    }
}
