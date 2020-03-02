package com.kudi_test.product_service.service;

import com.kudi_test.product_service.dto.ProductCreator;
import com.kudi_test.product_service.entity.Product;
import com.kudi_test.product_service.repository.ProductRepository;
import com.kudi_test.product_service.service.interfaces.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, Long> implements ProductService {

    final private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        super(productRepository);
        this.productRepository = productRepository;
    }

    @Override
    public Product create(ProductCreator productCreator) {
        Product product = new Product();
        BeanUtils.copyProperties(productCreator, product);
        return this.save(product);
    }
}