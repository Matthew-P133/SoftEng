package teachingManagementSystem;

import java.io.Serializable;

/**
 * The StaffList class that stores staff objects.
 * It also deals with getting out list of all teachers or a list of teacher filtered by name.
 *
 * @author Lin Yun Jen
 *
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class StaffList implements Serializable {
    private List<Staff> staffList = new LinkedList<>();
    private List<Teacher> teacherList = new LinkedList<>();
    private List<Staff> teacherTraingList = new LinkedList<>();

    // Constructor
    public StaffList(List<Staff> staffList){
        this.staffList = staffList;
    }
    
    public StaffList(){
    }
    
    public void addTeacher(String name) {
    	Teacher teacher = new Teacher(name);
    	addStaffMember(teacher);
    	teacherList.add(teacher);
    }

    // add staff member
    private void addStaffMember(Staff s){
        staffList.add(s);
    }

    // get staff list
    public List<Staff> getStaffList(){
        return this.staffList;
    }

    // get teachers
    public List<Teacher> getTeachers(){
    	
    	List<Teacher> tempTeachers = new LinkedList<Teacher>();
    	
        for(int i=0; i<staffList.size(); i++){
            if(staffList.get(i) instanceof Teacher){
                Teacher teacher = (Teacher)staffList.get(i);
                tempTeachers.add(teacher);
            }
        }
        return tempTeachers;
    }

    // get teachers (name)
    public List<Teacher> getTeachers(String name){
    	 List<Teacher> tempTeachers = new LinkedList<Teacher>();
        for(int i=0; i<teacherList.size(); i++){
            if (teacherList.get(i).getName().equals(name)){
                tempTeachers.add(teacherList.get(i));
            }
        }
        return tempTeachers;
    }
    
    
    // get teachers by training
    public List<Teacher> getTeachersByTraining(Set<String> request){
    	 List<Teacher> tempTeachers = new LinkedList<Teacher>();
        for(int i=0; i<teacherList.size(); i++){
            Teacher teacher = teacherList.get(i);
            if(teacher.getTrainingStatus().contains(request)){ // TODO check training match
                tempTeachers.add(teacherList.get(i));
            }
        }
        return tempTeachers;
    }

    // toString
    @Override
    public String toString(){
        String staffListOutput = "";
        for(int i=0;i<staffList.size();i++){
            staffListOutput += staffList.get(i).getName() + ", ";
        }
        return staffListOutput; 
    }

	public List<Teacher> getTeachers(Set<String> requirements) {
		// TODO Auto-generated method stub
		return null;
	}
}
