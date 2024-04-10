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
    private int departmentId;

    public Employee(String lastname, String firstname, Role role, int departmentId) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.role = role;
        this.departmentId = departmentId;
    }
}
