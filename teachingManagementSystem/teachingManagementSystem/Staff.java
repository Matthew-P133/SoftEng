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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static int nextID = 1;
	
    // Attributes
    String name; 
    int ID;

    // Contructor
    public Staff(String name){
        this.name = name;
        this.ID = nextID;
        nextID++;
    }

    // Getters
    public String getName(){
        return this.name;
    }

    public int getID(){
        return this.ID;
    }

    // toString
    public String toString(){
        return this.name + ", ";
    }

}
