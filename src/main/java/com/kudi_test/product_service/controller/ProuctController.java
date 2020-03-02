package com.kudi_test.product_service.controller;

import com.kudi_test.product_service.dto.ProductCreator;
import com.kudi_test.product_service.entity.Product;
import com.kudi_test.product_service.service.interfaces.ProductService;
import com.kudi_test.product_service.utils.Utility;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/product")
public class ProuctController {

    final private ProductService productService;

    public ProuctController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public Page<Product> getProducts(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "500") int size ){
        return productService.getAll(Utility.getPageable(page, size));
    }

    @GetMapping("/{uuid}")
    public Product getProducts(@PathVariable(name = "uuid") String productUuid ){
        return productService.findByUuid(productUuid);
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody ProductCreator productCreator){
        Product product = productService.create(productCreator);
        return ResponseEntity.created( URI.create(Utility.getRequestUrl().concat("/").concat(product.getUuid())) )
                .body(product);
    }

    @PutMapping("/{uuid}")
    public Product update(@PathVariable("uuid") String productUuid, Product product){
        return productService.update(productUuid, product);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity delete(@PathVariable("uuid") String productUuid){
        productService.delete(productUuid);
        return ResponseEntity.ok().build();
    }
}
