package com.example.product.controller;

import com.example.product.entity.Product;
import com.example.product.service.helper.CacheHandler;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title = "Product Service")
)
@Slf4j
@Tag(name = "Cache Controller")
@RestController
@AllArgsConstructor
@RequestMapping("/cache")
public class CacheController {

    private final CacheHandler cacheHandler;

    @PostMapping("/product/reset")
    public List<Product> resetProducts() {
        return cacheHandler.getAllProducts(Boolean.TRUE);
    }
}
