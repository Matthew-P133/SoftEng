package teachingManagementSystem;

import java.util.Set;

/**
 * The Teacher class inherits the Staff class. 
 * This class deals with the training data of teachers.
 * @author Lin Yun Jen
 *
 */

public class Teacher extends Staff{
    // A set to store a teacher's training
    private Set<String> trainingStatus;

    // Contructor
    public Teacher(String name, String id){
        super(name, id);
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