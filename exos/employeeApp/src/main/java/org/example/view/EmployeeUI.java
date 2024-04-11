package org.example.view;

import org.example.view.components.FormPanel;

import javax.swing.*;
import java.awt.*;

public class EmployeeUI extends JPanel {

    private FormPanel formPanel;

    public EmployeeUI() {
        setLayout(new BorderLayout());

        formPanel = new FormPanel();
        add(formPanel, BorderLayout.CENTER);

        setVisible(true);
    }

}
