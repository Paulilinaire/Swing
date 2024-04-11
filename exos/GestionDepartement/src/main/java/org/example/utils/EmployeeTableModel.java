package org.example.utils;

import javax.swing.table.AbstractTableModel;

public class EmployeeTableModel extends AbstractTableModel {

    private String[] columnNames = {"ID", "Last name", "First name", "Role", "Department"};

    private Object[][] data = {

    };

    @Override
    public int getRowCount() {
        return columnNames.length;
    }

    @Override
    public int getColumnCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }
}
