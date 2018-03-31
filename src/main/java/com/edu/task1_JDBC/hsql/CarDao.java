package com.edu.task1_JDBC.hsql;

import com.edu.task1_JDBC.entity.Car;
import com.edu.task1_JDBC.entity.Machine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarDao extends MachineDao {
    public CarDao(Connection connection) {
        super(connection);
    }

    @Override
    protected void fillStatementForPersistForChild(PreparedStatement statement, Machine object) throws SQLException {
        Car car = (Car) object;
        statement.setInt(6, car.getNumberPassengerSeats());
    }

    @Override
    protected Machine parseResultSetForChild(ResultSet result) throws SQLException {
        Car car = new Car();
        car.setNumberPassengerSeats(result.getInt("numberPassengerSeats"));
        return car;
    }
}
