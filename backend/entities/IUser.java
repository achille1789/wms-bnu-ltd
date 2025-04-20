package entities;

 /**
  * An interface to implement add() method for User.
  *
  * @author Vanni Gallo
  * @version 1.0.0
  */
 interface IUser {
    /**
     * Add a new entity User.
     */
    public User add(UserData user);
    
    /**
     * User's getters.
     */
     public String getSurname();
 }
