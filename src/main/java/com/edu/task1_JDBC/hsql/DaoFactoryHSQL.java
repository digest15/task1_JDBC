package com.edu.task1_JDBC.hsql;

import com.edu.task1_JDBC.dao.GenericDao;
import com.edu.task1_JDBC.dao.DaoFactory;
import com.edu.task1_JDBC.dao.PersistException;

import java.sql.Connection ;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactoryHSQL implements DaoFactory {
    private String user = "SA";
    private String password = "";
    private String url = "jdbc:hsqldb:file:HSQLDB/CarServiceDB";
    private String driver = "org.hsqldb.jdbc.JDBCDriver";

    public DaoFactoryHSQL() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws PersistException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        }catch (SQLException e) {
            throw new PersistException("Ошибка получения соединения", e);
        }
        return connection;
    }

    @Override
    public GenericDao getCarServiceDao(Connection connection) {
        return new CarServiceDao(connection);
    }

    @Override
    public GenericDao getColorDao(Connection connection) {
        return new ColorDao(connection);
    }

    @Override
    public GenericDao getMarkDao(Connection connection) {
        return new MarkDao(connection);
    }

    @Override
    public GenericDao getCarDao(Connection connection) {
        return new CarDao(connection);
    }
}
