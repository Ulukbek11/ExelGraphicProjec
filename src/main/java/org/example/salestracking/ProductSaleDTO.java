package org.example.salestracking;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductSaleDTO {
    private int id;
    private double totalPrice;
    private LocalDate date;

    public ProductSaleDTO(Product product) {
        this.id = product.getId();
        this.totalPrice = product.getFinalPrice();
//        this.date = LocalDate.parse(product.getDate());
    }
}
