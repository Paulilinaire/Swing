package org.example.view;

import org.example.controller.DepartmentController;
import org.example.dao.DepartmentDao;
import org.example.utils.DepartmentTableModel;

import javax.swing.*;
import java.awt.*;

public class DepartmentUI extends JPanel{

    public DepartmentUI() {
        super(new GridLayout(1, 0));
        DepartmentController departmentController = new DepartmentController(new DepartmentDao());
        JTable table = new JTable(new DepartmentTableModel(departmentController.getAllDepartments()));
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
    }


}
