package com.springboot.jpa2.service;

import com.springboot.jpa2.data.dto.ProductDto;
import com.springboot.jpa2.data.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto getProduct(Long number);

    ProductResponseDto saveProduct(ProductDto productDto);

    ProductResponseDto changeProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;


}
