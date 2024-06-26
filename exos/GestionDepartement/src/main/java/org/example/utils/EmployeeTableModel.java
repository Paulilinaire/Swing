package org.example.utils;

import lombok.Data;
import org.example.model.Employee;

import javax.swing.table.AbstractTableModel;
import java.util.List;

@Data
public class EmployeeTableModel extends AbstractTableModel {

    private final List<Employee> employees;
    private final String[] columnNames = {"ID", "First name", "Last name", "Role", "Department"};

    public EmployeeTableModel(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public int getRowCount() {
        return employees.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee employee = employees.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> employee.getId();
            case 1 -> employee.getFirstname();
            case 2 -> employee.getLastname();
            case 3 -> employee.getRole().toString();
            case 4 -> employee.getDepartment().getName();
            default -> null;
        };
    }


}
