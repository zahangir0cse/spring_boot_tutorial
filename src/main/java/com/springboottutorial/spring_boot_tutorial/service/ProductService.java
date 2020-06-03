package com.springboottutorial.spring_boot_tutorial.service;

import com.springboottutorial.spring_boot_tutorial.dto.ProductDto;
import com.springboottutorial.spring_boot_tutorial.dto.Response;

public interface ProductService {
    Response save(ProductDto productDto);
    Response update(Long id, ProductDto productDto);
    Response delete(Long id);
    Response get(Long id);
    Response getAll();
}
