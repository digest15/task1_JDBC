package com.edu.task1_JDBC.hsql;

import com.edu.task1_JDBC.dao.jdbc.AbstractJdbcDao;
import com.edu.task1_JDBC.entity.Color;
import com.edu.task1_JDBC.entity.Identified;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ColorDao extends AbstractJdbcDao<Color> {
    public ColorDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String QueryGetAll() {
        return "SELECT * FROM COLOR;";
    }

    @Override
    protected String QueryGetReadById() {
        return "SELECT * FROM COLOR WHERE id = ?;";
    }

    @Override
    protected String QueryUpdate() {
        return "UPDATE COLOR SET rgb=?, name=? WHERE id=?;";
    }

    @Override
    protected String QueryDelete() {
        return "DELETE FROM COLOR WHERE id = ?";
    }

    @Override
    protected String QueryPersist() {
        return "INSERT INTO COLOR(name, rgb) VALUES(?,?)";
    }

    @Override
    protected void fillStatementForPersist(PreparedStatement statement, Color object) throws SQLException {
        statement.setString(1, object.getName());
        statement.setString(2, object.getRgb());
    }

    @Override
    protected void fillStatementForUpdate(PreparedStatement statement, Color object) throws SQLException {
        fillStatementForPersist(statement, object);
        statement.setInt(3,object.getId());
    }

    @Override
    protected List<Color> parseResultSet(ResultSet result) throws SQLException {
        List<Color> list = new ArrayList<>();
        while (result.next()) {
            Color color = new Color();
            color.setId(result.getInt("id"));
            color.setName(result.getString("name"));
            color.setRgb(result.getString("rgb"));

            list.add(color);
        }

        return list;
    }
}
