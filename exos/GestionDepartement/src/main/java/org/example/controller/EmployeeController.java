package org.example.controller;

import org.example.dao.EmployeeDao;
import org.example.model.Employee;
import org.example.model.Role;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class EmployeeController {
    private final EmployeeDao employeeDao;

    public EmployeeController(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public int createEmployee(String firstname, String lastname, Role role, int departmentId) {
        Employee employee = new Employee();
        employee.setFirstname(firstname);
        employee.setLastname(lastname);
        employee.setRole(role);
        employee.setDepartmentId(departmentId);

        return employeeDao.save(employee);
    }

    public int updateEmployee(Employee employee) {
        return employeeDao.update(employee);
    }

    public Employee getEmployee(int id) {
        return employeeDao.get(id);
    }

    public boolean deleteEmployee(int id) {
        Employee employee = employeeDao.get(id);
        if (employee != null) {
            return employeeDao.delete(id) > 0;
        }
        return false;
    }

    public List<Employee> getAllEmployees(DefaultTableModel tableModel) {
        return employeeDao.getAll(tableModel);

    }
}
