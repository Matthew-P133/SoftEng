package teachingManagementSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
    static char role;
    static String name;
    static ArrayList<String> commands = new ArrayList<>();
    static boolean systemActive;

    /*
     * User Input Methods
     */
    public static String getStringInput() {
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    public static char getCharInput() {
        String input = getStringInput();
        return input.charAt(0);
    }

    public static int getNumberInput() {
        char a = getCharInput();
        return Character.getNumericValue(a);
    }

    public static String checkForName(List<Staff> listOfStaff) {
        while (true) {
            String userInputName = getStringInput();

            for (Staff eachStaffMember : listOfStaff) {
                String staffName = eachStaffMember.getName();
                if (staffName.toLowerCase().contains(userInputName.toLowerCase())) {
                    return eachStaffMember.getName();
                }
            }
        }
    }

    /*
     * System functionality Methods
     */
    public static void getRole() {
        // first find out if they are admin or course director
        System.out.println("Please enter 'a' for admin or 'c' for course director: ");
        role = getCharInput();

        // convert to lowercase for consistency
        if (role == 'A') {
            role = 'a';
        } else if (role == 'C') {
            role = 'c';
        }

        // get name
        // TODO add y/n option to go back in case of incorrect entry.
        if (role == 'a') {
            System.out.println(">>>>>> Entering Admin System. <<<<<<");
            name = "Admin";
            generateHelp(role);
        } else if (role == 'c') {
            System.out.println(">>>>>> Entering Course Director System <<<<<<");
            getName();
            generateHelp(role);
        } else {
            System.out.println("Invalid entry please try again.");
            getRole();
        }
    }

    public static void getName() {
        System.out.println("Please enter name eg. 'Mark' :");

        name = checkForName(StaffList.getDirectors());

        System.out.println("Accessing system as " + name + ", continue? (y/n) :");
        char response = getCharInput();
        if (response == 'y') {
            return;
        } else {
            getName();
        }
    }

    public static void generateHelp(char role) {
        // if user is admin
        if (role == 'a') {
            String viewTrainingRecords = "View training records for a staff member";
            String viewTeachingStaff = "View teaching staff";
            String addStaffMembers = "Add members of Staff";
            String viewCourses = "View courses";
            String updateTrainingRecords = "Update the training records of a staff member";

            commands.add(viewTrainingRecords);
            commands.add(viewTeachingStaff);
            commands.add(addStaffMembers);
            commands.add(viewCourses);
            commands.add(updateTrainingRecords);

            // if user is course director
        } else if (role == 'c') {
            String viewCourses = "View my courses";
            String createTeachingRequirement = "Create new teaching requirements for a course";
            // FIXME do we need to be able to edit teaching requirements for a course?
            String createTeachingRequest = "Create a new teaching request";
            // FIXME do we need to be able to remove a new teaching request?

            commands.add(viewCourses);
            commands.add(createTeachingRequirement);
            commands.add(createTeachingRequest);

        }

        // both will require an exit function
        String exitSystem = "Exit the system";
        commands.add(exitSystem);
    }

    public static void showHelp(char role) {
        // display commands list
        System.out.println("System options: ");
        int count = 0;
        for (String eachCommand : commands) {
            count++;
            System.out.println(count + ". " + eachCommand);
        }

        // get command from user
        System.out.println("Please enter number of desired option, eg '1' : ");
        int userInputNumber = getNumberInput();

        // check if user has selected a valid option
        while (userInputNumber <= 0 || userInputNumber > commands.size()) {
            System.out.println("Invalid option, please try again:");
            userInputNumber = getNumberInput();
        }

        // do action from input
        if (role == 'a') {
            doAdminAction(userInputNumber);
        } else if (role == 'c') {
            doCourseDirectorAction(userInputNumber);
        }
    }

    public static void doAdminAction(int selectedNumber) {
        String selectedString = commands.get(selectedNumber - 1);
        System.out.println("\n" + selectedString + ":");

        if (selectedString.contains("View training records")) {
            // view training records for a staff member
            System.out.println("Please enter the name of the person whose training records you would like to view:");
            String searchName = checkForName(listOfAllStaffNames);
            System.out.println("Training records for:" + searchName); // might not need this line
            Staff.toString(searchName);

        } else if (selectedString.contains("View teaching staff")) {
            // view teaching staff
            System.out.println("All Teaching Staff:"); // might not need this line
            StaffList.toString();

        } else if (selectedString.contains("View courses")) {
            // view courses
            System.out.println("All Courses:"); // might not need this line
            CourseList.toString();

        } else if (selectedString.contains("Update the training records")) {
            // get the training records of a staff member
            // TODO how to write this?

        } else if (selectedString.contains("Add members")) {
            // get the training records of a staff member
            // TODO add a staff member and update their training

        } else if (selectedString.contains("Exit")) {
            // exit the system.
            systemActive = false;
            System.out.println("Exiting System.");
        }
    }

    public static void doCourseDirectorAction(int selectedNumber) {
        String selectedString = commands.get(selectedNumber - 1);
        System.out.println("\n" + selectedString + ":");

        if (selectedString.contains("my courses")) {
            // view my courses
            System.out.println("Courses Directed by " + name + ":"); // might not need this line
            CourseList.toString(name);

        } else if (selectedString.contains("Create new teaching requirements")) {
            // create new teaching requirements
            // TODO not sure how to do this bit yet

        } else if (selectedString.contains("Create a new teaching request")) {
            // create new teaching request
            // TODO not sure how to do this bit yet

        } else if (selectedString.contains("Exit")) {
            // exit the system.
            systemActive = false;
            System.out.println("Exiting System.");
        }
    }

    public static void display(Object o) {
        // display it
    }

    /*
     * Main Method
     */

    public static void main(String[] args) {
        getRole();
        while (systemActive) {
            showHelp(role);
        }
    }
}
