package charusupat.woot.employeesdbweb.biz.service;

import charusupat.woot.employeesdbweb.biz.model.Employee;
import charusupat.woot.employeesdbweb.data.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public Employee save(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }

    public Optional<Employee> findById(Long aLong) {
        return employeeRepository.findById(aLong);
    }

    public Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public void deleteById(Long aLong) {
        employeeRepository.deleteById(aLong);
    }
}
