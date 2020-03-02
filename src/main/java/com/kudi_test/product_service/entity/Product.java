package com.kudi_test.product_service.entity;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(name = "product")
public class Product extends Model {

    private String name;

    private String description;

    @ElementCollection
    private Map<String, String> attributes;

}
