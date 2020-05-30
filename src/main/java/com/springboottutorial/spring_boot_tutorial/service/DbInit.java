package com.springboottutorial.spring_boot_tutorial.service;

import com.springboottutorial.spring_boot_tutorial.model.Address;
import com.springboottutorial.spring_boot_tutorial.model.Employee;
import com.springboottutorial.spring_boot_tutorial.repository.AddressRepository;
import com.springboottutorial.spring_boot_tutorial.repository.EmployeeRepository;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Configuration
public class DbInit {
    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;
    public DbInit(EmployeeRepository employeeRepository, AddressRepository addressRepository){
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
    }

//    @PostConstruct
    public void init(){
        Address address = new Address();
        address.setCity("Dhaka");
        address.setCountry("Bangladesh");
//        address = addressRepository.save(address);
        Employee employee = new Employee();
        employee.setName("Rashed");
        employee.setAddress(Arrays.asList(address));
        employee = employeeRepository.save(employee);
        address.setEmployee(employee);
        address = addressRepository.save(address);
        System.out.println("E ID= "+employee.getId());
        System.out.println("A ID= "+address.getId());
        /*Employee employee = employeeRepository.findById(Long.valueOf(4)).get();
        employeeRepository.delete(employee);*/
    }
}
