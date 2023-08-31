package com.example.product.controller;

import com.example.product.entity.Product;
import com.example.product.model.request.ValidateProfileRequest;
import com.example.product.service.helper.CacheHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CacheControllerTest {

    @Mock
    CacheHandler cacheHandler;

    @InjectMocks
    CacheController cacheController;

    private MockMvc mockMvc;
    private List<Product> productList;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(cacheController).build();
        productList = List.of(
                Product.builder().productId(BigInteger.ONE).productName("Product One").updateAllowed(Boolean.TRUE).build(),
                Product.builder().productId(BigInteger.TWO).productName("Product Two").updateAllowed(Boolean.TRUE).build());
    }

    @Test
    public void resetProducts() throws Exception {
        when(cacheHandler.getAllProducts(Boolean.TRUE)).thenReturn(productList);
        mockMvc.perform(post("/cache/product/reset").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(cacheHandler).getAllProducts(Boolean.TRUE);
        verify(cacheHandler, times(1)).getAllProducts(Boolean.TRUE);
    }
}