package com.edu.task1_JDBC.hsql;

import com.edu.task1_JDBC.dao.DaoFactory;
import com.edu.task1_JDBC.entity.Machine;
import com.edu.task1_JDBC.entity.Truck;
import com.edu.task1_JDBC.entity.TypeCar;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TruckDao extends AbstractMachineDao {
    public TruckDao(Connection connection, DaoFactory factory) {
        super(connection, factory);
    }

    @Override
    protected void fillStatementForPersistForChild(PreparedStatement statement, Machine object) throws SQLException {
        Truck truck = (Truck) object;
        statement.setInt(8, truck.getNumberAxle());
        statement.setInt(9, truck.getCapacity());
    }

    @Override
    protected Machine parseResultSetForChild(ResultSet result) throws SQLException {
        Truck truck = new Truck();
        truck.setCapacity(result.getInt("numberAxle"));
        truck.setNumberAxle(result.getInt("capacity"));
        return truck;
    }

    @Override
    protected TypeCar returnTypeCar() {
        return TypeCar.Truck;
    }
}
