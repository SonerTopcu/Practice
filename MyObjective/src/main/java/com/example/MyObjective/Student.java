package com.example.MyObjective;
public class Student {
    private String firstName;
    private String lastName;
    private double grade;
    private String letterGrade;
    public Student(){

    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public double getGrade() {
        return grade;
    }
    public void setGrade(double grade) {
        this.grade = grade;
    }
    public String getLetterGrade() {
        return letterGrade;
    }
    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }
    public String toString(){
        return String.format("%n%s %s %.2f --> %s", firstName, lastName, grade, letterGrade);
    }
}
