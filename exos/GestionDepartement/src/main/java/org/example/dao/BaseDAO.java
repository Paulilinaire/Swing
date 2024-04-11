package org.example.dao;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface BaseDAO<T> {
    int save(T element) throws SQLException;
    int update(T element) throws SQLException;
    int delete(int id) throws SQLException;
    T get(int id) throws SQLException;
    List<T> getAll() throws SQLException;
}