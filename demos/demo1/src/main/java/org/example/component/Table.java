package org.example.component;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Table {

    private JTable tb;
    private JScrollPane scrollPane;

    public Table(){
        String rows[][] = {{"100", "Pierre", "6500"}, {"101", "Lucas", "6700"}, {"102", "Corentin", "1000"}};
        String columns[] = {"ID", "NAME", "POINTS"};

        tb = new JTable();

        tb.setBounds(30, 40, 500, 500);

        scrollPane = new JScrollPane(tb);

        tb.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                int row = tb.getSelectedRow();

                if (row<0){
                    System.out.println("Il n'y a rien");
                } else {
                    int modelRow = tb.convertRowIndexToModel(row);

                    System.out.println(String.format("Info selected in: %d. In model : %d. Other info: %d, %s, %s", row,
                            modelRow, tb.getModel().getValueAt(modelRow, 0),
                            tb.getModel().getValueAt(modelRow,1),
                            tb.getModel().getValueAt(modelRow, 2)));
                }
            }
        });
    }
}
