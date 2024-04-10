package org.example.dao;

import org.example.model.Department;
import org.example.utils.DbConnexion;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao implements BaseDAO<Department> {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet resultSet;


    @Override
    public int save(Department department) {
        try {
            connection = DbConnexion.getConnection();
            ps = connection.prepareStatement("INSERT INTO `department` (`name`) VALUES (?)");
            ps.setString(1, department.getName());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeResources();
        }
    }

    @Override
    public int update(Department department) {
        try {
            connection = DbConnexion.getConnection();
            ps = connection.prepareStatement("UPDATE `department` SET `name` = ?, WHERE id = ?");
            ps.setString(1, department.getName());
            ps.setInt(2, department.getId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeResources();
        }
    }

    @Override
    public int delete(int id) {
        try {
            connection = DbConnexion.getConnection();
            ps=connection.prepareStatement("DELETE FROM `department` WHERE id = ?");
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeResources();
        }
    }

    @Override
    public Department get(int id) {
        try {
            connection = DbConnexion.getConnection();
            ps = connection.prepareStatement("SELECT * FROM `department` WHERE id = ?");
            ps.setInt(1, id);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new Department(resultSet.getInt("id"),
                        resultSet.getString("name"));
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeResources();
        }
    }


    @Override
    public List<Department> getAll(DefaultTableModel tableModel) {
        List<Department> departments = new ArrayList<>();
        try {
            connection = DbConnexion.getConnection();
            ps = connection.prepareStatement("SELECT * FROM `department`");
            resultSet = ps.executeQuery();

            tableModel.addColumn("ID");
            tableModel.addColumn("Name");
            tableModel.addColumn("Employees (number)");

            while (resultSet.next()) {
                Department department = new Department(
                        resultSet.getInt("id"),
                        resultSet.getString("name"));
                departments.add(department);

                Object[] row = {
                        department.getId(),
                        department.getName()
                };
                tableModel.addRow(row);
            }
            return departments;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeResources();
        }
    }


    private void closeResources() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
