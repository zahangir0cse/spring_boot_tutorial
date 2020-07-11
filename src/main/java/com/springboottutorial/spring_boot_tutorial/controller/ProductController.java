package com.springboottutorial.spring_boot_tutorial.controller;

import com.springboottutorial.spring_boot_tutorial.annotations.ApiController;
import com.springboottutorial.spring_boot_tutorial.annotations.ValidateData;
import com.springboottutorial.spring_boot_tutorial.dto.ProductDto;
import com.springboottutorial.spring_boot_tutorial.dto.Response;
import com.springboottutorial.spring_boot_tutorial.service.ProductService;
import com.springboottutorial.spring_boot_tutorial.util.ResponseBuilder;
import com.springboottutorial.spring_boot_tutorial.util.UrlConstraint;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostPersist;
import javax.validation.Valid;

@ApiController
@RequestMapping(UrlConstraint.ProductManagement.ROOT)
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(UrlConstraint.ProductManagement.CREATE)
    @ValidateData
    public Response create(@Valid @RequestBody ProductDto productDto, BindingResult result) {
        return productService.save(productDto);
    }

    @PutMapping(UrlConstraint.ProductManagement.UPDATE)
    @ValidateData
    public Response update(@PathVariable("id") Long id, @Valid @RequestBody ProductDto productDto, BindingResult result) {
        return productService.update(id, productDto);
    }

    @DeleteMapping(UrlConstraint.ProductManagement.DELETE)
    public Response delete(@PathVariable("id") Long id) {
        return productService.delete(id);
    }

    @GetMapping(UrlConstraint.ProductManagement.GET)
    public Response get(@PathVariable("id") Long id) {
        return productService.get(id);
    }

    @GetMapping(UrlConstraint.ProductManagement.GET_ALL)
    public Response getAll() {
        return productService.getAll();
    }
}
