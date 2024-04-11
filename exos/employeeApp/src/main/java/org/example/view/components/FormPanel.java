package org.example.view.components;

import org.example.dao.EmployeeDao;
import org.example.model.Employee;
import org.example.model.Qualification;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FormPanel extends JPanel {
    private JTextField jtfEmployeeId, jtfName, jtfAge, jtfBloodGroup, jtfNumber, jtfAddress;
    private JButton uploadImage;
    private JRadioButton radioButtonMale, radioButtonFemale;
    private ButtonGroup genderGroup;

    private EmployeeDao employeeDao;


    public FormPanel(){
        TitledBorder tableBorder = BorderFactory.createTitledBorder("Form");
        setBorder(tableBorder);

        setBounds(100,100, 450,550);
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(5,5,5,5));
        setLayout(null);

        jtfEmployeeId = new JTextField();
        jtfEmployeeId.setBounds(80, 20,150, 20);
        add(jtfEmployeeId);

        jtfName = new JTextField();
        jtfName.setBounds(80, 50,150, 20);
        add(jtfName);

        JPanel genderPanel = new JPanel();
        genderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        radioButtonMale = new JRadioButton("Male");
        radioButtonFemale = new JRadioButton("Female");

        genderGroup = new ButtonGroup();
        genderGroup.add(radioButtonMale);
        genderGroup.add(radioButtonFemale);

        genderPanel.add(radioButtonMale);
        genderPanel.add(radioButtonFemale);
        genderPanel.setBounds(90, 80, 150, 30);
        add(genderPanel);

        jtfAge = new JTextField();
        jtfAge.setBounds(90, 110,150, 20);
        add(jtfAge);

        jtfBloodGroup = new JTextField();
        jtfBloodGroup.setBounds(90, 140,150, 20);
        add(jtfBloodGroup);

        jtfNumber = new JTextField();
        jtfNumber.setBounds(90, 170,150, 20);
        add(jtfNumber);

        JComboBox<String> qualificationList = new JComboBox<>(new String[]{"DOCTORAL", "MASTER", "BACHELOR", "ASSOCIATE"});
        qualificationList.setBounds(90, 200, 150, 20);
        add(qualificationList);

        JLabel labelEmployeeId = new JLabel("EmployeeId");
        labelEmployeeId.setBounds(10,20,80,15);
        add(labelEmployeeId);

        JLabel labelName = new JLabel("Name");
        labelName.setBounds(10,50,80,15);
        add(labelName);

        JLabel labelGender = new JLabel("Gender");
        labelGender.setBounds(10,80,50,30);
        add(labelGender);

        JLabel labelAge = new JLabel("Age");
        labelAge.setBounds(10,110,80,15);
        add(labelAge);

        JLabel labelBloodGroup = new JLabel("BloodGroup");
        labelBloodGroup.setBounds(10,140,80,15);
        add(labelBloodGroup);

        JLabel labelNumber = new JLabel("ContactNo");
        labelNumber.setBounds(10,170,80,15);
        add(labelNumber);

        JLabel labelQualification = new JLabel("Qualification");
        labelQualification.setBounds(10,200,80,15);
        add(labelQualification);

        JLabel labelStartDate= new JLabel("Start date");
        labelStartDate.setBounds(10,230,80,15);
        add(labelStartDate);


        setVisible(true);

    }
}


