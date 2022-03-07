public class Staff {
    // Attributes
    String name;
    String ID;

    // Contructor
    public Staff(String name, String id){
        this.name = name;
        this.id = id;
    }

    // Getters
    public String getName(){
        return this.name;
    }

    public String getID(){
        return this.ID;
    }

    // toString
    public String toString(){
        return this.name;
    }

    
}
