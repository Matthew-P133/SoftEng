package teachingManagementSystem;

/**
 * The staff class.
 *
 * @author Lin Yun Jen
 *
 */

public class Staff {
	static int nextID = 1
	
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
        return this.name;
    }

}
