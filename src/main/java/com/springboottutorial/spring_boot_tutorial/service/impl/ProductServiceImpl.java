package com.springboottutorial.spring_boot_tutorial.service.impl;

import com.springboottutorial.spring_boot_tutorial.dto.ProductDto;
import com.springboottutorial.spring_boot_tutorial.dto.Response;
import com.springboottutorial.spring_boot_tutorial.model.Product;
import com.springboottutorial.spring_boot_tutorial.repository.ProductRepository;
import com.springboottutorial.spring_boot_tutorial.service.ProductService;
import com.springboottutorial.spring_boot_tutorial.util.ResponseBuilder;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final String root = "Product";
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper){
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public Response save(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        product = productRepository.save(product);
        if(product != null){
            return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, root+" created Successfully", null);
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
    }

    @Override
    public Response update(Long id, ProductDto productDto) {
        Product product = productRepository.findByIdAndIsActiveTrue(id);
        if(product != null){
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            modelMapper.map(productDto, product);
            product = productRepository.save(product);
            if(product != null){
                return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root+" updated Successfully", null);
            }
            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root+" not found");
    }

    @Override
    public Response delete(Long id) {
        Product product = productRepository.findByIdAndIsActiveTrue(id);
        if(product != null){
            product.setIsActive(false);
            product = productRepository.save(product);
            if(product != null){
                return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root+" deleted Successfully", null);
            }
            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root+" not found");
    }

    @Override
    public Response get(Long id) {
        Product product = productRepository.findByIdAndIsActiveTrue(id);
        if(product != null){
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
           ProductDto productDto = modelMapper.map(product, ProductDto.class);
            if(product != null){
                return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root+" retrieved Successfully", productDto);
            }
            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root+" not found");
    }

    @Override
    public Response getAll() {
        List<Product> products = productRepository.findAllByIsActiveTrue();
        List<ProductDto> productDtos = this.getProducts(products);
        return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root+" retrieved Successfully", productDtos);
    }

    private List<ProductDto> getProducts(List<Product> products){
        List<ProductDto> productDtos = new ArrayList<>();
        products.forEach(product -> {
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            ProductDto productDto = modelMapper.map(product, ProductDto.class);
            productDtos.add(productDto);
        });
        return productDtos;
    }
}
