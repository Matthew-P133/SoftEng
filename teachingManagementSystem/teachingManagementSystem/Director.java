package teachingManagementSystem;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * The Director class extends the Staff class. This class keeps track of the courses a 
 * director is currently in charge of, as well as allowing for courses to be added or removed from this director.
 * 
 * @author Lin Yun Jen
 *
 */
public class Director extends Staff implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Course> courseList;
 
    public Director(String name){
       super(name);
       courseList = new LinkedList<Course>();
    }
    
    // setters

	public void addCourse(Course course) {
		courseList.add(course);
		
	}

	// getters
	
	public void removeCourse(Course course) {
		courseList.remove(course);
	}
	
	public List<Course> getCourses() {
		return courseList;
	}

	public String getFormattedCourses() {
		String output = "";
		for (Course course : courseList) {
			output += course.toString() + " ";
		}
		return output;
	}

	public boolean hasCourse(String courseName) {
		for (Course course : courseList) {
			if (course.getCourseName().equals(courseName)) {
				return true;
			}
		}
		return false;
	}
}
