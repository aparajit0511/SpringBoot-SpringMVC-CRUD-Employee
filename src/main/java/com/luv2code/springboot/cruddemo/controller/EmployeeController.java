package com.luv2code.springboot.cruddemo.controller;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees") // base mapping for url
public class EmployeeController {

    private EmployeeService employeeService;

    // EmployeeService theEmployeeService - constructor injection
    // No @Autowired required since only one constructor is used
    public EmployeeController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

//    In Spring MVC, a model attribute is a mechanism used to pass data from the controller to the view.
//    It allows you to add objects to the model, making them available to the view for rendering.
//    The model attribute can be of any Java type, including custom-defined classes.

    // Add mapping for "/list"
    @GetMapping("/list")
    // Import Model from Spring framework
    public String listEmployees(Model theModel){

        // get employees from the db
        List<Employee> theEmployees = employeeService.findAll();

        // add that to the spring model
        theModel.addAttribute("employees",theEmployees);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    // Create mapping for add employee
    public String showFormForAdd(Model theModel){

        // create model attribute to bind form data
        Employee theEmployee = new Employee();

        // thymeleaf template will access "employees" for binding form data
        theModel.addAttribute("employee",theEmployee);

        return "employees/employee-form";
    }

//    The @ModelAttribute annotation is used to bind form data or request parameters to a model attribute.
//    It can be applied to method parameters or method-level to indicate that a method parameter should be retrieved from the model.
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

        // save the employee
        employeeService.save(theEmployee);

        // use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }

    @PostMapping("/showFormForUpdate")
    // RequestParam("employeeId") helps in prepopulation of the form when then update button is clicked
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel){

        // get the employee from the service
        Employee theEmployee = employeeService.findById(theId);

        // set the employee in the model to prepopulate the form
        theModel.addAttribute("employee",theEmployee);

        // send over to our form
        return "employees/employee-form";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId) {

        // delete the employee
        employeeService.deleteById(theId);

        // redirect to /employees/list
        return "redirect:/employees/list";

    }
}
