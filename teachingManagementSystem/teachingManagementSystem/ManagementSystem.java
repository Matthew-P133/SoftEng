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
public class ManagementSystem { 

	private static String username = "admin";
	private static final File file = new File("PTT_system_data");
	private static CourseList courses;
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
	
	public static void addTeacher(String name) {
		staff.addTeacher(name);
	}
	
	
	public static List<Teacher> getTeachers(String teacherName) {
		
		List<Teacher> filteredTeachers = staff.getTeachers(teacherName);
		return filteredTeachers;
	}
	
	
	public static List<Teacher> getTeachers() {
		
		List<Teacher> filteredTeachers = staff.getTeachers();
		return filteredTeachers;
	}
	
	
	public static List<Teacher> getTeachers(Set<String> requirements) {
		
		List<Teacher> filteredTeachers = staff.getTeachers(requirements);
		return filteredTeachers;
	}
	
	
	public static boolean addTraining(String teacherName, String requirement) {
		
		// assumes only one teacher with each name
		Teacher teacher = getTeachers(teacherName).get(0); 
		
		if (teacher == null) {
			return false;
		}
		teacher.addTraining(requirement);
		return true;
	}
	
	public static Set<String> getTraining(String teacherName) {
		Set<String> training = null;
		Teacher teacher = null;
		try {
			teacher = getTeachers(teacherName).get(0);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Sorry, can't find a teach with that name.");
			return null;
		}
		
		training = teacher.getTrainingStatus();
		return training;
		
	}
	
	
	public static boolean removeTraining(String teacherName, String requirement) {
		
		Teacher teacher = getTeachers(teacherName).get(0); 
		
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
	
	
	public static boolean removeTeachingRequirements(String courseName, Set<String> requirements) {
		
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
			e.printStackTrace();
			courses = new CourseList();
			staff = new StaffList();
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
			file.delete();
			objectOutputStream.writeObject(courses);
			objectOutputStream.writeObject(staff);
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
