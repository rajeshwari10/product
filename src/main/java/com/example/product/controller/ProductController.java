package com.example.product.controller;

import com.example.product.model.request.ValidateProfileRequest;
import com.example.product.service.ProductService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@OpenAPIDefinition(
        info = @Info(
                title = "Product Service")
)
@Slf4j
@Tag(name = "Product Controller")
@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/validate/profile")
    public Boolean validateBusinessProfile(@RequestBody ValidateProfileRequest validateProfileRequest){
        return productService.validateBusinessProfile(validateProfileRequest);
    }

}
