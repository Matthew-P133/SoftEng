package teachingManagementSystem;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
* Stores a list of all courses currently contained in the PTT management system, and
* allows courses to be added to or deleted from the system.
* 
* @author Val
*/
public class CourseList implements Serializable {
   
	private static final long serialVersionUID = 1L;
	private List<Course> courses = new LinkedList<>();
    
	// default constructor (used for instantiating in the case that an empty or unreadable file was provided)
    public CourseList() {}

    // setters
    
    public void addCourse(Course course) {
        courses.add(course);
    } 

    // getters
    
    public List<Course> getCourses() {
    	List<Course> tempCourses = new LinkedList<Course>();
    	
    	for (Course c : courses) { 
    		tempCourses.add(c);
    	}
    	
        return tempCourses;
    }

	public Course getCourse(String courseName) {
		
		Course course = null;
		
		for (Course c : courses) {
			if (c.getCourseName().equals(courseName)) {
				course = c;
				break;
			}
		}
		return course;
	}
	
	public String toString(){
        StringBuilder returnString = new StringBuilder();
        courses.forEach(course -> returnString.append(course.toString()));
        return returnString.toString();
    }

}
