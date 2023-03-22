package kg.sanack.newsstand.entity;

import kg.sanack.newsstand.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "newsstand_id", nullable = false)
    private Newsstand newsstand;

    @Enumerated(value = EnumType.STRING)
    private ProductType productType;

    private LocalDate datePublished;
}
