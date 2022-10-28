import org.example.DepartmentService;
import org.example.Employee;
import org.example.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    public void beforeEach() {
        when(employeeService.getAll()).thenReturn(
                Arrays.asList(
                        new Employee("Vladimir", "Katolikov", 1, 150000),
                        new Employee("Veronika", "Katoliova", 1, 155000),
                        new Employee("Ivan", "Ivanov", 2, 125000),
                        new Employee("Sergey", "Petrov", 2, 134500),
                        new Employee("Anton", "Sidorov", 3, 95200),
                        new Employee("Anna", "Ivanova", 3, 81543),
                        new Employee("Alex", "Sergeev", 4, 65800),
                        new Employee("Pavel", "Smirnov", 4, 75750),
                        new Employee("Marat", "Goldberg", 5, 55300),
                        new Employee("Irina", "Goldberg", 5, 45200)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("findEmployeeWithMaxSalaryFromDepartmentTestParams")
    public void findEmployeeWithMaxSalaryFromDepartmentTest(int department, Employee expected) {
        assertThat(departmentService.findEmployeeWithMaxSalaryFromDepartment(department)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("findEmployeeWithMinSalaryFromDepartmentTestParams")
    public void findEmployeeWithMinSalaryFromDepartmentTest(int department, Employee expected) {
        assertThat(departmentService.findEmployeeWithMinSalaryFromDepartment(department)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("findEmployeesFromDepartmentTestParams")
    public void findEmployeesFromDepartmentTest(int department, Collection<Employee> expected) {
        assertThat(departmentService.findEmployeesFromDepartment(department)).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void findEmployees() {
        assertThat(departmentService.findEmployees()).containsExactlyInAnyOrderEntriesOf(
                Map.of(
                        1, List.of(
                                new Employee("Vladimir", "Katolikov", 1, 150000),
                                new Employee("Veronika", "Katoliova", 1, 155000)
                ),
                        2, List.of(
                                new Employee("Ivan", "Ivanov", 2, 125000),
                                new Employee("Sergey", "Petrov", 2, 134500)
                        ),
                        3, List.of(
                                new Employee("Anton", "Sidorov", 3, 95200),
                                new Employee("Anna", "Ivanova", 3, 81543)
                        ),
                        4, List.of(
                                new Employee("Alex", "Sergeev", 4, 65800),
                                new Employee("Pavel", "Smirnov", 4, 75750)
                        ),
                        5, List.of(
                                new Employee("Marat", "Goldberg", 5, 55300),
                                new Employee("Irina", "Goldberg", 5, 45200)
                        )
                )

        );
    }

    public static Stream<Arguments> findEmployeeWithMaxSalaryFromDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1, new Employee("Veronika", "Katoliova", 1, 155000)),
                Arguments.of(5, new Employee("Marat", "Goldberg", 5, 55300))
        );
    }

    public static Stream<Arguments> findEmployeeWithMinSalaryFromDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1, new Employee("Vladimir", "Katolikov", 1, 150000)),
                Arguments.of(5, new Employee("Irina", "Goldberg", 5, 45200))
        );
    }

    public static Stream<Arguments> findEmployeesFromDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1,
                        List.of(
                                new Employee("Vladimir", "Katolikov", 1, 150000),
                                new Employee("Veronika", "Katoliova", 1, 155000))),
                Arguments.of(2,
                        List.of(
                                new Employee("Ivan", "Ivanov", 2, 125000),
                                new Employee("Sergey", "Petrov", 2, 134500))),
                Arguments.of(3,
                        List.of(
                                new Employee("Anton", "Sidorov", 3, 95200),
                                new Employee("Anna", "Ivanova", 3, 81543))),
                Arguments.of(4,
                        List.of(
                                new Employee("Alex", "Sergeev", 4, 65800),
                                new Employee("Pavel", "Smirnov", 4, 75750))),
                Arguments.of(5,
                        List.of(
                                new Employee("Marat", "Goldberg", 5, 55300),
                                new Employee("Irina", "Goldberg", 5, 45200)))
        );
    }

}
