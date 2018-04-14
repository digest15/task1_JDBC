package com.edu.task1_JDBC.hsql;

import com.edu.task1_JDBC.dao.DaoFactory;
import com.edu.task1_JDBC.entity.Car;
import com.edu.task1_JDBC.entity.TypeCar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarDao extends AbstractMachineDao<Car> {
    public CarDao(Connection connection, DaoFactory factory) {
        super(connection, factory);
    }

    @Override
    protected void fillStatementForPersistForChild(PreparedStatement statement, Car object) throws SQLException {
        statement.setInt(6, object.getNumberPassengerSeats());
    }

    @Override
    protected Car parseResultSetForChild(ResultSet result) throws SQLException {
        Car car = new Car();
        car.setNumberPassengerSeats(result.getInt("numberPassengerSeats"));
        return car;
    }

    @Override
    protected TypeCar returnTypeCar() {
        return TypeCar.Car;
    }
}
