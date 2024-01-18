package charusupat.woot.employeesdbweb.web.controller;

import charusupat.woot.employeesdbweb.biz.model.Employee;
import charusupat.woot.employeesdbweb.biz.service.EmployeeService;
import charusupat.woot.employeesdbweb.data.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeesController {

    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;

    public EmployeesController(EmployeeRepository employeeRepository, EmployeeService employeeService) {
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
    }

    @ModelAttribute("employees")
    public Page<Employee> getEmployees(@PageableDefault(size = 5) Pageable page) {
        return employeeService.findAll(page);
    }

    @ModelAttribute("employee")
    public Employee getEmployee() {
        return new Employee();
    }

    @GetMapping
    public String showEmployeesPage() {
        return "employees";
    }

    @PostMapping
    public String savedEmployee(@Valid Employee employee, Errors errors) {
        System.out.println(employee);
        if (!errors.hasErrors()) {
            employeeService.save(employee);
            return "redirect:employees";
        }
        return "employees";
    }

    @PostMapping(params = "delete")
    public String deleteEmployee(@RequestParam(name = "delete") Long id) {
        System.out.println(id);
//        employeeRepository.deleteById(id);
        employeeService.deleteById(id);
        return "redirect:employees";
    }

    @PostMapping(params = "edit")
    public String editEmployee(@RequestParam(name = "edit") Long id, Model model) {
        System.out.println(id);
        Optional<Employee> employee = employeeRepository.findById(id);
        model.addAttribute("employee", employee);
        return "employees";
    }

    @PostMapping(params = "reset=true")
    public String resetEmployee() {
        return "redirect:employees";
    }
}
