package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    //add mapping for "/list
    @GetMapping("/list")
    public String listEmployees(Model model){
        // get the employees from db
        List<Employee> theEmployees= employeeService.findAll();
        //add to the spring model
        model.addAttribute("employees",theEmployees);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        //create model attribute to bind form data

        Employee theEmployee=new Employee();

        theModel.addAttribute("employee", theEmployee);
        System.out.println("try");
        return "employees/employee-form";

    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        employeeService.save(theEmployee);

        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId")int theId,
                                    Model theModel){
        //get the employee from the service
        Employee theEmployee=employeeService.findById(theId);

        //set employee in the model to repopulate the form
        theModel.addAttribute("employee", theEmployee);

        //send over to our form

        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String deleteById(@RequestParam("employeeId")int theId){
        employeeService.deleteById(theId);

        return "redirect:/employees/list";
    }
}
