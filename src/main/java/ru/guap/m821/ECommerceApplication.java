package ru.guap.m821;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.guap.m821.model.dao.Price;
import ru.guap.m821.model.dao.Product;
import ru.guap.m821.repository.PriceRepository;
import ru.guap.m821.repository.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class ECommerceApplication implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PriceRepository priceRepository;

    public static void main(String[] args) {
        SpringApplication.run(ECommerceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Product product1 = Product.builder().name("Мороженное #1").description("Мороженное #1").build();
        Product product2 = Product.builder().name("Мороженное #2").description("Мороженное #2").build();
        Product product3 = Product.builder().name("Мороженное #3").description("Мороженное #3").build();

        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime advancedDateTime = currentDateTime.plusHours(1L);
        Price price1 = Price.builder().product(product1).effectiveDateTime(currentDateTime).price(BigDecimal.valueOf(20.0)).build();
        Price price2 = Price.builder().product(product2).effectiveDateTime(currentDateTime).price(BigDecimal.valueOf(23.0)).build();
        Price price3 = Price.builder().product(product3).effectiveDateTime(currentDateTime).price(BigDecimal.valueOf(24.0)).build();
        Price price4 = Price.builder().product(product3).effectiveDateTime(advancedDateTime).price(BigDecimal.valueOf(24.01)).build();

        productRepository.saveAll(List.of(product1,
                product2,
                product3));
        priceRepository.saveAll(List.of(price1,
                price2,
                price3, price4));

    }
}
