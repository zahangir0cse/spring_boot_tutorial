package com.springboottutorial.spring_boot_tutorial.controller;
import com.springboottutorial.spring_boot_tutorial.annotations.ApiController;
import com.springboottutorial.spring_boot_tutorial.model.Employee;
import com.springboottutorial.spring_boot_tutorial.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@ApiController
@RequestMapping("/employee")
@Controller
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    public Employee save(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @PutMapping("/update")
    public Employee update(@RequestBody Employee employee){
        return employeeService.update(employee);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
         employeeService.delete(id);
    }

    @GetMapping("/{id}")
    public Employee save(@PathVariable("id") Long id){
        return employeeService.get(id);
    }

    @GetMapping("/all")
    public List<Employee> getAll(){
        return employeeService.getAll();
    }
}
