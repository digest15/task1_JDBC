package com.edu.task1_JDBC.hsql;

import com.edu.task1_JDBC.dao.PersistException;
import com.edu.task1_JDBC.dao.jdbc.AbstractJdbcDao;
import com.edu.task1_JDBC.entity.CarService;
import com.google.common.base.Preconditions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarServiceDao extends AbstractJdbcDao<CarService> {

    public CarServiceDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String QueryUpdate() {
        return "UPDATE CARSERVICE SET name=?, address=?, openingTime=?, closingTime=? WHERE id=?;";
    }

    @Override
    protected String QueryDelete() {
        return "DELETE FROM CARSERVICE WHERE id = ?";
    }

    @Override
    protected String QueryPersist() {
        return "INSERT INTO CARSERVICE(name, address, openingTime, closingTime) VALUES(?,?,?,?)";
    }

    @Override
    protected String QueryGetAll() {
        return "SELECT * FROM CARSERVICE;";
    }

    @Override
    protected String QueryGetReadById() {
        return "SELECT * FROM CARSERVICE WHERE id = ?;";
    }

    @Override
    protected void fillStatementForPersist(PreparedStatement statement, CarService object) throws SQLException{
        statement.setString(1, object.getName());
        statement.setString(2, object.getAddress());
        statement.setInt(3, object.getOpeningTime());
        statement.setInt(4, object.getClosingTime());
    }

    @Override
    protected void fillStatementForUpdate(PreparedStatement statement, CarService object) throws SQLException {
        fillStatementForPersist(statement, object);
        statement.setInt(5, object.getId());
    }

    @Override
    protected List<CarService> parseResultSet(ResultSet result) throws SQLException {
        List<CarService> list = new ArrayList<>();
        while (result.next()) {
            CarService carService = new CarService();
            carService.setId(result.getInt("id"));
            carService.setName(result.getString("name"));
            carService.setAddress(result.getString("address"));
            carService.setOpeningTime(result.getInt("openingTime"));
            carService.setClosingTime(result.getInt("closingTime"));

            list.add(carService);
        }

        return list;
    }

}
