
package p2;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    static int pto = 9000;
    static String host = "localhost";
    static String path = "C:\\Users\\Rafael Barajas\\Desktop\\";
    //static String path = "C:\\Users\\alumno\\Desktop\\";
    
    public static void main(String[] args) {
        
        ArrayList<Product> menu = new ArrayList<Product>();
        
        try{
            Socket cl = new Socket(host, pto);
            System.out.println("Entrando al restaurante...");
            
            ObjectInputStream ois = new ObjectInputStream(cl.getInputStream());
            
            System.out.println("Recibiendo carta");
            menu = (ArrayList<Product>)ois.readObject();
            
            System.out.println("Mmmm... Quiero...");
            
            /*
            Hacer ciclo donde el cliente vaya seleccionando productos
            con su cantidad
            e irlo añadiendo a un objeto Order
            
            al cuando el cliente haya terminado de aregar productos
            mandarlo por un ObjectOutputStream oos
            
            recibir por ois el ticket.
            */
            
            //oos.close();
            ois.close();
            
            
        } catch(Exception e){
            
        }
    }
    
}
