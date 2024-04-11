package org.example.utils;

import lombok.Data;
import org.example.model.Department;

import javax.swing.table.AbstractTableModel;
import java.util.List;

@Data
public class DepartmentTableModel extends AbstractTableModel {

    private final List<Department> departments;
    private final String[] columnNames = {"ID", "Nom"};

    public DepartmentTableModel(List<Department> departments) {
        this.departments = departments;
    }

    @Override
    public int getRowCount() {
        return departments.size();
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
        Department department = departments.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> department.getId();
            case 1 -> department.getName();
            default -> null;
        };
    }
}
