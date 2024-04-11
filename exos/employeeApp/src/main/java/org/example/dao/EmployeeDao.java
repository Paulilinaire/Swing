package org.example.dao;


import org.example.model.Employee;
import org.example.model.Qualification;
import org.example.utils.DbConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    public boolean add(Employee employee) {
        try (Connection con = DbConnexion.getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT INTO employee(name, gender, age, blood_group, number, qualification, start_date, image_path, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getGender());
            ps.setInt(3, employee.getAge());
            ps.setString(4, employee.getBloodGroup());
            ps.setString(5, employee.getNumber());
            ps.setString(6, employee.getQualification().toString());
            ps.setDate(7, new Date(employee.getStartDate().getTime()));
            ps.setString(8, employee.getImagePath());
            ps.setString(9, employee.getAddress());

            int n = ps.executeUpdate();
            return n > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Employee employee) {
        try (Connection con = DbConnexion.getConnection();
             PreparedStatement ps = con.prepareStatement("UPDATE employee SET name = ?, gender = ?, age = ?, blood_group = ?, number = ?, qualification = ?, start_date = ?, image_path = ?, address = ? WHERE id = ?")) {
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getGender());
            ps.setInt(3, employee.getAge());
            ps.setString(4, employee.getBloodGroup());
            ps.setString(5, employee.getNumber());
            ps.setString(6, employee.getQualification().toString());
            ps.setDate(7, new Date(employee.getStartDate().getTime()));
            ps.setString(8, employee.getImagePath());
            ps.setString(9, employee.getAddress());
            ps.setInt(10, employee.getId());

            int n = ps.executeUpdate();
            return n > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        try (Connection con = DbConnexion.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM employee WHERE id = ?")) {
            ps.setInt(1, id);
            int n = ps.executeUpdate();
            return n > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Employee findById(int id) {
        try (Connection con = DbConnexion.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM employee WHERE id = ?")) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return extractEmployeeFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try (Connection con = DbConnexion.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM employee");
             ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                Employee employee = extractEmployeeFromResultSet(resultSet);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private Employee extractEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String gender = resultSet.getString("gender");
        int age = resultSet.getInt("age");
        String bloodGroup = resultSet.getString("blood_group");
        String number = resultSet.getString("number");
        Qualification qualification = Qualification.valueOf(resultSet.getString("qualification"));
        Date startDate = resultSet.getDate("start_date");
        String imagePath = resultSet.getString("image_path");
        String address = resultSet.getString("address");
        return new Employee(id, name, gender, age, bloodGroup, number, qualification, startDate, imagePath, address);
    }
}
