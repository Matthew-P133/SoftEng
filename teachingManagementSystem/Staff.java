public class Staff {
    // Attributes
    String name;
    String id;

    // Contructor
    public Staff(String name, String id){
        this.name = name;
        this.id = id;
    }

    // Getters
    public String getName(){
        return this.name;
    }

    public String getId(){
        return this.id;
    }

    // toString
    public String toString(){
        return this.name;
    }

    
}
