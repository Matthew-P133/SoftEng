package teachingManagementSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Set;

/**
 * Represents the core of the PTT teacher management system. Responsible for reading from and writing to file,
 * and acts as controller for the UI.
 * 
 * @author Matthew
 *
 */
public class managementSystem {

	private static String username = "admin";
	private static final File file = new File("PTT_system_data");
	private static CourseList courses;
	private static StaffList staffList;
	private static StaffList staff;
	
	
	public static void start() {
		boolean status = readFromFile(file);
		if (!status) {
			System.err.println("Could not read file, setting up new data structures.");
		}
	}
	
	
	
	public static CourseList getCourses(String courseName) {
		
		CourseList filteredCourses = null;
		
		// TODO
		
		return filteredCourses;
	}
	
	public static CourseList getCourses() {
		
		CourseList courses = null;
		
		// TODO
		
		return courses;
	}
	
	
	public static StaffList getTeachers(String teacherName) {
		
		StaffList filteredTeachers = staffList.getTeachers(teacherName);
		return filteredTeachers;
	}
	
	
	public StaffList getTeachers() {
		
		StaffList filteredTeachers = staffList.getTeachers();
		return filteredTeachers;
	}
	
	
	public StaffList getTeachers(Set<String> requirements) {
		
		StaffList filteredTeachers = staffList.getTeachers(requirements);
		return filteredTeachers;
	}
	
	
	public static boolean addTraining(String teacherName, String requirement) {
		
		Teacher teacher = (Teacher) getTeachers(teacherName).getStaffList().get(0); 
		
		if (teacher == null) {
			return false;
		}
		teacher.addTraining(requirement);
		return true;
	}
	
	
	public static boolean removeTraining(String teacherName, String requirement) {
		
		Teacher teacher = (Teacher) getTeachers(teacherName).getStaffList().get(0); 
		
		if (teacher == null) {
			return false;
		}
		teacher.removeTraining(requirement);
		return true;
	}
	
	
	public static boolean addTeachingRequirements(String courseName, Set<String> requirements) {
		
		Course course = getCourses(courseName).getCourseList().get(0);
		
		if (course == null) {
			return false;
		}
		course.addTeachingRequirements(requirements); 
		return true;
	}
	
	
	public boolean removeTeachingRequirements(String courseName, Set<String> requirements) {
		
		Course course = getCourses(courseName).getCourseList().get(0);
		
		if (course == null) {
			return false;
		}
		course.removeTeachingRequirements(requirements);
		return true;
	}
	
	
	public static boolean makeTeachingRequest(String courseName) {
		
		Course course = getCourses(courseName).getCourseList().get(0);
		if (course == null) {
			return false;
		}
		CourseList.addTeachingRequest(courseName);
		return false;
		
	}
	
	
	/**
	 * Read in data from a specified file, or if an error occurs create fresh course and staff lists
	 * 
	 * @param file - the file to be read from
	 * @return boolean - indicates status of read operation
	 */
	private static boolean readFromFile(File file) {
		
		boolean status = false;
		try(
			FileInputStream fileInputStream = new FileInputStream(file);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		) {
			courses = (CourseList) objectInputStream.readObject();
			staff = (StaffList) objectInputStream.readObject();
			status = true;
		} catch (IOException e) {
			courses = new CourseList();
			staffList = new StaffList();
			status = false;
		} finally {
			return status;
		} 
		
		
	}
	
	
	/**
	 * Writes data out to a specified file
	 * 
	 * @param file - the file to be write to
	 * @return boolean - indicates status of write operation
	 */
	private static boolean writeToFile(File file) {
		
		boolean status = false;
		try(
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
		) {
			objectOutputStream.writeObject(this.courses);
			objectOutputStream.writeObject(this.staff);
			status = true;
		} catch (IOException e) {
			status = false;
		} finally {
			return status;
		}
	}


	public static StaffList getDirectors() {
		// TODO 
		return null;
	}


	public static Object getTrainingRecords(String searchName) {
		// TODO
		return null;
	}
	
	public static void exit() {
		writeToFile(file);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
