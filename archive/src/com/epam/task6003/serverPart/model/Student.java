package com.epam.task6003.serverPart.model;

import com.epam.task6003.serverPart.data.LocalDateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlType(name = "student")
@XmlRootElement
public class Student {
    private static int currentId = 1;
    private int id;
    private String surname;
    private String name;
    private String patronymic;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate birthdayDate;
    private int group;
    private int course;

    public Student() {
    }

    public Student(String surname, String name, String patronymic, LocalDate birthdayDate, int group, int course) {
        this.id = currentId;
        currentId++;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthdayDate = birthdayDate;
        this.group = group;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }


    public LocalDate birthdayDate() {
        return birthdayDate;
    }


    public void changeBirthdayDate(LocalDate birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public static int currentId() {
        return currentId;
    }

    public static void changeCurrentId(int value) {
        currentId = value;
    }

    public static void incrementCurrentId() {
        currentId++;
    }
}
