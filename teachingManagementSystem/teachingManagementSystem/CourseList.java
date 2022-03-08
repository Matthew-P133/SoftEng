package teachingManagementSystem;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CourseList implements Serializable {
    private static List<String> teachingRequests = new LinkedList<>();
    private List<Course> courses = new LinkedList<>();

    //Constructor that builds a new list from a passed in list.
    public CourseList(List<Course> courses){
        this.courses = courses;
    }
    
    public CourseList(){ 
    }

    /**
     * Filter a list of courses by staff ID.
     * @param ID the ID of the staff member.
     * @return CourseList : list with all courses associated with the passed ID.
     */
    public CourseList filterByStaffID(int ID){
        return new CourseList(courses.parallelStream()
                    .filter(course -> (course.getDirector().getID() == ID || course.getTeacher().getID() == ID))
                    .collect(Collectors.toList()));
    }

    /**
     * Filters the list by course name.
     * If it finds the course with the specified name, it will return that course.
     * Otherwise, if no courses with this name are in the list, it will return null.
     * The method assumes that every course has a unique name.
     * @param courseName String - The name of the course to find.
     * @return Course - Returns Course object if exists within list. Returns null if does not exist.
     */
    public Course filterByCourseName(String courseName){
        return courses.parallelStream()
                .filter(course -> course.equals(new Course(courseName, null, null)))
                .findFirst().orElse(null);
    }

    @Override
    public String toString(){
        StringBuilder returnString = new StringBuilder();
        courses.forEach(course -> returnString.append(course.toString()));
        return returnString.toString();
    }

    public void addCourse(Course course){
        courses.add(course);
    } 

    public static List<String> getTeachingRequests() {
        return teachingRequests;
    }

    public static void addTeachingRequest(String teachingRequest) {
        CourseList.teachingRequests.add(teachingRequest);
    }

    public List<Course> getCourseList() {
        return courses;
    }

}
