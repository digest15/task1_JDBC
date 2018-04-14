package com.edu.task1_JDBC.hsql;

import com.edu.task1_JDBC.dao.DaoFactory;
import com.edu.task1_JDBC.dao.GenericDao;
import com.edu.task1_JDBC.dao.PersistException;
import com.edu.task1_JDBC.dao.jdbc.AbstractJdbcDao;
import com.edu.task1_JDBC.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMachineDao<T extends Machine> extends AbstractJdbcDao<T> {
    final DaoFactory factory;

    public AbstractMachineDao(Connection connection, DaoFactory factory) {
        super(connection);
        this.factory = factory;
    }

    protected abstract void fillStatementForPersistForChild(PreparedStatement statement, T object) throws SQLException;

    protected abstract T parseResultSetForChild(ResultSet result) throws SQLException;

    protected abstract TypeCar returnTypeCar();

    @Override
    protected String QueryGetAll() {
        return String.format("SELECT * FROM MACHINE WHERE typeCar=\'%s\';", returnTypeCar());
    }

    @Override
    protected String QueryGetReadById() {
        return String.format("SELECT * FROM MACHINE WHERE id = ?;");
    }

    @Override
    protected String QueryUpdate() {
        return "UPDATE MACHINE SET namePiking=?, vin=?, releaseYear=?, color_id=?, mark_id=?, numberPassengerSeats=?, numberPassengerStanding=?, numberAxle=?, capacity=?, typeCar=? WHERE id=?;";
    }

    @Override
    protected String QueryDelete() {
        return "DELETE FROM MACHINE WHERE id = ?;";
    }

    @Override
    protected String QueryPersist() {
        return "INSERT INTO MACHINE(namePiking, vin, releaseYear, color_id, mark_id, numberPassengerSeats, numberPassengerStanding, numberAxle, capacity, typeCar) VALUES(?,?,?,?,?,?,?,?,?,?)";
    }

    @Override
    protected void fillStatementForPersist(PreparedStatement statement, T object) throws SQLException {
        statement.setString(1, object.getNamePicking());
        statement.setString(2, object.getVin());
        statement.setDate(3, new java.sql.Date(object.getReleaseYear().getTime()));
        statement.setString(10, object.getTypeCar().toString());

        fillIdForEntityInPreparedStatement(object.getColor(), statement, 4);

        fillIdForEntityInPreparedStatement(object.getMark(), statement, 5);

        statement.setNull(6, Types.NULL);
        statement.setNull(7, Types.NULL);
        statement.setNull(8, Types.NULL);
        statement.setNull(9, Types.NULL);

        fillStatementForPersistForChild(statement, object);
    }

    @Override
    protected void fillStatementForUpdate(PreparedStatement statement, T object) throws SQLException {
        fillStatementForPersist(statement, object);
        statement.setInt(11, object.getId());
    }

    @Override
    protected List<T> parseResultSet(ResultSet result) throws SQLException, PersistException {
        List<T> list = new ArrayList<>();
        while (result.next()) {
            T machine = parseResultSetForChild(result);
            machine.setId(result.getInt("id"));
            machine.setNamePicking(result.getString("namePiking"));
            machine.setVin(result.getString("vin"));
            machine.setReleaseYear(new java.util.Date(result.getDate("releaseYear").getTime()));
            machine.setTypeCar(TypeCar.valueOf(result.getString("typeCar")));

            Integer id;
            id = result.getInt("color_id");
            GenericDao<Color> colorDao = factory.getColorDao(connection);
            Color color = colorDao.readById(id);
            machine.setColor(color);

            id = result.getInt("mark_id");
            GenericDao<Mark> markDao = factory.getMarkDao(connection);
            Mark mark = markDao.readById(id);
            machine.setMark(mark);

            list.add(machine);
        }

        return list;
    }
}
