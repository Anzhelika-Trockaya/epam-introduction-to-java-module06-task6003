package by.epam.task6003.clientPart.main.menu;

import by.epam.task6003.clientPart.bean.*;
import by.epam.task6003.clientPart.controller.Controller;
import by.epam.task6003.clientPart.view.UserActionView;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.time.LocalDate;

public class AdminMenu extends Menu {

    public AdminMenu(String key, Controller controller) {
        super(key, controller);
    }

    @Override
    public void processMainMenu() throws JAXBException, IOException, MenuException {
        String input;

        MenuShowerUtil.showAdminStartMenu();
        input = getUserInput().readLine("Enter here: ");

        switch (input) {
            case "1" -> processViewStudents();
            case "2" -> processAddStudent();
            case "0" -> exit();
        }

    }

    @Override
    public void processViewStudent(String id) throws JAXBException, MenuException, IOException {
        String studentMessage;
        Student student;
        UserActionView userActionView;

        userActionView = getViewProvider().getUserActionView();

        student = getStudentCase(id);

        if (student != null) {
            studentMessage = userActionView.getStudentInfo(student);
            MenuShowerUtil.showMessage(studentMessage);
            processChangeMenu(student);
        } else {
            studentMessage = "Student with id=" + id + " not exists!";
            MenuShowerUtil.showMessage(studentMessage);
            processBackMenu();
        }
    }

    private Student getStudentCase(String id) throws JAXBException, MenuException {
        Student student;
        StudentXMLConverter studentXMLConverter;
        String response;

        studentXMLConverter = StudentXMLConverter.getInstance();

        response = getStudentCaseResponse(id);

        if (response.startsWith("0\n")) {
            student = studentXMLConverter.unmarshalStudent(response.substring(2));
        } else if (response.startsWith("1")) {
            student = null;
        } else {
            throw new MenuException(response.substring(2));
        }

        return student;
    }

    private Student saveChanges(Student student) throws JAXBException, IOException, MenuException {
        String response;

        response = getChangeStudentResponse(student);

        if (response.startsWith("0")) {
            student = getStudentCase(Integer.toString(student.getId()));
        } else if (response.startsWith("1")) {
            MenuShowerUtil.showMessage(response.substring(2));
            student = getStudentCase(Integer.toString(student.getId()));
        } else {
            throw new MenuException(response.substring(2));
        }

        return student;
    }

    private String getChangeStudentResponse(Student student) throws JAXBException, IOException {
        StudentXMLConverter studentXMLConverter;
        String request;
        String response;

        studentXMLConverter = StudentXMLConverter.getInstance();

        request = "CHANGE_STUDENT key=\"" + getKey() + "\"\n" + studentXMLConverter.marshalStudent(student);
        response = getController().doAction(request);

        return response;
    }

    private void processChangeMenu(Student student) throws JAXBException, MenuException, IOException {
        String input;
        UserActionView userActionView;
        String message;

        userActionView = getViewProvider().getUserActionView();

        MenuShowerUtil.showChangeMenu();
        input = getUserInput().readLine("Enter here: ");

        while (!"0".equals(input)) {

            while (!input.matches("[0-5]")) {
                MenuShowerUtil.showMessage("Incorrect operation: " + input);
                input = getUserInput().readLine("Enter here: ");
            }

            switch (input) {
                case "1" -> student = processChangeGeneralData(student);
                case "2" -> student = processChangePassportData(student);
                case "3" -> student = processChangeFamilyData(student);
                case "4" -> student = processChangeGrades(student);
                case "5" -> student = processRemoveStudent(student);
                case "0" -> {
                }
            }

            if (student != null) {
                if (!"0".equals(input)) {
                    message = userActionView.getStudentInfo(student);
                    MenuShowerUtil.showMessage(message);
                    MenuShowerUtil.showChangeMenu();
                    input = getUserInput().readLine("Enter here: ");
                }
            } else {
                message = "This student does not exist anymore!";
                MenuShowerUtil.showMessage(message);
                processBackMenu();
                return;
            }

        }
    }

    private Student processChangeGeneralData(Student student) throws JAXBException, IOException, MenuException {
        String input;
        UserActionView userActionView;
        String message;

        userActionView = getViewProvider().getUserActionView();

        MenuShowerUtil.showMessage(userActionView.getStudentGeneralData(student));
        MenuShowerUtil.showChangeGeneralDataMenu();

        input = getUserInput().readLine("Enter here: ");

        while (!"0".equals(input)) {

            while (!input.matches("[0-9]|(10|11|12)")) {
                MenuShowerUtil.showMessage("Incorrect operation: " + input);
                input = getUserInput().readLine("Enter here: ");
            }

            switch (input) {
                case "1" -> student = processChangeSurname(student);
                case "2" -> student = processChangeName(student);
                case "3" -> student = processChangePatronymic(student);
                case "4" -> student = processChangeSex(student);
                case "5" -> student = processChangeBirthdayDate(student);
                case "6" -> student = processChangeGroup(student);
                case "7" -> student = processChangeCourse(student);
                case "8" -> student = processChangeYearOfEnrollment(student);
                case "9" -> student = processChangeFormOfEducation(student);
                case "10" -> student = processChangeBasisOfEducation(student);
                case "11" -> student = processChangeResidenceAddress(student);
                case "12" -> student = processChangeRegistrationAddress(student);
                case "0" -> {
                }
            }

            if (student != null) {
                if (!"0".equals(input)) {
                    MenuShowerUtil.showMessage(userActionView.getStudentGeneralData(student));
                    MenuShowerUtil.showChangeGeneralDataMenu();
                    input = getUserInput().readLine("Enter here: ");
                }
            } else {
                message = "This student does not exist anymore!";
                MenuShowerUtil.showMessage(message);
                processBackMenu();
            }

        }

        return student;
    }

    private Student processChangeSurname(Student student) throws JAXBException, IOException, MenuException {
        String input;

        input = getUserInput().readLine("Enter surname or \"0\" to cancel: ");

        if (!input.equals("0")) {
            student.setSurname(input);
            student = saveChanges(student);
        }

        return student;
    }

    private Student processChangeName(Student student) throws JAXBException, IOException, MenuException {
        String input;

        input = getUserInput().readLine("Enter name or \"0\" to cancel: ");

        if (!input.equals("0")) {
            student.setName(input);
            student = saveChanges(student);
        }

        return student;
    }

    private Student processChangePatronymic(Student student) throws JAXBException, IOException, MenuException {
        String input;

        input = getUserInput().readLine("Enter patronymic or \"0\" to cancel: ");

        if (!input.equals("0")) {
            student.setPatronymic(input);
            student = saveChanges(student);
        }

        return student;
    }

    private Student processChangeSex(Student student) throws JAXBException, IOException, MenuException {
        String input;

        input = getUserInput().readLine("Enter sex (\"m\" or \"f\") or \"0\" to cancel: ");

        while (!"0".equals(input) && !"m".equals(input) && !"f".equals(input)) {

            MenuShowerUtil.showMessage("Incorrect sex: " + input);
            input = getUserInput().readLine("Enter sex (\"m\" or \"f\") or \"0\" to cancel: ");

        }

        if ("m".equals(input)) {

            student.setSex(Sex.MALE);
            student = saveChanges(student);

        } else if ("f".equals(input)) {

            student.setSex(Sex.FEMALE);
            student = saveChanges(student);

        }

        return student;
    }

    private Student processChangeBirthdayDate(Student student) throws JAXBException, IOException, MenuException {
        String input;
        String dateRegex;
        LocalDate newBirthdayDate;

        dateRegex = "([12][0-9][0-9][0-9]-(" +
                "((0[13578]|1[02])-(0[1-9]|1[0-9]|2[0-9]|3[01]))" +
                "|((0[469]|11)-(0[1-9]|1[0-9]|2[0-9]|3[0]))" +
                "|((02)(-(0[1-9]|1[0-9]|2[0-8])))))" +
                "|(([12][0-9]([02468][048]|[13579][26]))-(02)-(29))";
        input = getUserInput().readLine("Enter birthday date (yyyy-mm-dd) or \"0\" to cancel: ");

        while (!input.equals("0") && !input.matches(dateRegex)) {

            MenuShowerUtil.showMessage("Incorrect date: " + input);
            input = getUserInput().readLine("Enter birthday date (yyyy-mm-dd) or \"0\" to cancel: ");

        }

        if (!input.equals("0")) {
            newBirthdayDate = LocalDate.parse(input);
            student.changeBirthdayDate(newBirthdayDate);
            student = saveChanges(student);
        }

        return student;
    }

    private Student processChangeGroup(Student student) throws JAXBException, IOException, MenuException {
        String input;
        String groupRegex;

        groupRegex = "\\d+";
        input = getUserInput().readLine("Enter group number or \"0\" to cancel: ");

        while (!input.equals("0") && !input.matches(groupRegex)) {

            MenuShowerUtil.showMessage("Incorrect group number: " + input);
            input = getUserInput().readLine("Enter group number or \"0\" to cancel: ");

        }

        if (!input.equals("0")) {
            student.setGroup(Integer.parseInt(input));
            student = saveChanges(student);
        }

        return student;
    }

    private Student processChangeCourse(Student student) throws JAXBException, IOException, MenuException {
        String input;
        String courseRegex;

        courseRegex = "\\d+";
        input = getUserInput().readLine("Enter course number or \"0\" to cancel: ");

        while (!input.equals("0") && !input.matches(courseRegex)) {

            MenuShowerUtil.showMessage("Incorrect course number: " + input);
            input = getUserInput().readLine("Enter course number or \"0\" to cancel: ");

        }

        if (!input.equals("0")) {
            student.setCourse(Integer.parseInt(input));
            student = saveChanges(student);
        }

        return student;
    }

    private Student processChangeYearOfEnrollment(Student student) throws JAXBException, MenuException, IOException {
        String input;
        String yearRegex;

        yearRegex = "2\\d\\d\\d";
        input = getUserInput().readLine("Enter year of enrollment or \"0\" to cancel: ");

        while (!input.equals("0") && !input.matches(yearRegex)) {

            MenuShowerUtil.showMessage("Incorrect year: " + input);
            input = getUserInput().readLine("Enter year of enrollment or \"0\" to cancel: ");

        }

        if (!input.equals("0")) {
            student.setYearOfEnrollment(Integer.parseInt(input));
            student = saveChanges(student);
        }

        return student;
    }

    private Student processChangeFormOfEducation(Student student) throws JAXBException, IOException, MenuException {
        String input;

        input = getUserInput().readLine("Enter form of education (\"f\" - Full-time education, \"e\" - Extramural studies) or \"0\" to cancel: ");

        while (!input.equals("0") && !"f".equals(input) && !"e".equals(input)) {

            MenuShowerUtil.showMessage("Incorrect form of education: " + input);
            input = getUserInput().readLine("Enter form of education (\"f\" - Full-time education, \"e\" - Extramural studies) or \"0\" to cancel: ");

        }

        if ("f".equals(input)) {

            student.setFormOfEducation(FormOfEducation.FULL_TIME_EDUCATION);
            student = saveChanges(student);

        } else if ("e".equals(input)) {

            student.setFormOfEducation(FormOfEducation.EXTRAMURAL_STUDIES);
            student = saveChanges(student);

        }

        return student;
    }

    private Student processChangeBasisOfEducation(Student student) throws JAXBException, IOException, MenuException {
        String input;

        input = getUserInput().readLine("Enter basis of education (\"b\" - budgetary, \"p\" - paid) or \"0\" to cancel: ");

        while (!input.equals("0") && !"b".equals(input) && !"p".equals(input)) {

            MenuShowerUtil.showMessage("Incorrect basis of education: " + input);
            input = getUserInput().readLine("Enter basis of education (\"b\" - budgetary, \"p\" - paid) or \"0\" to cancel: ");

        }

        if ("b".equals(input)) {

            student.setBasisOfEducation(BasisOfEducation.BUDGETARY);
            student = saveChanges(student);

        } else if ("p".equals(input)) {

            student.setBasisOfEducation(BasisOfEducation.PAID);
            student = saveChanges(student);

        }

        return student;
    }

    private Student processChangeResidenceAddress(Student student) throws JAXBException, IOException, MenuException {
        String input;

        input = getUserInput().readLine("Enter residence address or \"0\" to cancel: ");

        if (!input.equals("0")) {
            student.setResidenceAddress(input);
            student = saveChanges(student);
        }

        return student;
    }

    private Student processChangeRegistrationAddress(Student student) throws JAXBException, IOException, MenuException {
        String input;

        input = getUserInput().readLine("Enter registration address or \"0\" to cancel: ");

        if (!input.equals("0")) {
            student.setRegistrationAddress(input);
            student = saveChanges(student);
        }

        return student;
    }

    private Student processChangePassportData(Student student) throws JAXBException, IOException, MenuException {
        String input;
        UserActionView userActionView;
        String message;

        userActionView = getViewProvider().getUserActionView();
        MenuShowerUtil.showMessage(userActionView.getStudentPassportData(student.getPassport()));
        MenuShowerUtil.showChangePassportDataMenu();

        input = getUserInput().readLine("Enter here: ");

        while (!"0".equals(input)) {

            while (!input.matches("[0-5]")) {
                MenuShowerUtil.showMessage("Incorrect operation: " + input);
                input = getUserInput().readLine("Enter here: ");
            }

            switch (input) {
                case "1" -> student = processChangePassportNumber(student);
                case "2" -> student = processChangePassportIdentificationNumber(student);
                case "3" -> student = processChangePassportDateOfIssue(student);
                case "4" -> student = processChangePassportIssuedBy(student);
                case "5" -> student = processChangePassportBirthPlace(student);
                case "0" -> {
                }
            }

            if (student != null) {
                if (!"0".equals(input)) {
                    MenuShowerUtil.showMessage(userActionView.getStudentPassportData(student.getPassport()));
                    MenuShowerUtil.showChangePassportDataMenu();
                    input = getUserInput().readLine("Enter here: ");
                }
            } else {
                message = "This student does not exist anymore!";
                MenuShowerUtil.showMessage(message);
                processBackMenu();
            }

        }

        return student;
    }

    private Student processChangePassportNumber(Student student) throws JAXBException, IOException, MenuException {
        String input;

        input = getUserInput().readLine("Enter number or \"0\" to cancel: ");

        if (!input.equals("0")) {
            student.getPassport().setNumber(input);
            student = saveChanges(student);
        }

        return student;
    }

    private Student processChangePassportIdentificationNumber(Student student) throws JAXBException, IOException, MenuException {
        String input;

        input = getUserInput().readLine("Enter identification number or \"0\" to cancel: ");

        if (!input.equals("0")) {
            student.getPassport().setIdentificationNumber(input);
            student = saveChanges(student);
        }

        return student;
    }

    private Student processChangePassportDateOfIssue(Student student) throws JAXBException, IOException, MenuException {
        String input;
        String dateRegex;
        LocalDate newDate;

        dateRegex = "([12][0-9][0-9][0-9]-(" +
                "((0[13578]|1[02])-(0[1-9]|1[0-9]|2[0-9]|3[01]))" +
                "|((0[469]|11)-(0[1-9]|1[0-9]|2[0-9]|3[0]))" +
                "|((02)(-(0[1-9]|1[0-9]|2[0-8])))))" +
                "|(([12][0-9]([02468][048]|[13579][26]))-(02)-(29))";
        input = getUserInput().readLine("Enter date og issue (yyyy-mm-dd) or \"0\" to cancel: ");

        while (!input.equals("0") && !input.matches(dateRegex)) {

            MenuShowerUtil.showMessage("Incorrect date: " + input);
            input = getUserInput().readLine("Enter date og issue (yyyy-mm-dd) or \"0\" to cancel: ");

        }

        if (!input.equals("0")) {
            newDate = LocalDate.parse(input);
            student.getPassport().changeDateOfIssue(newDate);
            student = saveChanges(student);
        }

        return student;
    }

    private Student processChangePassportIssuedBy(Student student) throws JAXBException, IOException, MenuException {
        String input;

        input = getUserInput().readLine("Enter name of the issuing authority or \"0\" to cancel: ");

        if (!input.equals("0")) {
            student.getPassport().setIssuedBy(input);
            student = saveChanges(student);
        }

        return student;
    }

    private Student processChangePassportBirthPlace(Student student) throws JAXBException, IOException, MenuException {
        String input;

        input = getUserInput().readLine("Enter birth place or \"0\" to cancel: ");

        if (!input.equals("0")) {
            student.getPassport().setBirthPlace(input);
            student = saveChanges(student);
        }

        return student;
    }

    private Student processChangeFamilyData(Student student) throws JAXBException, IOException, MenuException {
        UserActionView userActionView;
        String input;
        int number;
        String message;

        userActionView = getViewProvider().getUserActionView();
        MenuShowerUtil.showMessage(userActionView.getStudentFamilyData(student));
        MenuShowerUtil.showChangeFamilyDataMenu();

        input = getUserInput().readLine("Enter here: ");

        while (!"0".equals(input)) {

            while (!input.matches("\\d+|\\+|m")) {
                MenuShowerUtil.showMessage("Incorrect operation: " + input);
                input = getUserInput().readLine("Enter here: ");
            }

            switch (input) {
                case "m" -> student = changeStudentMaritalStatus(student);
                case "+" -> student = processAddFamilyMember(student);
                case "0" -> {
                }
                default -> {
                    number = Integer.parseInt(input);

                    if (number <= student.getFamily().size()) {
                        student = processChangeFamilyMember(student, number);
                    } else {
                        MenuShowerUtil.showMessage("Family member with number " + number + " not exists!");
                    }
                }
            }

            if (student != null) {
                if (!"0".equals(input)) {
                    MenuShowerUtil.showMessage(userActionView.getStudentFamilyData(student));
                    MenuShowerUtil.showChangeFamilyDataMenu();
                    input = getUserInput().readLine("Enter here: ");
                }
            } else {
                message = "This student does not exist anymore!";
                MenuShowerUtil.showMessage(message);
                processBackMenu();
                return null;
            }

        }

        return student;
    }

    private Student changeStudentMaritalStatus(Student student) throws JAXBException, IOException, MenuException {
        student.setMarried(!student.isMarried());
        student = saveChanges(student);

        return student;
    }

    private Student processAddFamilyMember(Student student) throws JAXBException, IOException, MenuException {
        FamilyMember familyMember;
        int familyMemberIndex;

        familyMember = new FamilyMember();

        student.getFamily().add(familyMember);

        familyMemberIndex = student.getFamily().size() - 1;

        student = processChangeFamilyMemberName(student, familyMemberIndex);
        student = processChangeFamilyMemberType(student, familyMemberIndex);
        student = processChangeFamilyMemberBirthdayDate(student, familyMemberIndex);
        student = processChangeFamilyMemberPlaceOfWork(student, familyMemberIndex);
        student = processChangeFamilyMemberPosition(student, familyMemberIndex);


        return student;
    }

    private Student processChangeFamilyMember(Student student, int number) throws JAXBException, IOException, MenuException {
        int familyMemberIndex;
        String input;
        UserActionView userActionView;
        String familyMemberInfo;
        String message;

        userActionView = getViewProvider().getUserActionView();
        familyMemberIndex = number - 1;
        familyMemberInfo = userActionView.getFamilyMemberInfo(number, student.getFamily().get(familyMemberIndex));

        MenuShowerUtil.showMessage(familyMemberInfo);
        MenuShowerUtil.showChangeFamilyMemberMenu();

        input = getUserInput().readLine("Enter here: ");

        while (!"0".equals(input)) {

            while (!input.matches("[0-6]")) {
                MenuShowerUtil.showMessage("Incorrect operation: " + input);
                input = getUserInput().readLine("Enter here: ");
            }

            switch (input) {
                case "1" -> student = processChangeFamilyMemberName(student, familyMemberIndex);
                case "2" -> student = processChangeFamilyMemberType(student, familyMemberIndex);
                case "3" -> student = processChangeFamilyMemberBirthdayDate(student, familyMemberIndex);
                case "4" -> student = processChangeFamilyMemberPlaceOfWork(student, familyMemberIndex);
                case "5" -> student = processChangeFamilyMemberPosition(student, familyMemberIndex);
                case "6" -> student = processDeleteFamilyMember(student, familyMemberIndex);
                case "0" -> {
                }
            }

            if (student != null) {
                if (!"0".equals(input)) {
                    MenuShowerUtil.showMessage(userActionView.getFamilyMemberInfo(number, student.getFamily().get(familyMemberIndex)));
                    MenuShowerUtil.showChangeFamilyMemberMenu();
                    input = getUserInput().readLine("Enter here: ");
                }
            } else {
                message = "This student does not exist anymore!";
                MenuShowerUtil.showMessage(message);
                processBackMenu();
                return null;
            }

        }

        return student;
    }

    private Student processDeleteFamilyMember(Student student, int familyMemberNumber) throws JAXBException, IOException, MenuException {
        String input;

        MenuShowerUtil.showMessage("Are you sure?");
        input = getUserInput().readLine("Enter \"y\" to remove: ");

        if ("y".equals(input)) {
            student.getFamily().remove(familyMemberNumber);
            student = saveChanges(student);
        }

        return student;

    }

    private Student processChangeFamilyMemberName(Student student, int familyMemberNumber) throws JAXBException, IOException, MenuException {
        String input;

        input = getUserInput().readLine("Enter name or \"0\" to cancel: ");

        if (!input.equals("0")) {
            student.getFamily().get(familyMemberNumber).setName(input);
            student = saveChanges(student);
        }

        return student;
    }

    private Student processChangeFamilyMemberType(Student student, int familyMemberIndex) throws JAXBException, IOException, MenuException {
        String input;
        FamilyMember familyMember;

        MenuShowerUtil.showChangeFamilyMemberTypeMenu();
        familyMember = student.getFamily().get(familyMemberIndex);
        input = getUserInput().readLine("Enter type number or \"0\" to cancel: ");

        if (!input.equals("0")) {

            while (!input.matches("[0-8]")) {
                MenuShowerUtil.showMessage("Incorrect type number: " + input);
                input = getUserInput().readLine("Enter type number or \"0\" to cancel: ");
            }

            switch (input) {
                case "1" -> familyMember.setType(FamilyMemberType.HUSBAND);
                case "2" -> familyMember.setType(FamilyMemberType.WIFE);
                case "3" -> familyMember.setType(FamilyMemberType.DAUGHTER);
                case "4" -> familyMember.setType(FamilyMemberType.SON);
                case "5" -> familyMember.setType(FamilyMemberType.MOTHER);
                case "6" -> familyMember.setType(FamilyMemberType.FATHER);
                case "7" -> familyMember.setType(FamilyMemberType.SISTER);
                case "8" -> familyMember.setType(FamilyMemberType.BROTHER);
                case "0" -> {
                }
            }

            if (!input.equals("0")) {
                student = saveChanges(student);
            }

        }

        return student;
    }

    private Student processChangeFamilyMemberBirthdayDate(Student student, int familyMemberIndex) throws JAXBException, IOException, MenuException {
        String input;
        String dateRegex;
        LocalDate newBirthdayDate;

        dateRegex = "([12][0-9][0-9][0-9]-(" +
                "((0[13578]|1[02])-(0[1-9]|1[0-9]|2[0-9]|3[01]))" +
                "|((0[469]|11)-(0[1-9]|1[0-9]|2[0-9]|3[0]))" +
                "|((02)(-(0[1-9]|1[0-9]|2[0-8])))))" +
                "|(([12][0-9]([02468][048]|[13579][26]))-(02)-(29))";
        input = getUserInput().readLine("Enter birthday date (yyyy-mm-dd) or \"0\" to cancel: ");

        while (!input.equals("0") && !input.matches(dateRegex)) {

            MenuShowerUtil.showMessage("Incorrect date: " + input);
            input = getUserInput().readLine("Enter birthday date (yyyy-mm-dd) or \"0\" to cancel: ");

        }

        if (!input.equals("0")) {
            newBirthdayDate = LocalDate.parse(input);
            student.getFamily().get(familyMemberIndex).changeBirthdayDate(newBirthdayDate);
            student = saveChanges(student);
        }

        return student;
    }

    private Student processChangeFamilyMemberPlaceOfWork(Student student, int familyMemberIndex) throws JAXBException, IOException, MenuException {
        String input;

        input = getUserInput().readLine("Enter place of work or \"0\" to cancel: ");

        if (!input.equals("0")) {
            student.getFamily().get(familyMemberIndex).setPlaceOfWork(input);
            student = saveChanges(student);
        }

        return student;
    }

    private Student processChangeFamilyMemberPosition(Student student, int familyMemberIndex) throws JAXBException, IOException, MenuException {
        String input;

        input = getUserInput().readLine("Enter position or \"0\" to cancel: ");

        if (!input.equals("0")) {
            student.getFamily().get(familyMemberIndex).setPosition(input);
            student = saveChanges(student);
        }

        return student;
    }

    private Student processChangeGrades(Student student) throws JAXBException, IOException, MenuException {
        UserActionView userActionView;
        String input;
        int number;
        String message;

        userActionView = getViewProvider().getUserActionView();
        MenuShowerUtil.showMessage(userActionView.getStudentGrades(student));
        MenuShowerUtil.showChangeGradesMenu();

        input = getUserInput().readLine("Enter here: ");

        while (!"0".equals(input)) {

            while (!input.matches("\\d+|\\+")) {
                MenuShowerUtil.showMessage("Incorrect operation: " + input);
                input = getUserInput().readLine("Enter here: ");
            }

            switch (input) {
                case "+" -> student = processAddGrade(student);
                case "0" -> {
                }
                default -> {
                    number = Integer.parseInt(input);

                    if (number > 0 && number <= student.getGrades().size()) {
                        student = processChangeGrade(student, number);
                    } else {
                        MenuShowerUtil.showMessage("Grade with number " + number + " not exists!");
                    }
                }
            }

            if (student != null) {
                if (!"0".equals(input)) {
                    MenuShowerUtil.showMessage(userActionView.getStudentGrades(student));
                    MenuShowerUtil.showChangeGradesMenu();
                    input = getUserInput().readLine("Enter here: ");
                }
            } else {
                message = "This student does not exist anymore!";
                MenuShowerUtil.showMessage(message);
                processBackMenu();
                return null;
            }
        }

        return student;
    }

    private Student processAddGrade(Student student) throws JAXBException, IOException, MenuException {
        Grade grade;
        int gradeIndex;

        grade = new Grade();
        student.getGrades().add(grade);
        gradeIndex = student.getGrades().size() - 1;

        student = processChangeGradeType(student, gradeIndex);
        student = processChangeGradeDiscipline(student, gradeIndex);
        student = processChangeGradeValue(student, gradeIndex);

        return student;
    }

    private Student processChangeGradeType(Student student, int index) throws JAXBException, IOException, MenuException {
        String input;

        input = getUserInput().readLine("Enter grade type (\"a\" - attestation or \"e\" - exam) or \"0\" to cancel: ");

        while (!input.equals("0") && !"a".equals(input) && !"e".equals(input)) {

            MenuShowerUtil.showMessage("Incorrect grade type: " + input);
            input = getUserInput().readLine("Enter grade type (\"a\" - attestation or \"e\" - exam) or \"0\" to cancel: ");

        }

        if ("a".equals(input)) {
            student.getGrades().get(index).setType(GradeType.ATTESTATION);
            student = saveChanges(student);
        } else if ("e".equals(input)) {
            student.getGrades().get(index).setType(GradeType.EXAM);
            student = saveChanges(student);
        }

        return student;
    }

    private Student processChangeGradeDiscipline(Student student, int index) throws JAXBException, IOException, MenuException {
        String input;

        input = getUserInput().readLine("Enter discipline or \"0\" to cancel: ");

        if (!input.equals("0")) {
            student.getGrades().get(index).setDiscipline(input);
            student = saveChanges(student);
        }

        return student;
    }

    private Student processChangeGradeValue(Student student, int index) throws JAXBException, IOException, MenuException {
        String input;
        String gradeRegex;

        gradeRegex = "[1-9]|(10)";
        input = getUserInput().readLine("Enter grade value (1-10) or \"0\" to cancel: ");

        while (!input.equals("0") && !input.matches(gradeRegex)) {

            MenuShowerUtil.showMessage("Incorrect grade: " + input);
            input = getUserInput().readLine("Enter grade value (1-10) or \"0\" to cancel: ");

        }

        if (!input.equals("0")) {
            student.getGrades().get(index).setGradeValue(Integer.parseInt(input));
            student = saveChanges(student);
        }

        return student;
    }

    private Student processChangeGrade(Student student, int number) throws JAXBException, IOException, MenuException {
        String input;
        int gradeIndex;
        String message;
        UserActionView userActionView;

        userActionView = getViewProvider().getUserActionView();
        gradeIndex = number - 1;
        message = userActionView.getGradeInfo(student.getGrades().get(gradeIndex), number);
        MenuShowerUtil.showMessage(message);
        MenuShowerUtil.showChangeGradeMenu();
        input = getUserInput().readLine("Enter here: ");

        while (!"0".equals(input)) {

            while (!input.matches("[0-6]|s")) {
                MenuShowerUtil.showMessage("Incorrect operation: " + input);
                input = getUserInput().readLine("Enter here: ");
            }

            switch (input) {
                case "1" -> student = processChangeGradeType(student, gradeIndex);
                case "2" -> student = processChangeGradeDiscipline(student, gradeIndex);
                case "3" -> student = processChangeGradeValue(student, gradeIndex);
                case "4" -> student = processRemoveGrade(student, gradeIndex);
                case "0" -> {
                }
            }

            if (student != null) {
                if (!"0".equals(input) && student.getGrades().size()>gradeIndex) {
                    message = userActionView.getGradeInfo(student.getGrades().get(gradeIndex), number);
                    MenuShowerUtil.showMessage(message);
                    MenuShowerUtil.showChangeGradeMenu();
                    input = getUserInput().readLine("Enter here: ");
                }
            } else {
                message = "This student does not exist anymore!";
                MenuShowerUtil.showMessage(message);
                processBackMenu();
            }

        }

        return student;
    }

    private Student processRemoveGrade(Student student, int index) throws JAXBException, IOException, MenuException {
        String input;

        MenuShowerUtil.showMessage("Are you sure you want to delete a student's case?");
        input = getUserInput().readLine("Enter \"y\" to remove case: ");

        if ("y".equals(input)) {
            student.getGrades().remove(index);
            student = saveChanges(student);
        }

        return student;
    }

    private Student processRemoveStudent(Student student) {
        String input;
        boolean isRemoved;

        MenuShowerUtil.showMessage("Are you sure you want to delete a student's case?");
        input = getUserInput().readLine("Enter \"y\" to remove case: ");

        if ("y".equals(input)) {
            isRemoved = removeStudent(student.getId());

            if (isRemoved) {
                student = null;
            }
        }

        return student;
    }

    private boolean removeStudent(int id) {
        String request;
        String response;
        String message;
        UserActionView userActionView;

        userActionView = getViewProvider().getUserActionView();
        request = "REMOVE_STUDENT key=\"" + getKey() + "\" id=\"" + id + "\"";
        response = getController().doAction(request);
        message = userActionView.getInfo(response);

        MenuShowerUtil.showMessage(message);

        return response.startsWith("0");
    }

    private void processAddStudent() throws JAXBException, IOException, MenuException {
        Student student;
        String message;
        UserActionView userActionView;

        userActionView = getViewProvider().getUserActionView();

        student = processCreateStudent();
        student = addStudent(student);

        student = student != null ? processChangeSex(student) : null;
        student = student != null ? processChangeBirthdayDate(student) : null;
        student = student != null ? processChangeGroup(student) : null;
        student = student != null ? processChangeCourse(student) : null;
        student = student != null ? processChangeYearOfEnrollment(student) : null;
        student = student != null ? processChangeFormOfEducation(student) : null;
        student = student != null ? processChangeBasisOfEducation(student) : null;
        student = student != null ? processChangeResidenceAddress(student) : null;
        student = student != null ? processChangeRegistrationAddress(student) : null;

        if (student != null) {
            message = userActionView.getStudentInfo(student);
            MenuShowerUtil.showMessage(message);
            processChangeMenu(student);
        }
    }

    private Student processCreateStudent() {
        Student student;
        String input;

        student = new Student();

        MenuShowerUtil.showAddingMenu();

        input = getUserInput().readLine("Enter surname: ");
        student.setSurname(input);
        input = getUserInput().readLine("Enter name: ");
        student.setName(input);
        input = getUserInput().readLine("Enter patronymic: ");
        student.setPatronymic(input);

        return student;
    }

    private Student addStudent(Student student) throws JAXBException, IOException, MenuException {
        String request;
        String response;
        String message;
        Student resultStudent;
        StudentXMLConverter studentXMLConverter;
        UserActionView userActionView;

        userActionView = getViewProvider().getUserActionView();
        studentXMLConverter = StudentXMLConverter.getInstance();

        request = "ADD_STUDENT key=\"" + getKey() + "\"\n" + studentXMLConverter.marshalStudent(student);
        response = getController().doAction(request);

        if (response.startsWith("0\n")) {

            resultStudent = studentXMLConverter.unmarshalStudent(response.substring(2));

        } else if (response.startsWith("1")) {

            resultStudent = null;
            message = userActionView.getInfo(response);
            MenuShowerUtil.showMessage(message);

        } else {
            throw new MenuException(response.substring(3));
        }

        return resultStudent;
    }

}
