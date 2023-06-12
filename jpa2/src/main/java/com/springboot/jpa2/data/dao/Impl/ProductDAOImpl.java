package com.springboot.jpa2.data.dao.Impl;

import com.springboot.jpa2.data.dao.ProductDAO;
import com.springboot.jpa2.data.entity.Product;
import com.springboot.jpa2.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class ProductDAOImpl implements ProductDAO {

    private final ProductRepository productRepository;

    @Autowired
    public ProductDAOImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }


    @Override
    public Product insertProduct(Product product) {
        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    @Override
    public Product selectProduct(Long number) {
        // getById로 key number 값을 받아서 로우 값을 리턴해 주는 듯.
        Product selectedProduct = productRepository.getById(number);

        return selectedProduct;
    }
    //Repository 메소드 공부 요망
    /*
        업데이트 하는 건데 number 이상한거 치면 null 되니까 Optional 사용한거고 inPresent로 있는지 확인 후 안에 값 product 엔티티에 넣어 주는 거 거거 ㅓㄱ
     */
    @Override
    public Product updateProductName(Long number, String name) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);

        Product updatedProduct;
        if(selectedProduct.isPresent()){
            Product product = selectedProduct.get();

            product.setName(name);
            product.setUpdatedAt(LocalDateTime.now());
            updatedProduct = productRepository.save(product);
        } else{
            throw new Exception();
        }

        return updatedProduct;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);

        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();

            productRepository.delete(product);
        } else{
            // 이거 앞에 메소드 가서 오류 날려줘서 계속 쓰로우 했는지 의문 걍 여기서 오류 발생시키면 되는건데 왜 굳이 위에 throws 계속 하는 거지 ?
            throw new Exception();
        }
    }


}
