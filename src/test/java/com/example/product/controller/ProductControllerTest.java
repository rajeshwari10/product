package com.example.product.controller;

import com.example.product.model.request.ValidateProfileRequest;
import com.example.product.service.ProductService;
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
public class ProductControllerTest {

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    private MockMvc mockMvc;
    private String jsonContent;
    private ValidateProfileRequest validateProfileRequest;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        jsonContent = """
                {
                    "productIds" : [1],
                    "businessProfile" : "string"
                }
                """;
        validateProfileRequest = ValidateProfileRequest.builder().businessProfile("string").productIds(List.of(BigInteger.ONE)).build();
    }

    @Test
    public void validateBusinessProfile() throws Exception {
        when(productService.validateBusinessProfile(any(ValidateProfileRequest.class))).thenReturn(Boolean.TRUE);
        mockMvc.perform(post("/product/validate/profile").contentType(MediaType.APPLICATION_JSON).content(jsonContent))
                .andDo(print())
                .andExpect(status().isOk());
        verify(productService).validateBusinessProfile(validateProfileRequest);
        verify(productService, times(1)).validateBusinessProfile(validateProfileRequest);
    }
}