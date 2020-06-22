package com.springboottutorial.spring_boot_tutorial.service;

import com.springboottutorial.spring_boot_tutorial.model.Address;
import com.springboottutorial.spring_boot_tutorial.model.Employee;
import com.springboottutorial.spring_boot_tutorial.model.Role;
import com.springboottutorial.spring_boot_tutorial.model.User;
import com.springboottutorial.spring_boot_tutorial.repository.AddressRepository;
import com.springboottutorial.spring_boot_tutorial.repository.EmployeeRepository;
import com.springboottutorial.spring_boot_tutorial.repository.RoleRepository;
import com.springboottutorial.spring_boot_tutorial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Configuration
public class DbInit {
    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${login.username}")
    private String username;
    @Value("${login.password}")
    private String password;
    public DbInit(PasswordEncoder passwordEncoder, UserRepository userRepository, EmployeeRepository employeeRepository, AddressRepository addressRepository, RoleRepository roleRepository){
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    @PostConstruct
    public void init(){
//        Address address = new Address();
//        address.setCity("Dhaka");
//        address.setCountry("Bangladesh");
////        address = addressRepository.save(address);
//        Employee employee = new Employee();
//        employee.setName("Rashed");
//        employee.setAddress(Arrays.asList(address));
//        employee = employeeRepository.save(employee);
//        address.setEmployee(employee);
//        address = addressRepository.save(address);
//        System.out.println("E ID= "+employee.getId());
//        System.out.println("A ID= "+address.getId());
        /*Employee employee = employeeRepository.findById(Long.valueOf(4)).get();
        employeeRepository.delete(employee);*/
        String roleName = "ROLE_ADMIN";
        int roleExistCount = roleRepository.countByName(roleName);
        Role role = null;
        if(roleExistCount==1){
            role = roleRepository.findByName(roleName);
        }else {
            role = new Role();
            role.setName(roleName);
            role = roleRepository.save(role);
        }
        User user =userRepository.findByUsernameAndIsActiveTrue(username);
        if(user == null){
            user = new User();
            user.setEmail("abc@ab.com");
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
        }
        user.setRoles(Arrays.asList(role));
        user = userRepository.save(user);
    }
}
