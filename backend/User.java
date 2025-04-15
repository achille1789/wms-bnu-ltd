/**
 * A class to create User objects.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
class User extends Entity implements IUser {
    
    public void add(UserData user) {
        entityInfo.put("name", user.getName());
        entityInfo.put("surname", user.getSurname());
        entityInfo.put("email", user.getEmail());
        entityInfo.put("address", user.getAddress());
        entityInfo.put("creditCard", user.getCreditCard());
        System.out.println("User info: " + entityInfo);
    }
    
    public void addNewOrder() {
        System.out.println("new order placed");
    }
}
