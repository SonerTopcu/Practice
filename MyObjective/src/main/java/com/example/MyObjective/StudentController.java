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
    public void calculateLetterGrades(List<Student> studentList){
        double average = 0;
        int standardDeviation = 5;

        for (Student student : studentList) {
            average += student.getGrade();
        }
        average = average / studentList.size();

        for (Student student : studentList) {
            int newGrade = (int) (((student.getGrade() - average) / standardDeviation) * 10 + student.getGrade());

            if (newGrade >= average + standardDeviation * 9) {
                student.setLetterGrade("A");
            } else if (newGrade >= average + standardDeviation * 8) {
                student.setLetterGrade("A-");
            } else if (newGrade >= average + standardDeviation * 7) {
                student.setLetterGrade("B+");
            } else if (newGrade >= average + standardDeviation * 6) {
                student.setLetterGrade("B");
            } else if (newGrade >= average + standardDeviation * 5) {
                student.setLetterGrade("B-");
            } else if (newGrade >= average + standardDeviation * 4) {
                student.setLetterGrade("C+");
            } else if (newGrade >= average + standardDeviation * 3) {
                student.setLetterGrade("C");
            } else if (newGrade >= average + standardDeviation * 2) {
                student.setLetterGrade("C-");
            } else if (newGrade >= average + standardDeviation) {
                student.setLetterGrade("D+");
            } else if (newGrade >= average){
                student.setLetterGrade("D");
            } else{
                student.setLetterGrade("F");
            }
        }
    }
    public List<Student> readStudentsFromExcelFile(String excelFilePath) throws IOException {
        List<Student> listStudents = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(excelFilePath);

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
                    case 2 -> student.setGrade((double) getCellValue(nextCell));
                }
            }
            listStudents.add(student);
            workbook.close();
            inputStream.close();
        }
        return listStudents;
    }
}
