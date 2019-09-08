
package p2;

import UIs.MainPage;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    static int pto = 9000;
    static String host = "localhost";
    //static String path = "C:\\Users\\Rafael Barajas\\Desktop\\";
    //static String path = "C:\\Users\\alumno\\Desktop\\";
    static String path = "C:\\Users\\chistopher\\Desktop\\";
    
    public static void main(String[] args) {
        
        ArrayList<Product> menu = new ArrayList<Product>();
        Order order;
        
        try{
            Socket cl = new Socket(host, pto);
            System.out.println("Entrando al restaurante...");
            
            ObjectInputStream ois = new ObjectInputStream(cl.getInputStream());
            
            System.out.println("Recibiendo carta");
            menu = (ArrayList<Product>)ois.readObject();
            order= new Order();
            
            MainPage r = new MainPage(cl, menu, order);
            

            
            //oos.close();
            ois.close();

            
        } catch(Exception e){
            
        }
    }
    
}
