package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    private int id;
    private String name;
    private List<Employee> employees;

    public int countEmployees() {
        return employees != null ? employees.size() : 0;
    }

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
