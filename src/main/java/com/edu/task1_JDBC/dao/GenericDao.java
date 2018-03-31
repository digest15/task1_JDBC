package com.edu.task1_JDBC.dao;

import com.edu.task1_JDBC.entity.Identified;

import java.util.List;

public interface GenericDao<T extends Identified> {

    public T readById(Integer id) throws PersistException;

    public void update(T object) throws PersistException;

    public List<T> readAll() throws PersistException;

    public void persist(T object) throws PersistException;

    public void persistAll(List<T> list) throws PersistException;

    public void delete(T object) throws PersistException;

}
