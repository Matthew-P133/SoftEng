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
            commands.put(12, "View open teaching requests");
            commands.put(5, "Add a new teacher");
            commands.put(6, "Add a new course director");
            commands.put(7, "Add a new course");
            commands.put(8, "Update teachers for a course");
            commands.put(9, "Update the training records of a staff member");
            commands.put(10, "Transfer a course to a new director");
            commands.put(11, "Exit the system");
            
        // if user is course director
        } else if (role == 'c') {

            commands.put(1, "View my courses");
            commands.put(4, "Create new teaching requirements for a course"); 
            commands.put(3, "Create or delete a teaching request"); //TODO
            commands.put(2, "View all teaching requests"); 
            commands.put(5, "Exit the system");
            
            // TODO use switch statements
        }
    }

    public static void showHelp(char role) {
        // display commands list
        System.out.println("\nSystem options: ");
        for (int i : commands.keySet()) {
            System.out.println(i + ".\t" + commands.get(i));
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

        if (selectedNumber == 1) {
            // view teaching staff
            System.out.println("All Teaching Staff:");
            display(ManagementSystem.queryTeachers());

        }else if (selectedNumber == 2) {
            // view course directors
            System.out.println("All Course Directors:");
            display(ManagementSystem.queryDirectors());

        } else if (selectedNumber == 12) {
            // view open teaching requests
            display(ManagementSystem.queryTeachingRequests());

        }else if (selectedNumber == 3) {
            // view training records for a staff member
            System.out.println("Please enter the name of the person whose training records you would like to view:");
            String searchName = getStringInput();																	
            System.out.println("Training records for: " + searchName); 
            display(ManagementSystem.queryTeacherTraining(searchName));

        } else if (selectedNumber == 4) {
            // view courses
            System.out.println("All Courses:"); 
            display(ManagementSystem.queryCourses());

        } else if (selectedNumber == 5) {
            System.out.println("Please enter the name of the teacher that you would like to add:");
            String newTeacherName = getStringInput();
            ManagementSystem.enterTeacher(newTeacherName);


        } else if (selectedNumber == 6) {
            System.out.println("Please enter the name of the course director that you would like to add:");
            String newDirectorName = getStringInput();
            display(ManagementSystem.enterDirector(newDirectorName));

        } else if (selectedNumber == 7) {
            System.out.println("Please enter the name of the course that you would like to add:");
            String newCourseName = getStringInput();

            System.out.println("Please enter the name director for this course:");
            
            String directorName = getStringInput();
            display(ManagementSystem.enterCourse(newCourseName, directorName));


        } else if (selectedNumber == 8) {
            // update teachers for a course

            // get the course to add or remove teachers
            System.out.println("Please enter the course name to add or remove a teacher:");
            String courseName = getStringInput();

            // get the teacher to add or remove from that course
            System.out.println("Please enter the name of the teacher to add or remove:");
            String teacherName = getStringInput();
            
            // pass action to management system
            display(ManagementSystem.updateTeacher(courseName, teacherName));
            display(ManagementSystem.queryCourse(courseName));
          

        }else if (selectedNumber == 9) {
            // display the current training records
            System.out.println("Please enter the staff name to view training records:");
            String inputName = getStringInput();
            display(ManagementSystem.queryTeacherTraining(inputName));

            // add or remove training records
            System.out.println("Please enter the training records that you wish to add or delete: ");
            String newTraining = getStringInput();
            display(ManagementSystem.updateTraining(inputName, newTraining));		

            // display updated training records
            System.out.println("Updated training records for " + inputName + ": ");
            display(ManagementSystem.queryTeacherTraining(inputName));


        }  else if (selectedNumber == 10) {
            // transfer director for a course
            System.out.println("Please enter the new director name:");
            String newDirName = getStringInput();						
            display(ManagementSystem.queryDirectorCourses(newDirName));

            // get new course name to transfer
            System.out.println("Please enter the course that you wish transfer to this director: ");
            String courseName = getStringInput();
            display(ManagementSystem.transferCourse(newDirName, courseName));			

            // show confirmation
            System.out.println(courseName + " transferred to " + newDirName);
         
            // TODO show who to transfer from? or course?

        } else if (selectedNumber == 11) {
            // exit the system.
            systemActive = false;
            display(ManagementSystem.exit());
        }
    }

	public static void doCourseDirectorAction(int selectedNumber) {
		String selectedString = commands.get(selectedNumber);
		System.out.println("\n" + selectedString + ":");
		String courseName = "";

		switch (selectedNumber) {

		case 1:
			// view my courses
			System.out.println("Courses Directed by " + name + ":");
			display(ManagementSystem.queryDirectorCourses(name));
			break;
			
		case 2:
			// View my open teaching requests
			System.out.println("Open teaching requests for courses directed by " + name + ":");
			display(ManagementSystem.queryTeachingRequests(name));
			break;
			
		case 3:
			// show user the current open teaching requests
			System.out.println("Open teaching requests:");
			display(ManagementSystem.queryTeachingRequests(name));

			// Create or delete a teaching request
			System.out.println("\nWhich course would you like to create or remove a teaching request for?");
			courseName = getStringInput();

			// would they like to add or remove a teaching request for this course
			System.out.println("Enter 'a' to add a teaching request or 'r' to remove a teaching request:");
			char requestType = getCharInput();
			if (requestType == 'a' || requestType == 'A') {
				// we want to add a teaching request
				// how many requests do we want to add?
				System.out.println("Please enter the number of teaching request that you want to add for this course:");
				int count = getNumberInput();
				// pass action to management system
				display(ManagementSystem.enterTeachingRequest(courseName, count));
				// display updated list
				display(ManagementSystem.queryTeachingRequests(name));

			} else if (requestType == 'r' || requestType == 'R') {
				// we want to remove a teaching request
				// how many requests do we want to add?
				System.out.println(
						"Please enter the number of teaching request that you want to remove for this course:");
				int count = getNumberInput();
				// pass action to management system
				display(ManagementSystem.deleteTeachingRequest(courseName, count));
				// display updated list
				display(ManagementSystem.queryTeachingRequests(name));

			} else {
				System.out.println("Sorry, incorrect input please try again.");
			}
			break;

		case 4:
			// create new teaching requirements

			// ask for the course
			System.out.println("Which course would you like to create or remove requirements for?");
			courseName = getStringInput();

			// check if that course belongs to the director
			if (!ManagementSystem.directorHasCourse(name, courseName)) {
				System.out.println("Sorry this is not one of your courses so you cannot edit the requirements");
				return;
			} else {
				// show them current requirements for that course
				display(ManagementSystem.queryCourse(courseName));

				// add or remove requirements
				System.out.println("Which requirements would you like to add or remove?");
				String requirmentInput = getStringInput();
				ManagementSystem.updateRequirement(courseName, requirmentInput);
				display(ManagementSystem.queryCourse(courseName));
			}
			break;

		case 5:
			// exit the system.
			systemActive = false;
			System.out.println("Exiting System.");
			display(ManagementSystem.exit());
			break;
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
        display(ManagementSystem.start());

        getRole();
        while (systemActive) {
            showHelp(role);
        }
    } 
}
 