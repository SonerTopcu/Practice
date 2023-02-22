package com.example.MyObjective;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class MyObjectiveApplication {
	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(StudentController.class)
				.web(WebApplicationType.NONE).run();
		String excelFilePath = "src/main/resources/Student Grades/Student Grades.xlsx";
		StudentController controller = new StudentController();
		List<Student> studentList = controller.readStudentsFromExcelFile(excelFilePath);
		System.out.println(studentList.toString().replace("[","").replace("]","").replace(",", ""));

		SpringApplication.exit(ctx, () -> 0);
	}
}
