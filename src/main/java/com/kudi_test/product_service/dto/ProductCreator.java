package com.kudi_test.product_service.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ProductCreator {

    private String name;

    private String description;

    private Map<String, String> attributes;
}
