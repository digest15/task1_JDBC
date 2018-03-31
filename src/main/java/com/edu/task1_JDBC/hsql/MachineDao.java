package com.edu.task1_JDBC.hsql;

import com.edu.task1_JDBC.dao.jdbc.AbstractJdbcDao;
import com.edu.task1_JDBC.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class MachineDao extends AbstractJdbcDao<Machine> {
    public MachineDao(Connection connection) {
        super(connection);
    }

    protected abstract void fillStatementForPersistForChild(PreparedStatement statement, Machine object) throws SQLException;

    protected abstract Machine parseResultSetForChild(ResultSet result) throws SQLException;

    @Override
    protected String QueryGetAll() {
        return "SELECT * FROM MACHINE;";
    }

    @Override
    protected String QueryGetReadById() {
        return "SELECT * FROM MACHINE WHERE id = ?;";
    }

    @Override
    protected String QueryUpdate() {
        return "UPDATE MACHINE SET namePiking=?, vin=?, releaseYear=?, color_id=?, mark_id=?, numberPassengerSeats=?, numberPassengerStanding=?, numberAxle=?, capacity=? WHERE id=?;";
    }

    @Override
    protected String QueryDelete() {
        return "DELETE FROM MACHINE WHERE id = ?";
    }

    @Override
    protected String QueryPersist() {
        return "INSERT INTO MACHINE(namePiking, vin, releaseYear, color_id, mark_id, numberPassengerSeats, numberPassengerStanding, numberAxle, capacity) VALUES(?,?,?,?,?,?,?,?,?)";
    }

    @Override
    protected void fillStatementForPersist(PreparedStatement statement, Machine object) throws SQLException {
        statement.setString(1, object.getNamePicking());
        statement.setString(2, object.getVin());
        statement.setDate(3, new java.sql.Date(object.getReleaseYear().getTime()));

        Color color = object.getColor();
        if (color == null){
            statement.setNull(4, Types.NULL);
        }else {
            Integer color_id = color.getId();
            if (color_id != null) {
                statement.setInt(4, color_id);
            }else {
                throw new SQLException(String.format("Перед сохранением объекта %s нужно сохранить объект %s", object.getClass().getName(), color.getClass().getName()));
            }
        }

        Mark mark = object.getMark();
        if (object.getMark() == null) {
            statement.setNull(5, Types.NULL);
        }else {
            Integer mark_id = mark.getId();
            if (mark_id != null) {
                statement.setInt(5, mark_id);
            } else {
                throw new SQLException(String.format("Перед сохранением объекта %s нужно сохранить объект %s", object.getClass().getName(), mark.getClass().getName()));
            }
        }

        statement.setNull(6, Types.NULL);
        statement.setNull(7, Types.NULL);
        statement.setNull(8, Types.NULL);
        statement.setNull(9, Types.NULL);

        fillStatementForPersistForChild(statement, object);
    }

    @Override
    protected void fillStatementForUpdate(PreparedStatement statement, Machine object) throws SQLException {
        fillStatementForPersist(statement, object);
        statement.setInt(10, object.getId());
    }

    @Override
    protected List<Machine> parseResultSet(ResultSet result) throws SQLException {
        List<Machine> list = new ArrayList<>();
        while (result.next()) {
            Machine machine = parseResultSetForChild(result);
            machine.setId(result.getInt("id"));
            machine.setNamePicking(result.getString("namePiking"));
            machine.setVin(result.getString("vin"));
            machine.setReleaseYear(new java.util.Date(result.getDate("releaseYear").getTime()));

            list.add(machine);
        }

        return list;
    }
}
