package by.epam.task6003.clientPart.view;


import by.epam.task6003.clientPart.bean.FamilyMember;
import by.epam.task6003.clientPart.bean.Grade;
import by.epam.task6003.clientPart.bean.Passport;
import by.epam.task6003.clientPart.bean.Student;

public interface UserActionView {

    String getAuthorizationInfo(String response);

    String getExceptionInfo(String exceptionMessage);

    String getStudentInfo(Student student);

    String getStudentGeneralData(Student student);

    String getStudentPassportData(Passport passport);

    String getStudentFamilyData(Student student);

    String getFamilyMemberInfo(int number, FamilyMember familyMember);

    String getStudentGrades(Student student);

    String getInfo(String response);

    String getGradeInfo(Grade grade, int number);
}
