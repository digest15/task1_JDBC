package com.edu.task1_JDBC.dao;

import java.sql.Connection;

public interface DaoFactory {
    public Connection getConnection() throws PersistException;

    public GenericDao getCarServiceDao(Connection connection);

    public GenericDao getColorDao(Connection connection);

    public GenericDao getMarkDao(Connection connection);

    public GenericDao getCarDao(Connection connection, DaoFactory factory);

    public GenericDao getBusDao(Connection connection, DaoFactory factory);

    public GenericDao getTruckDao(Connection connection, DaoFactory factory);

    public GenericDao getMehanicDao(Connection connection);

    public GenericDao getRepairDao(Connection connection, DaoFactory factory);
}
