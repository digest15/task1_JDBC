import com.edu.task1_JDBC.dao.DaoFactory;
import com.edu.task1_JDBC.dao.PersistException;
import com.edu.task1_JDBC.hsql.DaoFactoryHSQL;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.sql.Connection;
import java.sql.SQLException;


public abstract class HsqlDaoTest extends GenericDaoTest {
    public static final DaoFactory factory = new DaoFactoryHSQL();
    protected Connection connection;

    public HsqlDaoTest()  {
        try {
            this.connection = factory.getConnection();
        } catch (PersistException e) {

        }
    }

    @Before
    public void setUp() throws SQLException {
        connection.setAutoCommit(false);
    }

    @After
    public void tearDown() throws SQLException {
        connection.rollback();
        connection.close();
    }

}
