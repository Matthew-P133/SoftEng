package teachingManagementSystem;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The Teacher class extends the Staff class. This class deals with information relating specifically
 * to staff who are teachers, namely their training.
 * @author Lin Yun Jen
 *
 */
public class Teacher extends Staff implements Serializable{

	private static final long serialVersionUID = 1L;
    private Set<String> trainingStatus; 

    public Teacher(String name){
        super(name);
        trainingStatus = new HashSet<String>();
    }

    // setters
    public void addTraining(String training){
        trainingStatus.add(training);
    }

    public void removeTraining(String training){
        trainingStatus.remove(training);
    }

    // getters
    public Set<String> getTraining(){
        return trainingStatus;
    }
    
    public String getFormattedTraining() {
    	String output = "";
    	for (String string : trainingStatus) {
    		output += string + ", ";
    	}
		return output;
    }

	public boolean trainedIn(String skill) {
		if (trainingStatus.contains(skill)) {
			return true;
		}
		return false;
	}
}
