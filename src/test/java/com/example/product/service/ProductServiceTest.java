package com.example.product.service;

import com.example.product.entity.Product;
import com.example.product.model.request.ValidateProfileRequest;
import com.example.product.service.helper.CacheHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@RunWith(JUnit4.class)
public class ProductServiceTest {

    @Mock
    CacheHandler cacheHandler;

    @InjectMocks
    ProductService productService;

    private List<Product> productList;
    private ValidateProfileRequest validateProfileRequest;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        productList = List.of(
                Product.builder().productId(BigInteger.ONE).productName("Product One").updateAllowed(Boolean.TRUE).build(),
                Product.builder().productId(BigInteger.TWO).productName("Product Two").updateAllowed(Boolean.TRUE).build());
        validateProfileRequest = ValidateProfileRequest.builder().businessProfile("string").productIds(List.of(BigInteger.ONE)).build();
    }

    @Test
    public void validateBusinessProfile() {
        when(cacheHandler.getAllProducts(Boolean.FALSE)).thenReturn(productList);
        assertSame(Boolean.TRUE, productService.validateBusinessProfile(validateProfileRequest));
    }

    @Test
    public void validateBusinessProfileUpdateNotAllowed() {
        productList.get(0).setUpdateAllowed(Boolean.FALSE);
        when(cacheHandler.getAllProducts(Boolean.FALSE)).thenReturn(productList);
        assertSame(Boolean.FALSE, productService.validateBusinessProfile(validateProfileRequest));
    }

    @Test
    public void validateBusinessProfileInvalidProductIdsReceived() {
        validateProfileRequest.setProductIds(List.of(BigInteger.ONE, BigInteger.TEN));
        when(cacheHandler.getAllProducts(Boolean.FALSE)).thenReturn(productList);
        assertSame(Boolean.FALSE, productService.validateBusinessProfile(validateProfileRequest));
    }

    @Test
    public void validateBusinessProfileRequestNull() {
        when(cacheHandler.getAllProducts(Boolean.FALSE)).thenReturn(productList);
        assertSame(Boolean.FALSE, productService.validateBusinessProfile(null));
    }

    @Test
    public void validateBusinessProfileIsNull() {
        validateProfileRequest.setBusinessProfile(null);
        when(cacheHandler.getAllProducts(Boolean.FALSE)).thenReturn(productList);
        assertSame(Boolean.FALSE, productService.validateBusinessProfile(validateProfileRequest));
    }

    @Test
    public void validateBusinessProfileProductIdsEmpty() {
        validateProfileRequest.setProductIds(new ArrayList<>());
        when(cacheHandler.getAllProducts(Boolean.FALSE)).thenReturn(productList);
        assertSame(Boolean.TRUE, productService.validateBusinessProfile(validateProfileRequest));
    }
}