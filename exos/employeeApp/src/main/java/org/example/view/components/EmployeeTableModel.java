package org.example.view.components;

import lombok.Data;
import org.example.model.Employee;

import javax.swing.table.AbstractTableModel;
import java.util.List;

@Data
public class EmployeeTableModel extends AbstractTableModel {

    private final List<Employee> employees;
    private final String[] columnNames = {"EmployeeId", "Name", "Gender", "Age", "BloodGroup", "ContactNo", "Qualification", "DOJ", "Address", "EmpImage"};

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
            case 1 -> employee.getName();
            case 2 -> employee.getGender();
            case 3 -> employee.getAge();
            case 4 -> employee.getBloodGroup();
            case 5 -> employee.getNumber();
            case 6 -> employee.getQualification().toString();
            case 7 -> employee.getStartDate();
            case 8 -> employee.getImagePath();
            case 9 -> employee.getAddress();
            default -> null;
        };
    }


}
