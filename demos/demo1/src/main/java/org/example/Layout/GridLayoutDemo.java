package org.example.Layout;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class GridLayoutDemo {

    private JPanel jPanel;

    public GridLayoutDemo(){

        jPanel = new JPanel(new GridLayout(3, 3));

        for (int i = 1; i <=9 ; i++){
            JButton jButton = new JButton("b" + i);
            jButton.addActionListener(e -> {
                System.out.println(((JButton)(e.getSource())).getText());
            });
            jPanel.add(jButton);
        }
    }
}
