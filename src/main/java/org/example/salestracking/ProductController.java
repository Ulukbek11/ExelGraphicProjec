package org.example.salestracking;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class ProductController {

    List<Product> products;

    private final ExcelReader excelReader;

    public ProductController(ExcelReader excelReader) {
        this.excelReader = excelReader;
    }


    @GetMapping("products")
    public List<Product> getProducts() throws IOException {
        return products;
    }



    @GetMapping("/")
    public String index() {
        return "index.html";
    }


    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        // Сохрани файл в resources/static или обработай напрямую
            this.products = excelReader.readExcelFile(file);
            return ResponseEntity.ok("File uploaded successfully");

    }



}