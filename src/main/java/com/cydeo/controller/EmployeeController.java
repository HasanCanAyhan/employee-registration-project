package com.cydeo.controller;

import com.cydeo.bootstrap.DataGenerator;
import com.cydeo.model.Employee;
import com.cydeo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/register")
    public String createEmployee(Model model){

        model.addAttribute("employee",new Employee());
        model.addAttribute("stateList", DataGenerator.getAllStates());

        return "employee/employee-create";
    }


    @PostMapping("/insert")
    public String insertEmployee(@ModelAttribute("employee") Employee employee){ // coming from Form

        employeeService.saveEmployee(employee); // saving employee

        return "redirect:/employee/list"; // end point // with redirect we are using endpoints
    }

    @GetMapping("/list")
    public String listEmployees(Model model){

        model.addAttribute("employeeList",employeeService.readAllEmployees());

        return "employee/employee-list";// we are using html file paths , showing list of employee to the user

    }

}
