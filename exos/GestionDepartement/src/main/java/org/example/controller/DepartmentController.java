package org.example.controller;

import org.example.dao.DepartmentDao;
import org.example.model.Department;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class DepartmentController {
    private final DepartmentDao departmentDao;

    public DepartmentController(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public int createDepartment(String name) {
        Department department = new Department();
        department.setName(name);
        return departmentDao.save(department);
    }

    public int updateDepartment(Department department) {
        return departmentDao.update(department);
    }

    public Department getDepartment(int id) {
        return departmentDao.get(id);
    }

    public boolean deleteDepartment(int id) {
        Department department = departmentDao.get(id);
        if (department != null) {
            return departmentDao.delete(id) > 0;
        }
        return false;

    }

    public List<Department> getAllDepartments(DefaultTableModel tableModel) {
        return departmentDao.getAll(tableModel);
    }
}
