import org.example.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


public class EmployeeServiceTest {

    private final EmployeeService employeeService = new EmployeeService(new ValidatorService());

    @AfterEach
    public void afterEach() {
        employeeService.getAll().forEach(employee -> employeeService.remove(employee.getName(), employee.getSurName()));
    }

    private Employee addOneWithCheck(String name, String surName) {
        Employee expected = new Employee(name, surName, 1, 55000);
        int sizeBefore = employeeService.getAll().size();
        employeeService.add(expected.getName(), expected.getSurName(), expected.getDepartment(), expected.getSalary());
        assertThat(employeeService.getAll())
                .isNotEmpty()
                .hasSize(sizeBefore + 1)
                .contains(expected);
        assertThat(employeeService.find(expected.getName(), expected.getSurName())).isEqualTo(expected);
        return expected;
    }

    private Employee addOneWithCheck() {
        return addOneWithCheck("Name", "Surname");
    }

    @Test
    public void addTest1() {
        addOneWithCheck();
    }

    @Test
    public void addTest2() {
        Employee employee = addOneWithCheck();
        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.add(employee.getName(), employee.getSurName(), employee.getDepartment(), employee.getSalary()));
    }

    @Test
    public void addTest3() {
        for (int i = 0; i < 10; i++) {
            addOneWithCheck("Name" + (char) ('a' + i), "Surname" + (char) ('a' + i));
        }
        assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(() -> employeeService.add("Name", "Surname", 1, 53000));
    }

    @Test
    public void findTest1() {
        Employee employee1 = addOneWithCheck("Name", "Surname");
        Employee employee2 = addOneWithCheck("Имя", "Фамилия");
        assertThat(employeeService.find(employee1.getName(), employee1.getSurName()))
                .isEqualTo(employee1);
        assertThat(employeeService.find(employee2.getName(), employee2.getSurName()))
                .isEqualTo(employee2);
    }

    @Test
    public void findTest2() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("nobody", "nobody"));
        Employee employee1 = addOneWithCheck("Name", "Surname");
        Employee employee2 = addOneWithCheck("Имя", "Фамилия");
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("nobody", "nobody"));
    }

    @Test
    public void removeTest1() {
        Employee employee1 = addOneWithCheck("Name", "Surname");
        Employee employee2 = addOneWithCheck("Имя", "Фамилия");
        employeeService.remove(employee1.getName(), employee1.getSurName());
        assertThat(employeeService.getAll())
                .isNotEmpty()
                .hasSize(1)
                .containsExactly(employee2);
        employeeService.remove(employee2.getName(), employee2.getSurName());
        assertThat(employeeService.getAll()).isEmpty();
    }

    @Test
    public void removeTest2() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.remove("nobody", "nobody"));
        Employee employee1 = addOneWithCheck("Name", "Surname");
        Employee employee2 = addOneWithCheck("Имя", "Фамилия");
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("nobody", "nobody"));
    }
}


