import java.util.Set;

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
