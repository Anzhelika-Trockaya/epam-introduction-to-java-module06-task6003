package com.epam.task6003.clientPart.main;

public class MenuShowerUtil {
    private static final String INDENT = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
    private static final String MENU_TITLE = "--------------------------   MENU   ---------------------------";
    private static final String TITLE = "              -  -  -  -  - ARCHIVE -  -  -  -  -  ";
    private static final String VIEWING_TITLE = "--------------------------   VIEW   ---------------------------";
    private static final String CHANGE_TITLE = "--------------------------   CHANGE   ---------------------------";
    private static final String ADD_TITLE = "--------------------------   ADD   ---------------------------";

    private static final String VIEW = " - VIEW a student case - \"1\"";
    private static final String ADD = " - ADD a new case - \"2\"";
    private static final String EXIT = " - EXIT - \"0\"";
    private static final String ENTER_ID = "Enter the student id or \"0\" to return: ";
    private static final String ENTER_HERE = "Enter here: ";

    static void showTitle(){
        System.out.println(INDENT);
        System.out.println(TITLE);
        System.out.println();
    }

    static void showAdminStartMenu() {
        System.out.println(INDENT);
        System.out.println(TITLE);
        System.out.println(MENU_TITLE);
        System.out.println(VIEW);
        System.out.println(ADD);
        System.out.println();
        System.out.println(EXIT);
        System.out.println();
        System.out.print(ENTER_HERE);
    }

    static void showUserStartMenu() {
        System.out.println(INDENT);
        System.out.println(TITLE);
        System.out.println(MENU_TITLE);
        System.out.println(VIEW);
        System.out.println();
        System.out.println(EXIT);
        System.out.println();
        System.out.print(ENTER_HERE);
    }

    static void showViewingMenu() {
        System.out.println(INDENT);
        System.out.println(VIEWING_TITLE);
        System.out.println(ENTER_ID);
        System.out.print(ENTER_HERE);
    }

    static void showChangeMenu(String studentString) {
        System.out.println(INDENT);
        System.out.println(CHANGE_TITLE);
        System.out.println(studentString);
        System.out.println();
        System.out.println("       Surname - \"1\"              Name - \"2\"     Patronymic - \"3\"");
        System.out.println("  BirthdayDate - \"4\"             Group - \"5\"         Course - \"6\"");
        System.out.println("\n  RETURN - any other symbol\n");
        System.out.print(ENTER_HERE);
    }

    static void showAddingMenu() {
        System.out.println(INDENT);
        System.out.println(ADD_TITLE);
        System.out.println("Enter student data:\n");
    }
}
