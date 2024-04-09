package org.example.component;

import lombok.Data;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@Data
public class Table {

    private JTable tb;
    private JScrollPane scrollPane;

    public Table(){
        String rows[][] = {{"100", "Pauline", "6500"}, {"101", "Lucas", "6700"}, {"102", "Corentin", "1000"}, {"103", "Blanche", "10"}};
        String column[] = {"ID", "NAME", "POINTS"};

        tb = new JTable(rows,column);

        tb.setBounds(30, 40, 500, 500);

        scrollPane = new JScrollPane(tb);

        tb.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                int row = tb.getSelectedRow();

                if (row<0){
                    System.out.format("Il n'y a rien");
                } else {
                    int modelRow = tb.convertRowIndexToModel(row);

                    System.out.format("Info selected in row n° %d. \n In column n° %d. \n Other infos: %s, %s, %s. \n", row,
                            modelRow, tb.getModel().getValueAt(modelRow, 0),
                            tb.getModel().getValueAt(modelRow,1),
                            tb.getModel().getValueAt(modelRow, 2));
                }
            }
        });
    }
}
