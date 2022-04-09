package ru.guap.m821.model.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@Value
@AllArgsConstructor
public class ProductDto {
    private final String name;
    private final String description;
    private final BigDecimal price;
}
