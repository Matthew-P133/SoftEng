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
	private static final File file = new File(System.getProperty("user.dir") + File.separator + "teachingManagementSystem" + File.separator + "PTT_system_data");
	private static CourseList courses;
	private static StaffList staff;
	
	
	public static String start() {
		boolean status = readFromFile(file);
		if (!status) {
			return "Could not read file, setting up new data structures";
		} else {
			return "Successfully read from file";
		}
	}
	
	public static String exit() {
		boolean status = writeToFile(file);
		if (status) {
			return "Successfully wrote data to file";
		}
		else {
			return "Error writing to file";
		}
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
			objectOutputStream.writeObject(courses);
			objectOutputStream.writeObject(staff);
			status = true;
		} catch (IOException e) {
			status = false;
		} finally {
			return status;
		}
	}

	// internal helper methods
	
	private static Course getCourse(String name) {
		Course course = null;
		course = courses.getCourse(name);
		return course;
	}
	
	private static Director getDirector(String name) {
		Director director = null;
		director = staff.getDirector(name);
		return director;
	}
	
	private static Director getCourseDirector(String name) {
		Course course = getCourse(name);
		Director director = course.getDirector();
		return director;
	}
	
	private static Teacher getTeacher(String name) {
		Teacher teacher = null;
		teacher = staff.getTeacher(name);
		return teacher;
	}
	
	// teachers
	
	public static String enterTeacher(String name) {
		if (getTeacher(name) != null) {
			return "Unsuccessful, teacher already exists";
		} else {
			Teacher teacher = new Teacher(name);
			staff.addStaffMember(teacher);
			return "Teacher added to system";
		}
	}
	
	public static List<Teacher> queryTeachers() {
		return staff.getTeachers();
	}
	
	public static String queryTeacherTraining(String name) {
		Teacher teacher = getTeacher(name);
		if (teacher != null) {
			return teacher.getFormattedTraining();
		} else {
			return "Teacher does not exist";
		}
	}
	
	public static String updateTraining(String teacherName, String skill) {
		
		Teacher teacher = getTeacher(teacherName);
		
		if (teacher != null) {
			if (teacher.trainedIn(skill)) {
				teacher.removeTraining(skill);
				return "Successfully removed training record";
			} else {
				teacher.addTraining(skill);
				return "Successfully added training record";
			}
		}
		return "Unsuccessful, teacher does not exist";
	}
	
	// directors
	
	public static String enterDirector(String name) {
		
		if (getDirector(name) != null) {
			return "Unsuccessful, director already exists";
		} else {
			Director director = new Director(name);
			staff.addStaffMember(director);
			return "Director added to system";
		}
	}
	
	public static List<Director> queryDirectors() {
		return staff.getDirectors();
	}
	
	public static String queryDirectorCourses(String directorName) {
		Director director = getDirector(directorName);
		
		if (director != null) {
			return director.getFormattedCourses();
		} else {
			return "Unsuccessful, director does not exist";
		}
	}
	
	// courses
	
	public static String enterCourse(String courseName, String directorName) {
		Director director = getDirector(directorName);
		
		if (director != null) {
			if (getCourse(courseName) == null) {
				Course course = new Course(courseName, director);
				courses.addCourse(course);
				director.addCourse(course);
				return "Successfully added course";
			} else {
				return "Unsuccesful, course already exists";
			}
		} else {
			return "Unsuccessful, director does not exist";
		}
	}
	
	public static List<Course> queryCourses() {
		return courses.getCourses();
	}
	
	public static Course queryCourse(String courseName) {
		Course course = getCourse(courseName);
		return course;
	}
	
	public static String updateRequirement(String courseName, String requirement) {
		Course course = getCourse(courseName);
		if (course == null) {
			return "Unsuccessful, course does not exist";
		} else {
			if (!course.updateTeachingRequirements(requirement)) {
				return "Requirement removed";
			} else {
				return "Requirement added";
			}
		}
	}
	
	public static boolean directorHasCourse(String directorName, String courseName) {
		Director director = getDirector(directorName);
		return director.hasCourse(courseName);
	}
	
	public static String transferCourse(String newDirectorName, String courseName) {

		Course c = getCourse(courseName);
		
		if (c != null) {
			Director newDirector = getDirector(newDirectorName);
			if (newDirector != null) {
				c.setDirector(newDirector);
				return "Successfully transfered course to new director";
			}
			else {
				return "Unsuccessful, new director does not exist";
			}
		}
		else {
			return "Unsuccessful, course does not exist";
		}
	}
	
	public static String updateTeacher(String courseName, String teacherName) {
		Course course = getCourse(courseName);
		
		if (course != null) {
			Teacher teacher = getTeacher(teacherName);
			if (teacher != null) {
				if (course.hasTeacher(teacher)) {
					course.removeTeacher(teacher);
					return "Successfully removed teacher from course";
				} else {
					if (course.addTeacher(teacher)) {
						return "Successfully added teacher to course";
					} else {
						return "Unsuccessful, teacher not qualified to teach course";
					}
				}
			}
			return "Unsuccessful, teacher does not exist";
		}
		return "Unsuccessful, course does not exist";
	}
	
	public static String queryTeachingRequests() {
		
		String output = "";
		
		List<Course> allCourses = courses.getCourses();
		
		for (Course course : allCourses) {
			if (course.hasOpenTeachingRequest()) {
				output += course.getFormattedTeachingRequests();
			}
		}
		return output;
	}
	
	public static String queryTeachingRequests(String directorName) {

		Director director = getDirector(directorName);

		if (director != null) {

			String output = "";

			List<Course> allCourses = courses.getCourses();

			for (Course course : allCourses) {
				if (course.hasOpenTeachingRequest() && course.getDirector().equals(director)) {
					output += course.getFormattedTeachingRequests();
				}
			}
			return output;
		} else {
			return "Unsuccessful";
		}
	}
	
	public static String enterTeachingRequest(String courseName, int requests) {
		Course course = getCourse(courseName);
		
		if (course != null) {
			course.addTeachingRequests(requests);
			return "Successfully addedd teaching requests(s)";
		}
		return "Unsuccessful, course does not exist";
	}
	
	public static String deleteTeachingRequest(String courseName, int requests) {
		Course course = getCourse(courseName);
		
		if (course != null) {
			if(course.deleteTeachingRequests(requests)) {
				return "Successfully removed teaching requests(s)";
			} else {
				return "Unsuccessful, cannot delete that many requests";
			}
		}
		return "Unsuccessful, course does not exist";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	
//	public static List<Course> getCourses(String directorName) {
//		
//		List<Course> filteredCourses = null;
//		
//		
//		Director director = null;
//		try {
//			director = getDirectors(directorName).get(0);
//			filteredCourses = director.getCourses();			
//			
//		} catch (IndexOutOfBoundsException e) {
//			System.out.println("Sorry, can't find a director with that name.");
//			return null;
//		}
//
//		return filteredCourses;
//	}
	
//	public static List<Course> getCourses() {
//		
//		List<Course> filteredCourses = null;
//		
//		filteredCourses = courses.getCourses();
//		
//		return filteredCourses;
//	}
//	
//	public static void addTeacher(String name) {
//		staff.addTeacher(name);
//	}
//	
//	public static void addDirector(String name) {
//		staff.addDirector(name);
//	}

	
	
//	public static Teacher getTeacher(String teacherName) {
//		
//		List<Teacher> filteredTeachers = staff.getTeachers(teacherName);
//		return filteredTeachers;
//	}
	
	
//	public static List<Teacher> getTeachers() {
//		
//		List<Teacher> filteredTeachers = staff.getTeachers();
//		return filteredTeachers;
//	}
//	
//	
//	public static List<Teacher> getTeachers(Set<String> requirements) {
//		
//		List<Teacher> filteredTeachers = staff.getTeachers(requirements);
//		return filteredTeachers;
//	}
	
//	
//	public static boolean addTraining(String teacherName, String requirement) {
//		
//		// assumes only one teacher with each name
//		Teacher teacher = getTeachers(teacherName).get(0); 
//		
//		if (teacher == null) {
//			return false;
//		}
//		teacher.addTraining(requirement);
//		return true;
//	}
//	
//	public static Set<String> getTraining(String teacherName) {
//		Set<String> training = null;
//		Teacher teacher = null;
//		try {
//			teacher = getTeachers(teacherName).get(0);
//		} catch (IndexOutOfBoundsException e) {
//			System.out.println("Sorry, can't find a teach with that name.");
//			return null;
//		}
//		
//		training = teacher.getTrainingStatus();
//		return training;
//		
//	}
//	
	
//	public static boolean removeTraining(String teacherName, String requirement) {
//		
//		Teacher teacher = getTeachers(teacherName).get(0); 
//		
//		if (teacher == null) {
//			return false;
//		}
//		teacher.removeTraining(requirement);
//		return true;
//	}
//	
//	
//	public static boolean addTeachingRequirements(String courseName, Set<String> requirements) {
////		
//		//TODO
////		Course course = getCourses(courseName).getCourseList().get(0);
////		
////		if (course == null) {
////			return false;
////		}
////		course.addTeachingRequirements(requirements); 
//		return false;
//	}
	
	
//	public static boolean removeTeachingRequirements(String courseName, Set<String> requirements) {
////		
////		Course course = getCourses(courseName).getCourseList().get(0);
////		
////		if (course == null) {
////			return false;
////		}
////		course.removeTeachingRequirements(requirements);
//		return true;
//	}
//	
//	
//	public static boolean makeTeachingRequest(String courseName) {
		
		//TODO
//		Course course = getCourses(courseName).getCourseList().get(0);
//		if (course == null) {
//			return false;
//		}
//		CourseList.addTeachingRequest(courseName);
//		return false;
		
	//}
	
	
	


//	public static List<Director> getDirectors() {
//		return staff.getDirectors();
//	}
//	

//	public static List<Director> getDirectors(String directorName) {
//		List<Director> filteredDirectors = staff.getDirectors(directorName);
//		return filteredDirectors;
//		
//	}


//	public static Object getTrainingRecords(String searchName) {
//		// TODO
//		return null;
//	}
	




//	
//

//
//
//
//	public static boolean addCourse(String directorName, String courseName) {
//		
//		Director director = getDirectors(directorName).get(0);
//
//		
//
//		if (director == null) {
//			return false;
//		} else {
//			if (getCoursesByName(courseName) == null) {
//			
//			Course course = courses.addCourse(courseName, director);
//			director.addCourse(course);
//			} else {
//				Course course = getCoursesByName(courseName).get(0);
//				director.addCourse(course);
//			}
//			return true;
//			} 
//
//	}
//	
//
//	private static List<Course> getCoursesByName(String courseName) {
//
//		List<Course> filteredCourses = courses.getCourses(courseName);
//		return filteredCourses;
//
//	}
//
//
//
//	private static boolean removeCourse(String directorName, String courseName) {
//		
//		Director director = getDirectors(directorName).get(0);
//
//		
//
//		if (director == null) {
//			return false;
//		} else {
//			
//			director.removeCourse(courseName);
//			return true;
//			} 
//		
//	}
//	
//	
	
	
	
	
	
	
	
	
	
	
}
