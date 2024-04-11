package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private int id;
    private String lastname;
    private String firstname;
    private Role role;
    private Department department;

    public Employee(String lastname, String firstname, Role role, Department department) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.role = role;
        this.department = department;
    }


}
