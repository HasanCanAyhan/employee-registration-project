package com.cydeo.controller;

import com.cydeo.bootstrap.DataGenerator;
import com.cydeo.model.Employee;
import com.cydeo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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


    @PostMapping("/insert") //                                after employee, you should put BindingResult
    public String insertEmployee(@ModelAttribute("employee") @Valid Employee employee, BindingResult bindingResult,Model model){ // coming from Form

        // what if it is not valid

        if (bindingResult.hasErrors()){
            model.addAttribute("stateList", DataGenerator.getAllStates());
            return "employee/employee-create"; // return the page
        }


        employeeService.saveEmployee(employee); // saving employee

        return "redirect:/employee/list"; // end point // with redirect we are using endpoints
    }

    @GetMapping("/list")
    public String listEmployees(Model model){

        model.addAttribute("employeeList",employeeService.readAllEmployees());

        return "employee/employee-list";// we are using html file paths , showing list of employee to the user

    }

}
