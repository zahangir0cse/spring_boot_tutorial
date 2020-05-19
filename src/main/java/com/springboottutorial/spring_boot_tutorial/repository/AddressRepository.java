package com.springboottutorial.spring_boot_tutorial.repository;

import com.springboottutorial.spring_boot_tutorial.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
