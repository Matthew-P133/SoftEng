package teachingManagementSystem;

import java.util.ArrayList;
import java.util.List;
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
    private static ArrayList<String> commands = new ArrayList<>(); 
    private static boolean systemActive;


    // below is subject to change, probably going to use a hashmap FIXME
    // admin commands
    static String viewTeachingStaff = "View teaching staff";
    static String viewCourseDirectors = "View coursse directors";
    static String viewTrainingRecords = "View training records for a staff member";
    static String viewCourses = "View courses";
    static String addTeacher = "Add a new teacher";
    static String addDirector = "Add a new course director";
    static String addCourse = "Add a new course";
    static String updateTrainingRecords = "Update the training records of a staff member";
    static String updateDirectorCourses = "Update courses associated with a course director";

    // course director commands
    static String viewDirectorCourses = "View my courses";
    static String createTeachingRequirement = "Create new teaching requirements for a course";
    static String viewTeachingRequirement = "View teaching requirements for a course";
    static String updateTeachingRequest = "Create or delete a teaching request";
    static String viewTeachingRequests = "View all teaching requests";

    static String exitSystem = "Exit the system";

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

     /*
     * FIXME use a hashmap??
     */
    public static void generateHelp(char role) {
        // if user is admin
        if (role == 'a') {
            commands.add(viewTeachingStaff);
            commands.add(viewCourseDirectors);
            commands.add(viewTrainingRecords);
            commands.add(viewCourses);
            commands.add(addTeacher);
            commands.add(addDirector);
            commands.add(addCourse);
            commands.add(updateTrainingRecords);
            commands.add(updateDirectorCourses);
            

        // if user is course director
        } else if (role == 'c') {

            commands.add(viewCourses);
            commands.add(viewTeachingRequirement);
            commands.add(viewTeachingRequests);
            commands.add(createTeachingRequirement);
            commands.add(updateTeachingRequest);
            
        }

        // both will require an exit function
        commands.add(exitSystem);
    }

    public static void showHelp(char role) {
        // display commands list
        System.out.println("\nSystem options: ");
        int count = 0;
        for (String eachCommand : commands) {
            count++;
            System.out.println(count + ". " + eachCommand);
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
        String selectedString = commands.get(selectedNumber - 1);
        System.out.println("\n" + selectedString + ":");

        if (selectedString.equals(viewTrainingRecords)) {
            // view training records for a staff member
            System.out.println("Please enter the name of the person whose training records you would like to view:");
            String searchName = getStringInput();
            System.out.println("Training records for: " + searchName); // might not need this line
            display(ManagementSystem.getTraining(searchName));

        } else if (selectedString.equals(viewTeachingStaff)) {
            // view teaching staff
            System.out.println("All Teaching Staff:"); // might not need this line
            display(ManagementSystem.getTeachers());

        } else if (selectedString.equals(viewCourseDirectors)) {
            // view course directors
            System.out.println("All Course Directors:"); // might not need this line
            display(ManagementSystem.getDirectors());

        }else if (selectedString.equals(addDirector)) {
            System.out.println("Please enter the name of the course director that you would like to add:");
            String newDirectorName = getStringInput();
            ManagementSystem.addDirector(newDirectorName);

        } else if (selectedString.equals(updateDirectorCourses)) {
            // transfer director for a course
            System.out.println("Please enter the new director name:");
            String inputName = getStringInput();
            display(ManagementSystem.getCourses(inputName));

            // get new course name to transfer
            System.out.println("Please enter the course that you wish transfer to this director: ");
            String newCourse = getStringInput();
            ManagementSystem.updateCourse(inputName, newCourse);

            // TODO check course is a real course that already exists

            // display updated
            System.out.println(inputName);
            display(ManagementSystem.getCourses(inputName));


        } else if (selectedString.equals(viewCourses)) {
            // view courses
            System.out.println("All Courses:"); 
            display(ManagementSystem.getCourses());

        } else if (selectedString.equals(updateTrainingRecords)) {
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


        } else if (selectedString.equals(addTeacher)) {
            System.out.println("Please enter the name of the teacher that you would like to add:");
            String newTeacherName = getStringInput();
            ManagementSystem.addTeacher(newTeacherName);


        } else if (selectedString.equals(addCourse)) {
            System.out.println("Please enter the name of the course that you would like to add:");
            String newCourseName = getStringInput();

            System.out.println("Please enter the name director for this course:");
            // TODO check direcotor exists
            String directorName = getStringInput();
            ManagementSystem.addCourse(directorName, newCourseName);

            System.out.println("New course: " + newCourseName + " directed by " + directorName);

        } else if (selectedString.equals(exitSystem)) {
            // exit the system.
            systemActive = false;
            System.out.println("Exiting System.");
            ManagementSystem.exit();
        }
    }

    public static void doCourseDirectorAction(int selectedNumber) {
        String selectedString = commands.get(selectedNumber - 1);
        System.out.println("\n" + selectedString + ":");

        if (selectedString.equals(viewDirectorCourses)) {
            // view my courses
            System.out.println("Courses Directed by " + name + ":"); // might not need this line
            display(ManagementSystem.getCourses(name));

        } else if (selectedString.equals(createTeachingRequirement)) {
            // create new teaching requirements
            // TODO not sure how to do this bit yet

        } else if (selectedString.equals(viewTeachingRequirement)) {
            // TODO 

        }else if (selectedString.equals(viewTeachingRequests)) {
            // TODO 

        }else if (selectedString.equals(updateTeachingRequest)) {
            // create new teaching request
            // TODO not sure how to do this bit yet

        } else if (selectedString.equals(exitSystem)) {
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
 