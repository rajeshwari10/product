package com.example.product.service;

import com.example.product.entity.Product;
import com.example.product.model.request.ValidateProfileRequest;
import com.example.product.service.helper.CacheHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final CacheHandler cacheHandler;

    public Boolean validateBusinessProfile(ValidateProfileRequest validateProfileRequest) {
        if (validateProfileRequest == null) {
            log.error("Request is null");
            return Boolean.FALSE;
        }
        if (validateProfileRequest.getBusinessProfile() == null) {
            log.error("Business Profile is null");
            return Boolean.FALSE;
        }
        if (validateProfileRequest.getProductIds().isEmpty()) {
            return Boolean.TRUE;
        }
        List<Product> products = cacheHandler.getAllProducts(Boolean.FALSE);
        List<Product> userProducts = products.stream().filter(p -> validateProfileRequest.getProductIds().contains(p.getProductId())).toList();
        if (userProducts.size() != validateProfileRequest.getProductIds().size()) {
            log.error("Product ID's missing in the Product Service");
            return Boolean.FALSE;
        }
        Optional<Product> optionalUpdateAllowed = userProducts.stream()
                .filter(p -> p.getUpdateAllowed().equals(Boolean.FALSE))
                .findAny();
        if (optionalUpdateAllowed.isPresent())
            return Boolean.FALSE;
        return Boolean.TRUE;
    }
}
