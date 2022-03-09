package teachingManagementSystem;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Course implements Serializable {
    //TODO TRAINING_OPTIONS needs to be filled in.
    private static final String[] TRAINING_OPTIONS = {}; //contains the list of all available training options.
    private Set<String> teachingRequirements;
    private String name;
    private Director director;
    private List<Teacher> teachers;

    public Course(String name, Director director){
        this.name = name;
        this.director = director;
        teachingRequirements = new HashSet<>();
    }

    /**
     * Compares two courses based on name. If names equal, returns true. False, otherwise.
     * @param course Object : should be of type Course.
     * @return True if two courses share same name. False, otherwise.
     */
    @Override
    public boolean equals(Object course){
        if(course instanceof Course) {
            return name.equals(((Course) course).name);
        }
        return false;
    }

    public Set<String> getTeachingRequirements() {
        return teachingRequirements;
    }

    public void addTeachingRequirements(Set<String> requirement) {
        //teachingRequirements.add(requirement);
    	//TODO
    }

    public String getCourseName() {
        return name;
    }

    public void setCourseName(String name) {
        this.name = name;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
    	if (this.director != null) {
    		this.director.removeCourse(this);
    	}
        this.director = director;
        this.director.addCourse(this);
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    @Override
    public String toString(){
        return "Course Name: %s%n Teaching Requirements: %s%n Director: %s%n%n"
                .formatted(name, String.join(",", teachingRequirements),
                        (director.getName()+ " " + director.getID()));
                      //  (teacher.getName() + " " + teacher.getID()));
    }

	public void removeTeachingRequirements(Set<String> requirements) {
		// TODO
		
	}
}
