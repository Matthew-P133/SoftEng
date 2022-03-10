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

/**
* Stores a list of all staff (teachers and directors) currently contained in the PTT management system.
* Contains methods to return either a specific teacher or director, or all teachers, or all directors.
* 
* @author Lin Yun Jen
*/
public class StaffList implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Staff> staffList = new LinkedList<>();
    
	// default constructor (used for instantiating in the case that an empty or unreadable file was provided)
    public StaffList(){}  
   
    // setters
    
    public void addStaffMember(Staff staff){
        staffList.add(staff);
    }

    // getters
    
    public List<Staff> getStaffList(){
        return this.staffList;
    }
   
    public List<Director> getDirectors(){
    	
    	List<Director> tempDirectors = new LinkedList<Director>();
    	
        for(int i=0; i<staffList.size(); i++){
            if(staffList.get(i) instanceof Director){
                Director director = (Director) staffList.get(i);
                tempDirectors.add(director);
            }
        }
        return tempDirectors;
    }

	public Director getDirector(String directorName) {
		
		Director director = null;

		for (Staff staff : staffList) {
			if (staff instanceof Director) {
				if (staff.getName().equals(directorName)) {
					director = (Director) staff;
					break;
				}
			}
		}
		return director;
    }
  
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

    public Teacher getTeacher(String teacherName) {
		
		Teacher teacher = null;

		for (Staff staff : staffList) {
			if (staff instanceof Teacher) {
				if (staff.getName().equals(teacherName)) {
					teacher = (Teacher) staff;
					break;
				}
			}
		}
		return teacher;
    }

    public String toString(){
        String staffListOutput = "";
        for(int i=0;i<staffList.size();i++){
            staffListOutput += staffList.get(i).getName() + ", ";
        }
        return staffListOutput; 
    }
}
