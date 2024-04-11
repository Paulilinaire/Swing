package org.example.dao;

import org.example.model.Department;
import org.example.model.Employee;
import org.example.model.Role;
import org.example.utils.DbConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao implements BaseDAO<Employee> {
    private Connection connection;
    private PreparedStatement ps;
    private ResultSet resultSet;
    private DepartmentDao departmentDao = new DepartmentDao();

    @Override
    public int save(Employee employee) {
        try {
            connection = DbConnexion.getConnection();
            ps = connection.prepareStatement("INSERT INTO `employee` (`lastname`, `firstname`, `role`, `department_id`) VALUES (?, ?, ?, ?)");
            ps.setString(1, employee.getLastname());
            ps.setString(2, employee.getFirstname());
            ps.setString(3, employee.getRole().toString());
            ps.setInt(4, employee.getDepartment().getId());
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
            ps = connection.prepareStatement("UPDATE `employee` SET `lastname` = ?, `firstname` = ?, `role` = ?, `department_id` = ? WHERE id = ?");
            ps.setString(1, employee.getLastname());
            ps.setString(2, employee.getFirstname());
            ps.setString(3, employee.getRole().toString());
            ps.setInt(4, employee.getDepartment().getId());
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
                int employeeId = resultSet.getInt("id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                Role role = Role.valueOf(resultSet.getString("role").toUpperCase());
                int departmentId = resultSet.getInt("department_id");
                Department department = departmentDao.get(departmentId);
                return new Employee(employeeId, firstname, lastname, role, department);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeResources();
        }
    }


    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        try {
            connection = DbConnexion.getConnection();
            ps = connection.prepareStatement("SELECT * FROM `employee`");
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int employeeId = resultSet.getInt("id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                Role role = Role.valueOf(resultSet.getString("role").toUpperCase());
                int departmentId = resultSet.getInt("department_id");
                Department department = departmentDao.get(departmentId);
                Employee employee = new Employee(employeeId, firstname, lastname, role, department);
                employees.add(employee);
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
