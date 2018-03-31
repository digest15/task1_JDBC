package com.edu.task1_JDBC.hsql;

import com.edu.task1_JDBC.dao.jdbc.AbstractJdbcDao;
import com.edu.task1_JDBC.entity.Identified;
import com.edu.task1_JDBC.entity.Mark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarkDao extends AbstractJdbcDao<Mark> {
    public MarkDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String QueryGetAll() {
        return "SELECT * FROM MARK;";
    }

    @Override
    protected String QueryGetReadById() {
        return "SELECT * FROM MARK WHERE id = ?;";
    }

    @Override
    protected String QueryUpdate() {
        return "UPDATE MARK SET manufacturer=? WHERE id=?;";
    }

    @Override
    protected String QueryDelete() {
        return "DELETE FROM MARK WHERE id = ?";
    }

    @Override
    protected String QueryPersist() {
        return "INSERT INTO MARK(manufacturer) VALUES(?)";
    }

    @Override
    protected void fillStatementForPersist(PreparedStatement statement, Mark object) throws SQLException {
        statement.setString(1, object.getManufacturer());
    }

    @Override
    protected void fillStatementForUpdate(PreparedStatement statement, Mark object) throws SQLException {
        statement.setString(1, object.getManufacturer());
        statement.setInt(2, object.getId());
    }

    @Override
    protected List<Mark> parseResultSet(ResultSet result) throws SQLException {
        List<Mark> list = new ArrayList<>();
        while (result.next()) {
            Mark mark = new Mark();
            mark.setId(result.getInt("id"));
            mark.setManufacturer(result.getString("manufacturer"));

            list.add(mark);
        }

        return list;
    }
}
