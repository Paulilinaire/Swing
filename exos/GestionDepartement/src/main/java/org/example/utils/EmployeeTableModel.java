package org.example.utils;

import javax.swing.table.AbstractTableModel;

public class EmployeeTableModel extends AbstractTableModel {

    private String[] columnNames = {"ID", "Last name", "First name", "Role", "Department"};
    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}
