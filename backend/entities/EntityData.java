package entities;

 /**
  * A class to specify needed data for a generic Entity.
  *
  * @author Vanni Gallo
  * @version 1.0.0
  */
abstract class EntityData {
    // The fields.
    protected String name;
    protected String email;
    protected String address;
    
    // TODO: implement input validity checks
    protected EntityData(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }
    
    /**
     * Getters
     */
    public String getName() {
        return this.name;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getAddress() {
        return this.address;
    }
}
