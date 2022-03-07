import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class StaffList {
    private List<Staff> staffList = new LinkedList<>();
    private List<Staff> teacherList = new LinkedList<>();
    private List<Staff> teacherNameList = new LinkedList<>();
    private List<Staff> teacherTraingList = new LinkedList<>();

    // Constructor
    public StaffList(List<Staff> staffList){
        this.staffList = staffList;
    }

    // add staff member
    public void addStaffMember(Staff s){
        staffList.add(s);
    }

    // get teachers
    public List<Staff> getTeachers(){
        return this.staffList;
    }

    // get teachers (name)
    public List<Staff> getTeachers(String name){
        for(int i=0; i<staffList.size(); i++){
            if (staffList.get(i).getName().equals(name)){
                teacherNameList.add(staffList.get(i));
            }
        }
        return teacherNameList;
    }
    
    // get teachers by training
    public List<Staff> getTeachersByTraining(Set<String> request){
        for(int i=0; i<staffList.size(); i++){
            if(staffList.get(i) instanceof Teacher){
                Teacher teacher = (Teacher)staffList.get(i);
                if(teacher.getTrainingStatus().contains(request)){
                    teacherTraingList.add(staffList.get(i));
                }
                teacherList.add(teacher);
            }
        }
        return teacherTraingList;
    }

    // toString
    public String toString(){
        String staffListOutput = "";
        for(int i=0;i<staffList.size();i++){
            staffListOutput += staffList.get(i).getName() + ", ";
        }
        return staffListOutput; 
    }
}
