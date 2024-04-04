package org.example;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame jFrame = new JFrame("Demo Layout");

        jFrame.setSize(900, 500);
        jFrame.setLocationRelativeTo(null); // centre la fenêtre
//        jFrame.setResizable(false); // bloque le réajustement de la taille du panel
//        jFrame.pack();// le conteneur qui s'adapte à la taill du composant (attention l'ordre est imposant)
//        jFrame.add(new BorderLayoutDemo());
//        jFrame.add(new FlowLayoutDemo().getJPanel());
        jFrame.add(new GridBagLayoutDemo().getJPanel());
        jFrame.setVisible(true);


    }
}