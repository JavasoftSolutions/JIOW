package ru.guap.m821.model.dao;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import ru.guap.m821.model.dto.ProductDto;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"priceList"})
@Entity
@Table(name = "product")
@NamedNativeQuery(name = "Product.getProductWithLatestPrice",
        query = "select p.name, p.description, pr.price from product p join price pr on p.code = pr.product_code " +
                "            where pr.code in (select pr.code from price pr join " +
                "            (select product_code, max(pr1.effective_date_time) as max_date_time from price pr1 group by product_code) as max_price " +
                "            on max_price.product_code = pr.product_code and max_price.max_date_time = pr.effective_date_time)",
        resultSetMapping = "Mapping.ProductDto")
@SqlResultSetMapping(name = "Mapping.ProductDto",
        classes = @ConstructorResult(targetClass = ProductDto.class,
                columns = {@ColumnResult(name = "name"),
                        @ColumnResult(name = "description"),
                        @ColumnResult(name = "price")}))
public class Product {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID code;
    @Column
    private String name;
    @Column
    private String description;
    @OneToMany(mappedBy = "product")
    List<Price> priceList;
}
