package com.example.MyObjective;

import org.apache.poi.ss.formula.atp.Switch;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@org.springframework.web.bind.annotation.RestController
public class StudentController {
    private Object getCellValue(Cell cell) {
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> cell.getNumericCellValue();
            default -> null;
        };
    }
    private String calculateLetterGrades(double grade){
        int value = (int)Math.round(grade);
        if(value >= 95){
            return "A";
        } else if (value >= 90) {
            return "A-";
        } else if (value >= 86) {
            return "B+";
        } else if (value >= 82) {
            return "B";
        } else if (value >= 78) {
            return "B-";
        } else if (value >= 74) {
            return "C+";
        } else if (value >= 70) {
            return "C";
        } else if (value >= 63) {
            return "C-";
        } else if (value >= 57) {
            return "D+";
        } else if (value >= 51) {
            return "D";
        } else{
            return "F";
        }
    }
    public List<Student> readStudentsFromExcelFile(String excelFilePath) throws IOException {
        List<Student> listStudents = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);

        for (Row nextRow : firstSheet) {
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            Student student = new Student();

            while (cellIterator.hasNext()) {
                Cell nextCell = cellIterator.next();
                int columnIndex = nextCell.getColumnIndex();

                switch (columnIndex) {
                    case 0 -> student.setFirstName((String) getCellValue(nextCell));
                    case 1 -> student.setLastName((String) getCellValue(nextCell));
                    case 2 -> {
                        student.setGrade((double) getCellValue(nextCell));
                        student.setLetterGrade(calculateLetterGrades((double)getCellValue(nextCell)));
                    }
                }
            }
            listStudents.add(student);
        }
        workbook.close();
        inputStream.close();

        return listStudents;
    }
}
