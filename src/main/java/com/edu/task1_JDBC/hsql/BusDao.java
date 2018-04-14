package com.edu.task1_JDBC.hsql;

import com.edu.task1_JDBC.dao.DaoFactory;
import com.edu.task1_JDBC.entity.Bus;
import com.edu.task1_JDBC.entity.TypeCar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BusDao extends AbstractMachineDao<Bus> {
    public BusDao(Connection connection, DaoFactory factory) {
        super(connection, factory);
    }

    @Override
    protected void fillStatementForPersistForChild(PreparedStatement statement, Bus object) throws SQLException {
        statement.setInt(6, object.getNumberPassengerSeats());
        statement.setInt(7, object.getNumberPassengerStanding());
    }

    @Override
    protected Bus parseResultSetForChild(ResultSet result) throws SQLException {
        Bus bus = new Bus();
        bus.setNumberPassengerSeats(result.getInt("numberPassengerSeats"));
        bus.setNumberPassengerStanding(result.getInt("numberPassengerStanding"));
        return bus;
    }

    @Override
    protected TypeCar returnTypeCar() {
        return TypeCar.Bus;
    }
}
