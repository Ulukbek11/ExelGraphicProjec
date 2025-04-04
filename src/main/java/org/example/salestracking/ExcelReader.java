package org.example.salestracking;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelReader {

    public List<Product> readExcelFile(MultipartFile file) throws IOException {
        List<Product> products = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(file.getInputStream());

        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Product product = new Product();

            //ID, Name, Price, Quantity, FinalPrice, Date
            product.setId((int) row.getCell(0).getNumericCellValue());

            Cell nameCell = row.getCell(1);
            if (nameCell.getCellType() == CellType.STRING) {
                product.setName(nameCell.getStringCellValue());
            } else if (nameCell.getCellType() == CellType.NUMERIC) {
                product.setName(String.valueOf(nameCell.getNumericCellValue()));
            }

            product.setPrice(row.getCell(2).getNumericCellValue());

            product.setQuantity((int) row.getCell(3).getNumericCellValue());

            product.setFinalPrice(row.getCell(4).getNumericCellValue());

            Cell dateCell = row.getCell(5);
            LocalDate localDate = dateCell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            product.setDate(localDate);

            products.add(product);
        }

        workbook.close();

        return products;
    }
}