package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private int id;
    private String name;
    private String gender;
    private int age;
    private String bloodGroup;
    private String number;
    private Qualification qualification;
    private Date startDate;
    private String imagePath;
    private String address;


}
