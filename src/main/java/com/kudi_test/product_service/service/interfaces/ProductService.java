package com.kudi_test.product_service.service.interfaces;

import com.kudi_test.product_service.dto.ProductCreator;
import com.kudi_test.product_service.entity.Product;

public interface ProductService extends BaseService<Product, Long>{

    public Product create(ProductCreator productCreator);
}
