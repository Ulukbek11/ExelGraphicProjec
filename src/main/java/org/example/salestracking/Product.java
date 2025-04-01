package org.example.salestracking;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private int id;
    private String name;
    private double price;
    private int quantity;
    private double finalPrice;
    private LocalDate date;


    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', price=" + price +
                ", quantity=" + quantity + ", finalPrice=" + finalPrice + ", date='" + date + "'}";
    }
}
