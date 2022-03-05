import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CourseList {
    private static List<String> teachingRequests = new LinkedList<>();
    private List<Course> courses = new LinkedList<>();

    //Constructor that builds a new list from a passed in list.
    public CourseList(List<Course> courses){
        this.courses = courses;
    }

    /**
     * Filter a list of courses by staff ID.
     * @param ID the ID of the staff member.
     * @return CourseList : list with all courses associated with the passed ID.
     */
    public List<Course> filterByStaffID(int ID){
        return new CourseList(courses.parallelStream()
                    .filter(course -> (course.getDirector().geID() == ID || course.getTeacher().getID() == ID))
                    .collect(Collectors.toList()));
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
