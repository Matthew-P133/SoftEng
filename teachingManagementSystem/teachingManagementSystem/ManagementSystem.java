package teachingManagementSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JFileChooser;

/**
 * Represents the core of the PTT teacher management system. Responsible for reading from and writing to file,
 * and acts as controller for the UI.
 * 
 * @author Matthew
 *
 */
public class ManagementSystem { 

	private static File file; //= new File(System.getProperty("user.dir") + File.separator + "teachingManagementSystem" + File.separator + "PTT_system_data");
	private static CourseList courses;
	private static StaffList staff;
	
	
	public static String start() {
		JFileChooser jfc = new JFileChooser();
		int returnValue = jfc.showOpenDialog(null);
		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			file = jfc.getSelectedFile();
		}
		
		boolean status = readFromFile(file);
		if (!status) {
			return "...empty, new or unreadable file - starting with fresh data structures";
		} else {
			return "...file loaded successfully";
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
	
	// staff
	
	public static List<Staff> queryStaff() {
		return staff.getStaffList();
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
	
	public static boolean validDirectorName(String directorName) {
		if (getDirector(directorName) == null) {
			return false;
		} 
		return true;
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
				return "Requirement successfully removed";
			} else {
				return "Requirement successfully added";
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
}
