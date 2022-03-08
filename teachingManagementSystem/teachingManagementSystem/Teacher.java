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
    private Set<String> trainingStatus = new HashSet<String>();

    // Contructor
    public Teacher(String name){
        super(name);
    }

    // add and remove
    public void addTraining(String request){
        trainingStatus.add(request);
    }

    public void removeTraining(String request){
        trainingStatus.remove(request);
    }

    // getters
    public Set<String> getTrainingStatus(){
        return trainingStatus;
    }

}
