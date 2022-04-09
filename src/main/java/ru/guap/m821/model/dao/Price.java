package ru.guap.m821.model.dao;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "price",
indexes = {
        @Index(name = "idx_product_code", columnList = "product_code"),
        @Index(name = "idx_effective_date_time", columnList = "effectiveDateTime"),
        @Index(name = "idx_price", columnList = "price"),
})
public class Price {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID code;
    @Column
    private BigDecimal price;
    @Column
    private LocalDateTime effectiveDateTime;
    @ManyToOne //(fetch = FetchType.LAZY)
    @JsonIgnore
    private Product product;
}
