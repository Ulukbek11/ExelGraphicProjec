package org.example.salestracking;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class ProductController {


    private final ExcelReader excelReader;

    public ProductController(ExcelReader excelReader) {
        this.excelReader = excelReader;
    }


    @GetMapping("products")
    public List<Product> getProducts() throws IOException {
        return excelReader.readExcelFile();
    }
    @GetMapping("/lastMonth")
    public List<Product> getLastMonthProducts() throws IOException {
        return excelReader.readExcelFile().stream().filter(
                product -> product.getDate()
                        .isAfter(LocalDate.now()
                                .minusMonths(1)))
                .collect(Collectors.toList());
    }

    @GetMapping("/")
    public String index() {
        return "index.html";
    }





}