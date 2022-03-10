package teachingManagementSystem;

import java.io.Serializable;

/**
 * The staff class. Stores basic information common to all
 * sub-types of staff.
 *
 * @author Lin Yun Jen
 *
 */

public class Staff implements Serializable{

	private static final long serialVersionUID = 1L;
    private String name; 
    private int ID;
    
    // controls the ID a new staff member is given on initialisation
	static int nextID = 1;

    public Staff(String name){
        this.name = name;
        this.ID = nextID;
        nextID++;
    }

    // getters
    public String getName(){
        return this.name;
    }

    public int getID(){
        return this.ID;
    }

    public String toString(){
        return this.name + ", ";
    }
}
