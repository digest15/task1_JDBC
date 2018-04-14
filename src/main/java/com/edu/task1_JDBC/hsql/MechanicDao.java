package com.edu.task1_JDBC.hsql;

import com.edu.task1_JDBC.dao.jdbc.AbstractJdbcDao;
import com.edu.task1_JDBC.entity.Mechanic;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MechanicDao extends AbstractJdbcDao<Mechanic> {
    public MechanicDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String QueryGetAll() {
        return "SELECT * FROM MECHANIC;";
    }

    @Override
    protected String QueryGetReadById() {
        return "SELECT * FROM MECHANIC WHERE id = ?;";
    }

    @Override
    protected String QueryUpdate() {
        return "UPDATE MECHANIC SET name=?, lastName=?, salary=? WHERE id=?;";
    }

    @Override
    protected String QueryDelete() {
        return "DELETE FROM MECHANIC WHERE id = ?";
    }

    @Override
    protected String QueryPersist() {
        return "INSERT INTO MECHANIC(name, lastName, salary) VALUES(?,?,?)";
    }

    @Override
    protected void fillStatementForPersist(PreparedStatement statement, Mechanic object) throws SQLException {
        statement.setString(1, object.getName());
        statement.setString(2, object.getLastName());
        statement.setBigDecimal(3, object.getSalary());
    }

    @Override
    protected void fillStatementForUpdate(PreparedStatement statement, Mechanic object) throws SQLException {
        fillStatementForPersist(statement, object);
        statement.setInt(4, object.getId());
    }

    @Override
    protected List<Mechanic> parseResultSet(ResultSet result) throws SQLException {
        List<Mechanic> list = new ArrayList<>();
        while (result.next()) {
            Mechanic mechanic = new Mechanic();
            mechanic.setId(result.getInt("id"));
            mechanic.setName(result.getString("name"));
            mechanic.setLastName(result.getString("lastName"));
            mechanic.setSalary(result.getBigDecimal("salary"));

            list.add(mechanic);
        }

        return list;
    }
}
