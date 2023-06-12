package com.springboot.jpa2.controller;

import ch.qos.logback.classic.Logger;
import com.springboot.jpa2.data.dto.ChangeProductNameDto;
import com.springboot.jpa2.data.dto.ProductDto;
import com.springboot.jpa2.data.dto.ProductResponseDto;
import com.springboot.jpa2.service.ProductService;

import org.jboss.jandex.Main;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
//    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class.getName());

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // .getProduct(number)는 productDAO.selectProduct(number)로  productRepository.getById(number)에 접근해서 엔티티값을
    // 가져오고 productResponseDto에 저장해서 리턴한다.  고로 productResponseDto변수는 입력받은 number값의 로우값이다.
    // 정수값을 입력받아 db정보를 리턴함.
    @GetMapping()
    public ResponseEntity<ProductResponseDto> getProduct(Long number){
        ProductResponseDto productResponseDto = productService.getProduct(number);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @PostMapping()
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductDto productDto) {
        ProductResponseDto productResponseDto = productService.saveProduct(productDto);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @PutMapping()
    public ResponseEntity<ProductResponseDto> changeProductName(
            @RequestBody ChangeProductNameDto changeProductNameDto) throws Exception{

        ProductResponseDto productResponseDto = productService.changeProductName(
                changeProductNameDto.getNumber(),
                changeProductNameDto.getName());

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteProduct(Long number) throws Exception{
        productService.deleteProduct(number);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }
}
