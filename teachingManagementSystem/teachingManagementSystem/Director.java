package teachingManagementSystem;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * The Teacher class inherits the Staff class. 
 * This class deals with the training data of teachers.
 * @author Lin Yun Jen
 *
 */

public class Director extends Staff implements Serializable {
    // A set to store a teacher's training 
	
	List<Course> courseList;
 
    // Constructor
    public Director(String name){
       super(name);
       courseList = new LinkedList<Course>();
    }

	public List<Course> getCourses() {
		return courseList;
	}

	public void addCourse(Course course) {
		courseList.add(course);
		
	}

	public void removeCourse(Course course) {
		courseList.remove(course);
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
