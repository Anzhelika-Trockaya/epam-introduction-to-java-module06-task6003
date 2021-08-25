package com.epam.task6003.clientPart.model;

import com.epam.task6003.serverPart.data.LocalDateAdapter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlType(name="student")
@XmlRootElement
public class Student {
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
        if(surname!=null && surname.matches("[a-zA-zа-яА-яЁё]+-?[a-zA-zа-яА-яЁё]+")){
            this.surname = surname;
        } else {
            throw new IllegalArgumentException("Incorrect surname!");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name!=null && name.matches("[a-zA-zа-яА-яЁё]+-?[a-zA-zа-яА-яЁё]+")){
            this.name = name;
        } else {
            throw new IllegalArgumentException("Incorrect name!");
        }
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        if(patronymic!=null && patronymic.matches("[a-zA-zа-яА-яЁё]+")){
            this.patronymic = patronymic;
        } else {
            throw new IllegalArgumentException("Incorrect patronymic!");
        }
    }


    public LocalDate birthdayDate() {
        return birthdayDate;
    }


    public void changeBirthdayDate(LocalDate birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public void changeBirthdayDate(String birthdayDateString) {
        String dateRegex = "([12][0-9][0-9][0-9]-(" +
                "((0[13578]|1[02])-(0[1-9]|1[0-9]|2[0-9]|3[01]))" +
                "|((0[469]|11)-(0[1-9]|1[0-9]|2[0-9]|3[0]))" +
                "|((02)(-(0[1-9]|1[0-9]|2[0-8])))))" +
                "|(([12][0-9]([02468][048]|[13579][26]))-(02)-(29))";
        LocalDate date;

        if(birthdayDateString.matches(dateRegex)){
            date = LocalDate.parse(birthdayDateString);
            if(date.isBefore(LocalDate.now().minusYears(15))){
                this.birthdayDate = date;
                return;
            }
        }
        throw new IllegalArgumentException("Incorrect date!");
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        if(group>0 && group<=10) {
            this.group = group;
        } else{
            throw new IllegalArgumentException("Incorrect group!");
        }
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        if(course>0 && course<=6) {
            this.course = course;
        } else{
            throw new IllegalArgumentException("Incorrect course!");
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthdayDate=" + birthdayDate +
                ", group=" + group +
                ", course=" + course +
                '}';
    }
}
