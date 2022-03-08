package teachingManagementSystem;

/**
 * The staff class.
 *
 * @author Lin Yun Jen
 *
 */

public class Staff {
    // Attributes
    String name;
    int ID;

    // Contructor
    public Staff(String name, int id){
        this.name = name;
        this.ID = id;
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
