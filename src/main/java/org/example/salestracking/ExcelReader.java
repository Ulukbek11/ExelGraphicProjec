package org.example.salestracking;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

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

    public List<Product> readExcelFile() throws IOException {
        List<Product> products = new ArrayList<>();

        String filePath = "src/main/resources/static/salesData1.xlsx";

        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(fileInputStream); // For .xlsx files

        Sheet sheet = workbook.getSheetAt(0); // Read the first sheet
        Iterator<Row> rowIterator = sheet.iterator();

        // Skip the header row if there's one
        if (rowIterator.hasNext()) {
            rowIterator.next(); // Skip the header row
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Product product = new Product();

            // Assuming columns: ID, Name, Price, Quantity, FinalPrice, Date
            // Handle the ID (int)
            product.setId((int) row.getCell(0).getNumericCellValue());

            // Handle the Name (String)
            Cell nameCell = row.getCell(1);
            if (nameCell.getCellType() == CellType.STRING) {
                product.setName(nameCell.getStringCellValue());
            } else if (nameCell.getCellType() == CellType.NUMERIC) {
                product.setName(String.valueOf(nameCell.getNumericCellValue())); // If numeric, convert to string
            }

            // Handle Price (double)
            product.setPrice(row.getCell(2).getNumericCellValue());

            // Handle Quantity (int)
            product.setQuantity((int) row.getCell(3).getNumericCellValue());

            // Handle FinalPrice (double)
            product.setFinalPrice(row.getCell(4).getNumericCellValue());

            // Handle Date (String or Date)
            Cell dateCell = row.getCell(5);
//            if (dateCell.getCellType() == CellType.STRING) {
//                product.setDate(dateCell.getStringCellValue());
//            } else if (dateCell.getCellType() == CellType.NUMERIC) {
//                // Handle date as a numeric value (Excel stores dates as serial numbers)
//                if (DateUtil.isCellDateFormatted(dateCell)) {
                    LocalDate localDate = dateCell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    product.setDate(localDate);
//                } else {
//                    product.setDate(String.valueOf(dateCell.getNumericCellValue()));
//                }
//            }

            products.add(product);
        }

        workbook.close();
        fileInputStream.close();

        return products;
    }
}