import ui.MainUI;
import backend.*;

/**
 * Main is the starting class that will launch that whole project.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class Main {
    /**
     * The main method that will launch the project.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean prepopulated = false;
        if (args.length > 0) {
            if (args[0].equals("prepopulated")) {
                prepopulated = true;
            }
        }
        
        new MainUI(new CustomersList(prepopulated), new Goods(prepopulated), new SuppliersList(prepopulated));
    }
}