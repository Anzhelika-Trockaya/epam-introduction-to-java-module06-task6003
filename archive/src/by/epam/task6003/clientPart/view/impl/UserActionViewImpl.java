package by.epam.task6003.clientPart.view.impl;

import by.epam.task6003.clientPart.bean.FamilyMember;
import by.epam.task6003.clientPart.bean.Grade;
import by.epam.task6003.clientPart.bean.Passport;
import by.epam.task6003.clientPart.view.UserActionView;
import by.epam.task6003.clientPart.bean.Student;

import java.util.List;

public class UserActionViewImpl implements UserActionView {
    @Override
    public String getAuthorizationInfo(String response) {
        String info;

        if (response.startsWith("0 ")) {
            info = "Authorization was successful.";
        } else if (response.startsWith("1 ")) {
            info = response.substring(1);
        } else {
            info = getExceptionInfo(response);
        }

        return info;
    }


    @Override
    public String getExceptionInfo(String response) {
        String info;

        if (response.startsWith("-1")) {
            info = response.substring(3);
        } else {
            info = "ERROR! " + response;
        }

        return info;
    }

    @Override
    public String getStudentInfo(Student student) {
        StringBuilder infoBuilder;

        infoBuilder = new StringBuilder("    __________ STUDENT CASE__________ \n\n");

        infoBuilder.append(getStudentGeneralData(student))
                .append(getStudentPassportData(student.getPassport()))
                .append(getStudentFamilyData(student))
                .append(getStudentGrades(student));


        return infoBuilder.toString();
    }

    @Override
    public String getStudentGeneralData(Student student) {
        StringBuilder infoBuilder;

        infoBuilder = new StringBuilder();

        infoBuilder.append("   1. GENERAL DATA     ").append('\n')
                .append("Surname: ").append(student.getSurname()).append('\n')
                .append("Name: ").append(student.getName()).append('\n')
                .append("Patronymic: ").append(student.getPatronymic()).append('\n')
                .append("Year of enrollment: ").append(student.getYearOfEnrollment()).append('\n')
                .append("Birthday date: ").append(student.takeBirthdayDate()).append('\n')
                .append("Sex: ").append(student.getSex()).append('\n')
                .append("Form of education: ").append(student.getFormOfEducation()).append('\n')
                .append("Basic of education: ").append(student.getBasisOfEducation()).append('\n')
                .append("Course: ").append(student.getCourse()).append('\n')
                .append("Group: ").append(student.getGroup()).append('\n')
                .append("Residence address: ").append(student.getResidenceAddress()).append('\n')
                .append("Registration address: ").append(student.getRegistrationAddress()).append('\n')
                .append('\n');

        return infoBuilder.toString();
    }

    @Override
    public String getStudentPassportData(Passport passport) {
        StringBuilder infoBuilder;

        infoBuilder = new StringBuilder();

        infoBuilder.append("    2. PASSPORT DATA ").append('\n')
                .append("Number: ").append(passport.getNumber()).append('\n')
                .append("Identification number: ").append(passport.getIdentificationNumber()).append('\n')
                .append("Date of issue: ").append(passport.takeDateOfIssue()).append('\n')
                .append("Issued by: ").append(passport.getIssuedBy()).append('\n')
                .append("Birthday place: ").append(passport.getBirthPlace()).append('\n')
                .append('\n');

        return infoBuilder.toString();
    }

    @Override
    public String getStudentFamilyData(Student student) {
        StringBuilder infoBuilder;

        infoBuilder = new StringBuilder();

        infoBuilder.append("    3. FAMILY DATA ").append('\n')
                .append("Is married: ").append(student.isMarried()).append('\n')
                .append(getFamilyMembersTable(student.getFamily())).append('\n')
                .append('\n');

        return infoBuilder.toString();
    }

    @Override
    public String getStudentGrades(Student student) {
        StringBuilder infoBuilder;

        infoBuilder = new StringBuilder();

        infoBuilder.append("    4. GRADES").append('\n')
                .append(getGradesTable(student.getGrades())).append('\n')
                .append('\n');


        return infoBuilder.toString();
    }

    private String getGradesTable(List<Grade> grades) {
        String lineFormat;
        StringBuilder tableBuilder;
        String titleLine;
        String gradeLine;
        Grade currentGrade;
        int number;

        lineFormat = "\n| %10s | %12s | %40s | %10s |";
        tableBuilder = new StringBuilder();

        titleLine = String.format(lineFormat, "№", "type", "discipline", "value");
        tableBuilder.append(titleLine);

        if (grades != null) {
            for (int i = 0; i < grades.size(); i++) {
                currentGrade = grades.get(i);
                number = i + 1;
                gradeLine = String.format(lineFormat, number, currentGrade.getType(), currentGrade.getDiscipline(), currentGrade.getGradeValue());
                tableBuilder.append(gradeLine);
            }
        }

        return tableBuilder.toString();
    }

    private String getFamilyMembersTable(List<FamilyMember> family) {
        String lineFormat;
        StringBuilder tableBuilder;
        String titleLine;
        String memberLine;
        FamilyMember currentFamilyMember;
        int number;


        lineFormat = "\n| %10s | %10s | %35s | %15s | %40s | %20s";
        tableBuilder = new StringBuilder();

        titleLine = String.format(lineFormat, "№", "type", "name", "birthday date", "place of work", "position");////////////////////////Number
        tableBuilder.append(titleLine);

        if (family != null) {
            for (int i = 0; i < family.size(); i++) {
                currentFamilyMember = family.get(i);
                number = i + 1;
                memberLine = String.format(lineFormat, number, currentFamilyMember.getType(), currentFamilyMember.getName()
                        , currentFamilyMember.takeBirthdayDate(), currentFamilyMember.getPlaceOfWork(), currentFamilyMember.getPosition());
                tableBuilder.append(memberLine);
            }
        }

        return tableBuilder.toString();
    }

    @Override
    public String getFamilyMemberInfo(int number, FamilyMember familyMember) {
        String lineFormat;
        StringBuilder tableBuilder;
        String titleLine;
        String memberLine;


        lineFormat = "\n| %10s | %10s | %35s | %15s | %40s | %20s";
        tableBuilder = new StringBuilder();

        titleLine = String.format(lineFormat, "№", "type", "name", "birthday date", "place of work", "position");
        tableBuilder.append(titleLine);

        if (familyMember != null) {
            memberLine = String.format(lineFormat, number, familyMember.getType(), familyMember.getName()
                    , familyMember.takeBirthdayDate(), familyMember.getPlaceOfWork(), familyMember.getPosition());
            tableBuilder.append(memberLine);
        }

        return tableBuilder.toString();
    }

    @Override
    public String getInfo(String response) {
        String info;

        if (response.startsWith("0") || response.startsWith("1")) {
            info = response.substring(2);
        } else {
            info = getExceptionInfo(response);
        }

        return info;
    }

    @Override
    public String getGradeInfo(Grade grade, int number) {
        StringBuilder gradeBuilder;
        String lineFormat;
        String titleLine;
        String gradeLine;

        gradeBuilder = new StringBuilder("CHANGE GRADE ");

        lineFormat = "\n| %10s | %12s | %40s | %10s |";

        titleLine = String.format(lineFormat, "№", "type", "discipline", "value");
        gradeBuilder.append(titleLine);

        gradeLine = String.format(lineFormat, number, grade.getType(), grade.getDiscipline(), grade.getGradeValue());
        gradeBuilder.append(gradeLine);

        return gradeBuilder.toString();
    }
}
