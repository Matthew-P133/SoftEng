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
    private List<Staff> teacherList = new LinkedList<>();
    private List<Staff> teacherNameList = new LinkedList<>();
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
    public StaffList getTeachers(){
        for(int i=0; i<staffList.size(); i++){
            if(staffList.get(i) instanceof Teacher){
                Teacher teacher = (Teacher)staffList.get(i);
                teacherList.add(teacher);
            }
        }
        return new StaffList(teacherList);
    }

    // get teachers (name)
    public StaffList getTeachers(String name){
        teacherList = getTeachers().getStaffList();
        for(int i=0; i<teacherList.size(); i++){
            if (teacherList.get(i).getName().equals(name)){
                teacherNameList.add(teacherList.get(i));
            }
        }
        return new StaffList(teacherList);
    }
    
    
    // get teachers by training
    public List<Staff> getTeachersByTraining(Set<String> request){
        teacherList = getTeachers().getStaffList();
        for(int i=0; i<teacherList.size(); i++){
            Teacher teacher = (Teacher)teacherList.get(i);
            if(teacher.getTrainingStatus().contains(request)){
                teacherTraingList.add(teacherList.get(i));
            }
        }
        return teacherTraingList;
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

	public StaffList getTeachers(Set<String> requirements) {
		// TODO Auto-generated method stub
		return null;
	}
}
