package org.example.dao;

import org.example.model.Employee;
import org.example.model.Role;
import org.example.utils.DbConnexion;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao implements BaseDAO<Employee> {
    private Connection connection;
    private PreparedStatement ps;
    private ResultSet resultSet;

    @Override
    public int save(Employee employee) {
        try {
            connection = DbConnexion.getConnection();
            ps = connection.prepareStatement("INSERT INTO `employee` (`lastname`, `firstname`, `role`, `departmentId`) VALUES (?, ?, ?, ?)");
            ps.setString(1, employee.getLastname());
            ps.setString(2, employee.getFirstname());
            ps.setString(3, employee.getRole().toString());
            ps.setInt(4, employee.getDepartmentId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeResources();
        }
    }

    @Override
    public int update(Employee employee) {
        try {
            connection = DbConnexion.getConnection();
            ps = connection.prepareStatement("UPDATE `employee` SET `lastname` = ?, `firstname` = ?, `role` = ?, `departmentId` = ? WHERE id = ?");
            ps.setString(1, employee.getLastname());
            ps.setString(2, employee.getFirstname());
            ps.setString(3, employee.getRole().toString());
            ps.setInt(4, employee.getDepartmentId());
            ps.setInt(5, employee.getId());
            return ps.executeUpdate();
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
            ps = connection.prepareStatement("DELETE FROM `employee` WHERE id = ?");
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeResources();
        }
    }

    @Override
    public Employee get(int id) {
        try {
            connection = DbConnexion.getConnection();
            ps = connection.prepareStatement("SELECT * FROM `employee` WHERE id = ?");
            ps.setInt(1, id);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        Role.valueOf(resultSet.getString("role")),
                        resultSet.getInt("departmentId"));
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeResources();
        }
    }

    @Override
    public List<Employee> getAll(DefaultTableModel tableModel) {
        List<Employee> employees = new ArrayList<>();
        try {
            connection = DbConnexion.getConnection();
            ps = connection.prepareStatement("SELECT * FROM `employee`");
            resultSet = ps.executeQuery();

            tableModel.addColumn("ID");
            tableModel.addColumn("Firstname");
            tableModel.addColumn("Lastname");
            tableModel.addColumn("Role");
            tableModel.addColumn("Department ID");

            while (resultSet.next()) {
                Employee employee = new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        Role.valueOf(resultSet.getString("role")),
                        resultSet.getInt("departmentId"));
                employees.add(employee);

                Object[] row = {
                        employee.getId(),
                        employee.getFirstname(),
                        employee.getLastname(),
                        employee.getRole(),
                        employee.getDepartmentId()
                };
                tableModel.addRow(row);
            }
            return employees;
        } catch (SQLException e) {
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
