package teachingManagementSystem;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
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
        teachers = new LinkedList<>();
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

    public boolean updateTeachingRequirements(String requirement) {
    	if (teachingRequirements.contains(requirement)) {
    		teachingRequirements.remove(requirement);
    		return false;
    	} else {
    		teachingRequirements.add(requirement);
    		return true;
    	}

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

    public boolean addTeacher(Teacher teacher) {
    	
    	if (canBeTaughtBy(teacher)) {
    		teachers.add(teacher);
    		return true;
    	} else {
    		return false;
    	}
    	
      
    }
    
   

	public void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
        
        // TODO 
        
        // check if teachers empty - if so create teaching request
        
    }
	
	private boolean canBeTaughtBy(Teacher teacher) {
		// compare teacher training with course requirements
		Set<String> teacherTraining = teacher.getTraining();
		return teacherTraining.containsAll(teachingRequirements);
	}

    @Override
    public String toString(){
    	
    	// TODO tidy up
    	String teacherString = "";
    	
    	for (Teacher t : teachers) {
    		teacherString += t.toString();
    	}
    	
        return "Course Name: %s%n Teaching Requirements: %s%n Director: %s%n%n"
                .formatted(name, String.join(",", teachingRequirements),
                        (director.getName()+ " " + director.getID())
                        ) + teacherString;
    }

	public void removeTeachingRequirements(Set<String> requirements) {
		// TODO
		
	}

	public boolean hasTeacher(Teacher teacher) {
		return teachers.contains(teacher);
		
		
	}
}
