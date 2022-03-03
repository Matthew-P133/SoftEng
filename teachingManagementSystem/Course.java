import java.util.HashSet;
import java.util.Set;

public class Course {
    //TODO TRAINING_OPTIONS needs to be filled in.
    private static final String[] TRAINING_OPTIONS = {}; //contains the list of all available training options.
    private Set<String> teachingRequirements;
    private String name;
    private Staff director;
    private Staff teacher;

    public Course(String name, Staff director, Staff teacher){
        this.name = name;
        this.director = director;
        this.teacher = teacher;
        teachingRequirements = new HashSet<>();
    }

    public Set<String> getTeachingRequirements() {
        return teachingRequirements;
    }

    public void addTeachingRequirements(String requirement) {
        teachingRequirements.add(requirement);
    }

    public String getCourseName() {
        return name;
    }

    public void setCourseName(String name) {
        this.name = name;
    }

    public Staff getDirector() {
        return director;
    }

    public void setDirector(Staff director) {
        this.director = director;
    }

    public Staff getTeacher() {
        return teacher;
    }

    public void setTeacher(Staff teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString(){
        return "Course Name: %s%n Teaching Requirements: %s%n Teacher: %s%n Director: %s%n%n"
                .formatted(name, String.join(",", teachingRequirements),
                        (director.getName()+ " " + director.getID()),
                        (teacher.getName() + " " + teacher.getID()));
    }
}
