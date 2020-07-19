package com.springboottutorial.spring_boot_tutorial.service;

import com.springboottutorial.spring_boot_tutorial.dto.ProductDto;
import com.springboottutorial.spring_boot_tutorial.dto.Response;
import org.springframework.http.HttpEntity;

import javax.servlet.http.HttpServletResponse;

public interface ProductService {
    Response save(ProductDto productDto);
    Response update(Long id, ProductDto productDto);
    Response delete(Long id);
    Response get(Long id);
    Response getAll();
    HttpEntity<byte[]> getPdfResponse(HttpServletResponse response);
}
