package charusupat.woot.employeesdbweb.data;

import charusupat.woot.employeesdbweb.biz.model.Employee;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

//@Component
public class EmployeeDataLoader implements ApplicationRunner {
    private EmployeeRepository employeeRepository;

    public EmployeeDataLoader(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (employeeRepository.count() == 0) {
            List<Employee> employees = List.of(
                    new Employee(null, "Pete", "Snake", LocalDate.of(1950, 1, 1), "dummy@sample.com", new BigDecimal("50000")),
                    new Employee(null, "Sarah", "Smith", LocalDate.of(1960, 2, 1), "dummy@sample.com", new BigDecimal("60000")),
                    new Employee(null, "Mark", "Jackson", LocalDate.of(1970, 3, 1), "dummy@sample.com", new BigDecimal("70000"))
            );
            employeeRepository.saveAll(employees);
        }
    }
}
