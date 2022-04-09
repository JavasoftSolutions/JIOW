package ru.guap.m821.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.guap.m821.model.dao.Product;
import ru.guap.m821.model.dto.ProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query(nativeQuery = true)
    List<ProductDto> getProductWithLatestPrice();
}
