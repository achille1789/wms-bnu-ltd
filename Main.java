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
        // Create a new MainUI object to start the application.
        MainUI mainUI = new MainUI();
        Customers customers = new Customers();
        Suppliers suppliers = new Suppliers();
        Goods goods = new Goods();
    }
}