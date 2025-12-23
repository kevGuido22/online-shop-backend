package com.keving.online_shop.product.service;

import com.keving.online_shop.exception.BusinessException;
import com.keving.online_shop.product.model.Product;
import com.keving.online_shop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
//only for final attributes
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new BusinessException("Product not found", 404));
    }

    public Page<Product> getProducts(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }
}
