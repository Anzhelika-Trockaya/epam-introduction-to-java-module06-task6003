package by.epam.task6003.serverPart.bean;

import by.epam.task6003.serverPart.dao.impl.LocalDateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlType(name = "student")
@XmlRootElement
public class Student implements Serializable {
    private int id;
    private String surname;
    private String name;
    private String patronymic;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate birthdayDate;
    private Sex sex;
    private boolean isMarried;

    private List<FamilyMember> family;
    private List<Grade> grades;
    private Passport passport;
    private String registrationAddress;
    private String residenceAddress;

    private int yearOfEnrollment;
    private FormOfEducation formOfEducation;
    private BasisOfEducation basisOfEducation;
    private int group;
    private int course;

    public Student() {
        this.family = new ArrayList<>();
        this.grades = new ArrayList<>();
        this.passport = new Passport();
    }

    public Student(int id, String surname, String name, String patronymic, LocalDate birthdayDate, Sex sex) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthdayDate = birthdayDate;
        this.sex = sex;
        this.isMarried = false;
        this.family = new ArrayList<>();
        this.grades = new ArrayList<>();
        this.passport = new Passport();
        this.registrationAddress = "";
        this.residenceAddress = "";
        this.yearOfEnrollment = 0;
        this.formOfEducation = null;
        this.basisOfEducation = null;
        this.group = 0;
        this.course = 0;
    }

    public Student(int id, String surname, String name, String patronymic, LocalDate birthdayDate, Sex sex
            , boolean isMarried, Passport passport, String registrationAddress, String residenceAddress) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthdayDate = birthdayDate;
        this.sex = sex;
        this.isMarried = isMarried;
        this.passport = passport;
        this.registrationAddress = registrationAddress;
        this.residenceAddress = residenceAddress;
        this.family = new ArrayList<>();
        this.grades = new ArrayList<>();
        this.yearOfEnrollment = 0;
        this.formOfEducation = null;
        this.basisOfEducation = null;
        this.group = 0;
        this.course = 0;
    }

    public Student(int id, String surname, String name, String patronymic, LocalDate birthdayDate, Sex sex
            , boolean isMarried, Passport passport, String registrationAddress, String residenceAddress
            , int yearOfEnrollment, FormOfEducation formOfEducation, BasisOfEducation basisOfEducation) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthdayDate = birthdayDate;
        this.sex = sex;
        this.isMarried = isMarried;
        this.passport = passport;
        this.registrationAddress = registrationAddress;
        this.residenceAddress = residenceAddress;
        this.yearOfEnrollment = yearOfEnrollment;
        this.formOfEducation = formOfEducation;
        this.basisOfEducation = basisOfEducation;
        this.family = new ArrayList<>();
        this.grades = new ArrayList<>();
        this.group = 0;
        this.course = 0;
    }

    public Student(int id, String surname, String name, String patronymic, LocalDate birthdayDate, Sex sex
            , boolean isMarried, List<FamilyMember> family, Passport passport, String registrationAddress
            , String residenceAddress, int yearOfEnrollment, FormOfEducation formOfEducation
            , BasisOfEducation basisOfEducation, int group, int course) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthdayDate = birthdayDate;
        this.sex = sex;
        this.isMarried = isMarried;
        this.family = family;
        this.passport = passport;
        this.registrationAddress = registrationAddress;
        this.residenceAddress = residenceAddress;
        this.yearOfEnrollment = yearOfEnrollment;
        this.formOfEducation = formOfEducation;
        this.basisOfEducation = basisOfEducation;
        this.group = group;
        this.course = course;
        this.family = new ArrayList<>();
        this.grades = new ArrayList<>();
    }

    public Student(int id, String surname, String name, String patronymic, LocalDate birthdayDate, Sex sex
            , boolean isMarried, List<FamilyMember> family, List<Grade> grades, Passport passport
            , String registrationAddress, String residenceAddress, int yearOfEnrollment
            , FormOfEducation formOfEducation, BasisOfEducation basisOfEducation, int group, int course) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthdayDate = birthdayDate;
        this.sex = sex;
        this.isMarried = isMarried;
        this.family = family;
        this.grades = grades;
        this.passport = passport;
        this.registrationAddress = registrationAddress;
        this.residenceAddress = residenceAddress;
        this.yearOfEnrollment = yearOfEnrollment;
        this.formOfEducation = formOfEducation;
        this.basisOfEducation = basisOfEducation;
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

    public LocalDate takeBirthdayDate() {
        return birthdayDate;
    }

    public void changeBirthdayDate(LocalDate birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public void setMarried(boolean married) {
        isMarried = married;
    }

    public List<FamilyMember> getFamily() {
        return family;
    }

    public void setFamily(List<FamilyMember> family) {
        this.family = family;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public String getRegistrationAddress() {
        return registrationAddress;
    }

    public void setRegistrationAddress(String registrationAddress) {
        this.registrationAddress = registrationAddress;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    public int getYearOfEnrollment() {
        return yearOfEnrollment;
    }

    public void setYearOfEnrollment(int yearOfEnrollment) {
        this.yearOfEnrollment = yearOfEnrollment;
    }

    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    public void setFormOfEducation(FormOfEducation formOfEducation) {
        this.formOfEducation = formOfEducation;
    }

    public BasisOfEducation getBasisOfEducation() {
        return basisOfEducation;
    }

    public void setBasisOfEducation(BasisOfEducation basisOfEducation) {
        this.basisOfEducation = basisOfEducation;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return id == student.id &&
                isMarried == student.isMarried &&
                yearOfEnrollment == student.yearOfEnrollment &&
                group == student.group &&
                course == student.course &&
                Objects.equals(surname, student.surname) &&
                Objects.equals(name, student.name) &&
                Objects.equals(patronymic, student.patronymic) &&
                Objects.equals(birthdayDate, student.birthdayDate) &&
                sex == student.sex &&
                Objects.equals(family, student.family) &&
                Objects.equals(grades, student.grades) &&
                Objects.equals(passport, student.passport) &&
                Objects.equals(registrationAddress, student.registrationAddress) &&
                Objects.equals(residenceAddress, student.residenceAddress) &&
                formOfEducation == student.formOfEducation &&
                basisOfEducation == student.basisOfEducation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, patronymic, birthdayDate, sex, isMarried, family, grades, passport, registrationAddress, residenceAddress, yearOfEnrollment, formOfEducation, basisOfEducation, group, course);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthdayDate=" + birthdayDate +
                ", sex=" + sex +
                ", isMarried=" + isMarried +
                ", family=" + family +
                ", grades=" + grades +
                ", passport=" + passport +
                ", registrationAddress='" + registrationAddress + '\'' +
                ", residenceAddress='" + residenceAddress + '\'' +
                ", yearOfEnrollment=" + yearOfEnrollment +
                ", formOfEducation=" + formOfEducation +
                ", basisOfEducation=" + basisOfEducation +
                ", group=" + group +
                ", course=" + course +
                '}';
    }
}
