package com.br.product.service;

import com.br.product.model.Product;
import com.br.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;


    public Product save ( Product product){
        return repository.save(product);
    }

    public ResponseEntity<Product> findById (String produtoId) {
        return repository.findById(produtoId).map(product -> ResponseEntity.ok().body(product))
                .orElse(ResponseEntity.notFound().build());
    }

    public void deleteById (String produtoId){
        repository.deleteById(produtoId);

    }

    public ResponseEntity<Product> update (String productId, Product product ) {
        return repository.findById(productId)
            .map(newProduct -> {
                newProduct.setProdutoId(productId);
                newProduct.setName(product.getName());
                newProduct.setPreco(product.getPreco());
                Product updated = repository.save(newProduct);
                return ResponseEntity.ok().body(updated);
            }).orElse(ResponseEntity.notFound().build());
    }

}
