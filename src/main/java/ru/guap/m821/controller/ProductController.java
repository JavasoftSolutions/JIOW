package ru.guap.m821.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.guap.m821.exception.NotFoundException;
import ru.guap.m821.model.dao.Product;
import ru.guap.m821.model.dto.ProductDto;
import ru.guap.m821.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product")
    public List<ProductDto> getAll() {
        return productRepository.getProductWithLatestPrice();
    }

    @GetMapping("/product/{code}")
    public Optional<Product> get(@PathVariable UUID code) {
        return productRepository.findById(code);
    }

    @PostMapping("/product")
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/product/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable UUID code, @RequestBody Product newProduct) {
        Optional<Product> existingProduct = productRepository.findById(code);
        if (!existingProduct.isPresent()) {
            throw  new NotFoundException(String.format("No product with code: %s", code));
        }
        newProduct.setCode(code);
        productRepository.save(newProduct);
    }

    @DeleteMapping("/product/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID code) {
        productRepository.deleteById(code);
    }

}
