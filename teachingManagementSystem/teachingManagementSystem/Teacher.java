package teachingManagementSystem;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The Teacher class inherits the Staff class. 
 * This class deals with the training data of teachers.
 * @author Lin Yun Jen
 *
 */

public class Teacher extends Staff implements Serializable{
    // A set to store a teacher's training
    private Set<String> trainingStatus; 

    // Constructor
    public Teacher(String name){
        super(name);
        trainingStatus = new HashSet<String>();
    }

    // add and remove training
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
