package com.edu.task1_JDBC.hsql;

import com.edu.task1_JDBC.dao.DaoFactory;
import com.edu.task1_JDBC.dao.GenericDao;
import com.edu.task1_JDBC.dao.PersistException;
import com.edu.task1_JDBC.dao.jdbc.AbstractJdbcDao;
import com.edu.task1_JDBC.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepairDao extends AbstractJdbcDao<Repair> {
    final DaoFactory factory;

    public RepairDao(Connection connection, DaoFactory factory) {
        super(connection);
        this.factory = factory;
    }

    @Override
    protected String QueryGetAll() {
        return "SELECT repair.*, machine.typeCar FROM repair LEFT JOIN machine on repair.machine_id = machine.id;";
    }

    @Override
    protected String QueryGetReadById() {
        return "SSELECT repair.*, machine.typeCar FROM repair LEFT JOIN machine on repair.machine_id = machine.id WHERE machine.id = ?;";
    }

    @Override
    protected String QueryUpdate() {
        return "UPDATE REPAIR SET dateTime=?, carServise_id=?, machine_id=?, amount=?, mechanic_id=? WHERE id=?;";
    }

    @Override
    protected String QueryDelete() {
        return "DELETE REPAIR WHERE id = ?";
    }

    @Override
    protected String QueryPersist() {
        return "INSERT INTO REPAIR(dateTime, carServise_id, machine_id, amount, mechanic_id) VALUES(?,?,?,?,?)";
    }

    @Override
    protected void fillStatementForPersist(PreparedStatement statement, Repair object) throws SQLException {
        statement.setDate(1, new java.sql.Date(object.getDateTime().getTime()));
        statement.setBigDecimal(4, object.getAmount());

        fillIdForEntityInPreparedStatement(object.getCarServise(), statement, 2);
        fillIdForEntityInPreparedStatement(object.getMachine(), statement, 3);
        fillIdForEntityInPreparedStatement(object.getMechanic(), statement, 5);
    }

    @Override
    protected void fillStatementForUpdate(PreparedStatement statement, Repair object) throws SQLException {
        statement.setInt(6, object.getId());
    }

    @Override
    protected List<Repair> parseResultSet(ResultSet result) throws SQLException, PersistException {
        List<Repair> list = new ArrayList<>();
        while (result.next()) {
            Repair repair = new Repair();
            repair.setId(result.getInt("id"));
            repair.setAmount(result.getBigDecimal("amount"));
            repair.setDateTime(new java.util.Date(result.getDate("dateTime").getTime()));

            Integer id;
            id = result.getInt("carServise_id");
            GenericDao<CarService> carServiceDao = factory.getCarServiceDao(connection);
            CarService carService = carServiceDao.readById(id);
            repair.setCarServise(carService);

            GenericDao machineDao;
            switch (TypeCar.valueOf(result.getString("typeCar"))) {
                case Car:
                    machineDao = factory.getCarDao(connection, factory);
                    break;
                case Truck:
                    machineDao = factory.getTruckDao(connection, factory);
                    break;
                case Bus:
                    machineDao = factory.getBusDao(connection, factory);
                    break;

                    default:
                        throw new PersistException("Неизвестный тип автомобиля");
            }
            id = result.getInt("machine_id");
            Machine machine = (Machine) machineDao.readById(id);
            repair.setMachine(machine);


            id = result.getInt("mechanic_id");
            GenericDao<Mechanic> mechanicDao = factory.getMehanicDao(connection);
            Mechanic mechanic = mechanicDao.readById(id);
            repair.setMechanic(mechanic);

            list.add(repair);
        }

        return list;
    }

}
