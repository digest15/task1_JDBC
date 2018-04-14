package com.edu.task1_JDBC.dao.jdbc;

import com.edu.task1_JDBC.dao.DaoFactory;
import com.edu.task1_JDBC.dao.GenericDao;
import com.edu.task1_JDBC.dao.PersistException;
import com.edu.task1_JDBC.entity.Identified;
import com.google.common.base.Preconditions;

import java.sql.*;
import java.util.List;

public abstract class AbstractJdbcDao<T extends Identified> implements GenericDao<T> {
    protected final Connection connection;

    public AbstractJdbcDao(Connection connection) {
        this.connection = connection;
    }


    protected abstract String QueryGetAll();

    protected abstract String QueryGetReadById();

    protected abstract String QueryUpdate();

    protected abstract String QueryDelete();

    protected abstract String QueryPersist();

    protected abstract void fillStatementForPersist(PreparedStatement statement, T object) throws SQLException;

    protected abstract void fillStatementForUpdate(PreparedStatement statement, T object) throws  SQLException;

    protected abstract List<T> parseResultSet(ResultSet result) throws SQLException, PersistException;

    protected void fillIdForEntityInPreparedStatement(Identified object, PreparedStatement statement, int parametrIndex) throws SQLException{
        if (object == null){
            statement.setNull(parametrIndex, Types.NULL);
        }else {
            Integer id = object.getId();
            if (id != null) {
                statement.setInt(parametrIndex, id);
            }else {
                throw new SQLException(String.format("Перед сохранением объекта %s нужно сохранить объект %s", object.getClass().getName(), object.getClass().getName()));
            }
        }
    }

    @Override
    public T readById(Integer id) throws PersistException {
        Preconditions.checkNotNull(id, "Нелья прочитать элемент без id");

        List<T> list;

        String sql = QueryGetReadById();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            list = parseResultSet(result);
        } catch (SQLException e) {
            throw new PersistException(e);
        }

        int listSize = list.size();
        if (listSize == 0 || list == null) {
            return null;
        } else if (listSize > 1){
            throw new PersistException("Не может быть по одному ключу несколько строк");
        }

        T object = list.get(0);
        return object;
    }

    @Override
    public List<T> readAll() throws PersistException {
        List<T> list;

        String sql = QueryGetAll();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            list = parseResultSet(result);
        } catch (SQLException e) {
            throw new PersistException(e);
        }

        return list;
    }

    @Override
    public void update(T object) throws PersistException {
        Preconditions.checkNotNull(object);

        String sql = QueryUpdate();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            Preconditions.checkNotNull(object.getId(), "Нелья изменить элемент без id");
            fillStatementForUpdate(statement, object);
            int count = statement.executeUpdate();
            checkCountRows(count);
            connection.commit();
        } catch (SQLException e) {
            rollbackTransation();
            throw new PersistException(e);
        }
    }

    @Override
    public void persist(T object) throws PersistException {
        Preconditions.checkNotNull(object);
        Preconditions.checkArgument(object.getId() == null, "Сохранять можно только новые объекты");

        String sql = QueryPersist();
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            fillStatementForPersist(statement, object);

            connection.setAutoCommit(false);
            int count = statement.executeUpdate();
            checkCountRows(count);

            ResultSet keys = statement.getGeneratedKeys();
            keys.next();
            int id = keys.getInt(1);

            object.setId(id);

            connection.commit();
        } catch (SQLException e) {
            rollbackTransation();
            throw new PersistException(e);
        }
    }

    @Override
    public void persistAll(List<T> list) throws PersistException  {
        Preconditions.checkNotNull(list);

        String sql = QueryPersist();
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (T object : list) {
                Preconditions.checkArgument(object.getId() == null, "Сохранять можно только новые объекты");

                fillStatementForPersist(statement, object);
                connection.setAutoCommit(false);
                int count = statement.executeUpdate();
                checkCountRows(count);

                ResultSet keys = statement.getGeneratedKeys();
                keys.next();
                int id = keys.getInt(1);

                object.setId(id);
                connection.commit();
            }
        } catch (SQLException e) {
            rollbackTransation();
            throw new PersistException(e);
        }
    }

    @Override
    public void delete(T object) throws PersistException {
        Preconditions.checkNotNull(object);

        String sql = QueryDelete();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            fillStatementForDelete(statement, object);
            connection.setAutoCommit(false);
            int count = statement.executeUpdate();
            checkCountRows(count);
            connection.commit();
        } catch (SQLException e) {
            rollbackTransation();
            throw new PersistException(e);
        }
    }


    private void fillStatementForDelete(PreparedStatement statement, T object) throws SQLException {
        Preconditions.checkNotNull(object.getId(), "Нелья удалить элемент без id");
        statement.setInt(1, object.getId());
    }

    private void checkCountRows(int count) throws SQLException {
        if (count != 1) {
            throw new SQLException("Было модифицировано больше одной записи");
        }
    }

    private void rollbackTransation() throws PersistException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }
}
