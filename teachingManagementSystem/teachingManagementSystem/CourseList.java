package teachingManagementSystem;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CourseList implements Serializable {
   
    private List<Course> courses = new LinkedList<>();
    
    public CourseList() {     	
    }

    @Override
    public String toString(){
        StringBuilder returnString = new StringBuilder();
        courses.forEach(course -> returnString.append(course.toString()));
        return returnString.toString();
    }

    public void addCourse(Course course) {
        courses.add(course);
    } 

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
}
