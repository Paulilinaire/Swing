package org.example;

import javax.swing.*;
import java.awt.*;

public class BorderLayoutDemo extends JPanel {

    private JButton jButton1;

    private JButton jButton2;

    private JButton jButton3;

    private JButton jButton4;

    private JButton jButton5;


    public BorderLayoutDemo(){

        setLayout(new BorderLayout());
        jButton1 = new JButton("NORTH");
        jButton2 = new JButton("CENTER");
        jButton3 = new JButton("SOUTH");
        jButton4 = new JButton("WEST");
        jButton5 = new JButton("EAST");

        jButton1.setForeground(Color.RED);
        jButton2.setForeground(Color.ORANGE);
        jButton3.setForeground(Color.YELLOW);
        jButton4.setForeground(Color.GREEN);
        jButton5.setForeground(Color.CYAN);

        add(jButton1, BorderLayout.NORTH);
        add(jButton2, BorderLayout.CENTER);
        add(jButton3, BorderLayout.SOUTH);
        add(jButton4, BorderLayout.WEST);
        add(jButton5, BorderLayout.EAST);


    }


}
