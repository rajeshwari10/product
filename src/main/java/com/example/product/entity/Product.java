package com.example.product.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @Column(name = "product_id", nullable = false)
    private BigInteger productId;

    @NotEmpty
    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "update_allowed")
    private Boolean updateAllowed = Boolean.FALSE;
}
