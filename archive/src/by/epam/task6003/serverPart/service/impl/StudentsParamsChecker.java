package by.epam.task6003.serverPart.service.impl;

import by.epam.task6003.serverPart.bean.Grade;
import by.epam.task6003.serverPart.bean.Student;

import java.time.LocalDate;
import java.util.List;

public class StudentsParamsChecker {
    private static StudentsParamsChecker instance = new StudentsParamsChecker();

    private StudentsParamsChecker() {
    }

    public static StudentsParamsChecker getInstance() {
        return instance;
    }

    public void checkParams(Student student) throws ParamsException {
        checkName(student.getSurname());
        checkName(student.getName());
        checkName(student.getPatronymic());
        checkYear(student.getYearOfEnrollment());
        checkGroup(student.getGroup());
        checkCourse(student.getCourse());
        checkGradesList(student.getGrades());
    }

    private void checkName(String name) throws ParamsException {
        String nameRegex;

        nameRegex = "[a-zA-Zа-яА-ЯёЁ-]+";

        if (name == null || !name.matches(nameRegex)) {
            throw new ParamsException("Incorrect name: " + name + "!");
        }
    }

    private void checkGradesList(List<Grade> grades) throws ParamsException {
        if (grades != null) {
            for (Grade grade : grades) {

                if (grade.getGradeValue() < 0 || grade.getGradeValue() > 10) {
                    throw new ParamsException("Incorrect grade: " + grade.getGradeValue() + "!");
                }
            }
        }
    }

    private void checkYear(int year) throws ParamsException {
        if ((year < 1900 && year != 0) || year > LocalDate.now().getYear()) {
            throw new ParamsException("Incorrect year: " + year + "!");
        }
    }

    private void checkGroup(int group) throws ParamsException {
        int defaultGroup = 0;
        int maxGroup = 10;
        if (group < defaultGroup || group > maxGroup) {
            throw new ParamsException("Incorrect group number: " + group + "!");
        }
    }

    private void checkCourse(int course) throws ParamsException {
        int defaultCourse = 0;
        int maxCourse = 6;
        if (course < defaultCourse || course > maxCourse) {
            throw new ParamsException("Incorrect course: " + course + "!");
        }
    }
}
