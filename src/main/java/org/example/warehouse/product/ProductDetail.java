package org.example.warehouse.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity()
public class ProductDetail {
    @Id
    private int id;

    @ManyToOne
    @MapsId
    @JoinColumn(name="id")
    private Product product;

    private String attribute;
    private String unit;
}
