package com.example.product.service.helper;

import com.example.product.entity.Product;
import com.example.product.repository.ProductRepository;
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
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@RunWith(JUnit4.class)
public class CacheHandlerTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    CacheHandler cacheHandler;

    private List<Product> productList;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        productList = List.of(
                Product.builder().productId(BigInteger.ONE).productName("Product One").updateAllowed(Boolean.TRUE).build(),
                Product.builder().productId(BigInteger.TWO).productName("Product Two").updateAllowed(Boolean.TRUE).build());
    }

    @Test
    public void getAllProducts() {
        when(productRepository.findAll()).thenReturn(productList);
        assertThat(cacheHandler.getAllProducts(Boolean.FALSE)).hasSize(2);
    }
}