package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Employee {
    @JsonProperty("firstName")
    private final String name;
    @JsonProperty("lastName")
    private final String surName;
    private final int department;
    private final double salary;


    public Employee(String name, String surName, int department, double salary) {
        this.name = name;
        this.surName = surName;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public int getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return department == employee.department && Double.compare(employee.salary, salary) == 0 && Objects.equals(name, employee.name) && Objects.equals(surName, employee.surName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surName, department, salary);
    }

    @Override
    public String toString() {
        return String.format("ФИ: %s %s, отдел: %d, ЗП: %.2f", surName, name, department, salary);
    }
}
