package com.example.product.service.helper;

import com.example.product.entity.Product;
import com.example.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CacheHandler {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    @Cacheable(value = "allProducts", condition = "!#reset")
    @CacheEvict(value = "allProducts", condition = "#reset", beforeInvocation = true, allEntries = true)
    public List<Product> getAllProducts(Boolean reset) {
        return productRepository.findAll();
    }
}
