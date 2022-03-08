package teachingManagementSystem;

import java.util.HashMap;
import java.util.Scanner;


/**
 * User Interface for the system.
 *
 * @author Hannah
 *
 */

public class UI {
    private static char role;
    private static String name;
    private static boolean systemActive;
    static HashMap<Integer, String> commands = new HashMap<Integer, String>();


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
        String a = getStringInput();
        return Integer.parseInt(a);
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

        name = getStringInput();

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

            commands.put(1, "View teaching staff");
            commands.put(2, "View course directors");
            commands.put(3, "View training records for a staff member");
            commands.put(4, "View courses");
            commands.put(5, "Add a new teacher");
            commands.put(6, "Add a new course director");
            commands.put(7, "Add a new course");
            commands.put(8, "Update the training records of a staff member");
            commands.put(9, "Transfer a course to a new director");
            commands.put(10, "Exit the system");
            

        // if user is course director
        } else if (role == 'c') {

            commands.put(1, "View my courses");
            commands.put(2, "Create new teaching requirements for a course");
            commands.put(3, "View teaching requirements for a course");
            commands.put(4, "Create or delete a teaching request");
            commands.put(5, "View all teaching requests");
            commands.put(6, "Exit the system");
            
        }
    }

    public static void showHelp(char role) {
        // display commands list
        System.out.println("\nSystem options: ");
        for (int i : commands.keySet()) {
            System.out.println(i + ". " + commands.get(i));
        }

        // get command from user
        System.out.println("\nPlease enter number of desired option, eg '1' : ");
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

    public static void doAdminAction(int selectedNumber) { //TODO fix order

        System.out.println("\n" + commands.get(selectedNumber) + ":");

        if (selectedNumber == 3) {
            // view training records for a staff member
            System.out.println("Please enter the name of the person whose training records you would like to view:");
            String searchName = getStringInput();
            System.out.println("Training records for: " + searchName); // might not need this line
            display(ManagementSystem.getTraining(searchName));

        } else if (selectedNumber == 1) {
            // view teaching staff
            System.out.println("All Teaching Staff:"); // might not need this line
            display(ManagementSystem.getTeachers());

        } else if (selectedNumber == 2) {
            // view course directors
            System.out.println("All Course Directors:"); // might not need this line
            display(ManagementSystem.getDirectors());

        }else if (selectedNumber == 6) {
            System.out.println("Please enter the name of the course director that you would like to add:");
            String newDirectorName = getStringInput();
            ManagementSystem.addDirector(newDirectorName);

        } else if (selectedNumber == 9) {
            // transfer director for a course
            System.out.println("Please enter the new director name:");
            String newDirName = getStringInput();
            display(ManagementSystem.getCourses(newDirName));

            // get new course name to transfer
            System.out.println("Please enter the course that you wish transfer to this director: ");
            String courseName = getStringInput();
            ManagementSystem.updateCourse(newDirName, courseName);

            // show confirmation
            System.out.println(courseName + " transferred to " + newDirName);
            display(ManagementSystem.getCourses(newDirName));

        } else if (selectedNumber == 4) {
            // view courses
            System.out.println("All Courses:"); 
            display(ManagementSystem.getCourses());

        } else if (selectedNumber == 8) {
            // display the current training records
            System.out.println("Please enter the staff name to view training records:");
            String inputName = getStringInput();
            display(ManagementSystem.getTraining(inputName));

            // get new training to add
            System.out.println("Please enter the training records that you wish to add or delete: ");
            String newTraining = getStringInput();
            ManagementSystem.updateTraining(inputName, newTraining);

            // display updated
            System.out.println(inputName);
            display(ManagementSystem.getTraining(inputName));


        } else if (selectedNumber == 5) {
            System.out.println("Please enter the name of the teacher that you would like to add:");
            String newTeacherName = getStringInput();
            ManagementSystem.addTeacher(newTeacherName);


        } else if (selectedNumber == 7) {
            System.out.println("Please enter the name of the course that you would like to add:");
            String newCourseName = getStringInput();

            System.out.println("Please enter the name director for this course:");
            // TODO check direcotor exists
            String directorName = getStringInput();
            ManagementSystem.addCourse(directorName, newCourseName);

            System.out.println("New course: " + newCourseName + " directed by " + directorName);

        } else if (selectedNumber == 10) {
            // exit the system.
            systemActive = false;
            System.out.println("Exiting System.");
            ManagementSystem.exit();
        }
    }

    public static void doCourseDirectorAction(int selectedNumber) {
        String selectedString = commands.get(selectedNumber - 1);
        System.out.println("\n" + selectedString + ":");

        if (selectedNumber == 1) {
            // view my courses
            System.out.println("Courses Directed by " + name + ":"); // might not need this line
            display(ManagementSystem.getCourses(name));

        } else if (selectedNumber == 2) {
            // create new teaching requirements
            // TODO not sure how to do this bit yet

        } else if (selectedNumber == 3) {
            // TODO 

        }else if (selectedNumber == 5) {
            // TODO 

        }else if (selectedNumber == 4) {
            // create new teaching request
            // TODO not sure how to do this bit yet

        } else if (selectedNumber == 6) {
            // exit the system.
            systemActive = false;
            System.out.println("Exiting System.");
        }
    }

    public static void display(Object o) {
        // display it
    	if (o != null) {
    		System.out.println(o.toString());
        	System.out.println("");
    	} else {
    		System.out.println("Please try again...");
    	}
    }

    /*
     * Main Method
     */

    public static void main(String[] args) {
        systemActive = true;
        ManagementSystem.start();

        getRole();
        while (systemActive) {
            showHelp(role);
        }
    } 
}
 