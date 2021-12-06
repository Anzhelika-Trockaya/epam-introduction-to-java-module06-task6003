package by.epam.task6003.clientPart.main.menu;

public class MenuShowerUtil {
    private static final String INDENT = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
    private static final String MENU_TITLE = "--------------------------   MENU   ---------------------------";
    private static final String TITLE = "              -  -  -  -  - ARCHIVE -  -  -  -  -  ";
    private static final String VIEWING_TITLE = "--------------------------   VIEW   ---------------------------";
    private static final String ADD_TITLE = "--------------------------   ADD   ---------------------------";


    private static final String VIEW = " - VIEW - \"1\"";
    private static final String ADD = " - ADD  - \"2\"";
    private static final String EXIT = " - EXIT - \"0\"";
    private static final String ENTER_ID = "Enter the student's id or \"0\" to return: ";

    public static void showTitle(){
        System.out.println(INDENT);
        System.out.println(TITLE);
        System.out.println();
    }

    public static void showAdminStartMenu() {
        System.out.println(INDENT);
        System.out.println(TITLE);
        System.out.println(MENU_TITLE);
        System.out.println(VIEW);
        System.out.println(ADD);
        System.out.println();
        System.out.println(EXIT);
        System.out.println();
    }

    public static void showUserStartMenu() {
        System.out.println(INDENT);
        System.out.println(TITLE);
        System.out.println(MENU_TITLE);
        System.out.println(VIEW);
        System.out.println();
        System.out.println(EXIT);
        System.out.println();
    }

    public static void showViewTitle() {
        System.out.println(INDENT);
        System.out.println(VIEWING_TITLE);
    }


    public static void showChangeMenu() {
        System.out.println();
        System.out.println(" Change GENERAL DATA - \"1\"            Change PASSPORT DATA - \"2\"");
        System.out.println(" Change FAMILY DATA  - \"3\"                   Change GRADES - \"4\"");
        System.out.println(" REMOVE - \"5\"");
        System.out.println();
        System.out.println("                                        RETURN - \"0\"");
    }

    public static void showChangeGeneralDataMenu(){
        System.out.println();
        System.out.println("       Surname - \"1\"       Name - \"2\"     Patronymic - \"3\"                   Sex - \"4\"");
        System.out.println("  Birthday date - \"5\"      Group - \"6\"        Course - \"7\"     Year of enrollment - \"8\"");
        System.out.println("  Form of education - \"9\"  Basic of education - \"10\"    Residence address - \"11\"     Registration address - \"12\"");
        System.out.println();
        System.out.println("                                        RETURN - \"0\"");
    }

    public static void showChangePassportDataMenu(){
        System.out.println();
        System.out.println("       Number - \"1\"       Identification number - \"2\"     Date of issue - \"3\"");
        System.out.println("    Issued by - \"4\"                 Birth place - \"5\" ");
        System.out.println();
        System.out.println("                                        RETURN - \"0\"");
    }

    public static void showAddingMenu() {
        System.out.println(INDENT);
        System.out.println(ADD_TITLE);
        System.out.println("Enter student data:\n");
    }

    public static void showMessage(String message){
        System.out.println();
        System.out.println(message);
        System.out.println();
    }

    public static void showChangeFamilyDataMenu() {
        System.out.println();
        System.out.println("Enter family member number to change      Add new family member - \"+\"");
        System.out.println("Change marital status - \"m\"");
        System.out.println();
        System.out.println("                                           RETURN - \"0\"");

    }

    public static void showChangeFamilyMemberMenu() {
        System.out.println();
        System.out.println("       Name - \"1\"            Type - \"2\"     Birthday date - \"3\"");
        System.out.println("    Place of work - \"4\"    Position - \"5\"     REMOVE - \"6\"");
        System.out.println();
        System.out.println("                                           RETURN - \"0\"");

    }

    public static void showChangeFamilyMemberTypeMenu() {
        System.out.println();
        System.out.println("       Husband - \"1\"      Daughter - \"3\"     Mother - \"5\"      Sister - \"7\"");
        System.out.println("        Wife - \"2\"          Son - \"4\"        Father - \"6\"     Brother - \"8\"");
        System.out.println();
        System.out.println("                                            RETURN - \"0\"");

    }

    public static void showChangeGradesMenu() {
        System.out.println();
        System.out.println("Enter grades number to change      Add new grade - \"+\"");
        System.out.println();
        System.out.println("                                           RETURN - \"0\"");

    }

    public static void showChangeGradeMenu() {
        System.out.println();
        System.out.println("    Type - \"1\"      Discipline - \"2\"        Value - \"3\"    REMOVE - \"4\"");
        System.out.println();
        System.out.println("                                           RETURN - \"0\"");

    }
}
