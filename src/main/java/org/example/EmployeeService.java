package org.example;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    private static final int LIMIT = 10;
    private final Map<String, Employee> employees = new HashMap<>();

    private final ValidatorService validatorService;

    public EmployeeService(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    private String getKey(String name, String surName) {
        return name + "|" + surName;
    }

    public Employee add(String name, String surName, int department, double salary) {
        Employee employee = new Employee(
                validatorService.validateName(name),
                validatorService.validateSurName(surName),
                department,
                salary);
        String key = getKey(name, surName);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT) {
            employees.put(key, employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }

    public Employee remove(String name, String surName) {
        String key = getKey(name, surName);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employees.remove(key);
    }


    public Employee find(String name, String surName) {
        String key = getKey(name, surName);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(key);
    }

    public List<Employee> getAll() {
        return new ArrayList<>(employees.values());
    }

}
