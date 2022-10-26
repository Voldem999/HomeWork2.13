package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam("firstName") String name,
                        @RequestParam("lastName") String surName,
                        @RequestParam("departmentId") int department,
                        @RequestParam double salary) {
        return employeeService.add(name, surName, department, salary);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam("firstName") String name,
                           @RequestParam("lastName") String surName) {
        return employeeService.remove(name, surName);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam("firstName") String name,
                         @RequestParam("lastName") String surName) {
        return employeeService.find(name, surName);
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }
}
