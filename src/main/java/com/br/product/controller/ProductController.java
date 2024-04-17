package com.br.product.controller;

import com.br.product.model.Product;
import com.br.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/products")
    public Product salvarProduto(@RequestBody Product product) {
        return service.save(product);
    }

    @GetMapping("/products/{produtoId}")
    public ResponseEntity<Product> findByIdProduct (@PathVariable String produtoId) {
        return service.findById(produtoId);
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable("id") String produtoId) {
        service.deleteById(produtoId);
    }

    @PutMapping("/products/{produtoId}")
    public Product update (@PathVariable String produtoId,
                                     @RequestBody Product product) {
        return service.update(produtoId, product).getBody();
    }
}
