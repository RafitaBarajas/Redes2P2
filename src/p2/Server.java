
package p2;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    
    //static String path = "C:\\Users\\alumno\\Desktop\\Server\\";
    //static String path = "C:\\Users\\grafi_000\\Desktop\\Server\\";
    static String path ="C:\\Users\\chistopher\\Desktop\\Server\\";

    public static void main(String[] args) {
        
        try{
            int pto = 9000;
            ServerSocket s = new ServerSocket(pto);

            ArrayList<Product> menu = new ArrayList<Product>();
            ArrayList<Order> orders = new ArrayList<Order>();
            Ticket ticket;
            
            menu = createMenu(menu);
            
            System.out.println("Restaurante abierto, esperando clientes...");
            
            while(true){
                Socket cl = s.accept();
                System.out.println("Cliente entrando...");
                
                ObjectOutputStream oos = new ObjectOutputStream(cl.getOutputStream());
                DataInputStream dis = new DataInputStream(cl.getInputStream());
                
                System.out.println("Entregando carta...");
                oos.writeObject(menu);
                oos.flush();
                
                int option= dis.readInt();
                
                if( option == 1 ){
                    //Server receives an Order object, then sends a Ticket
                }
                else if( option == 0 ){
                    //Client disconected, receives a menu to update existance quantities
                }
                
                
                oos.close();
                //cerrar ois
            }
        
        } catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    public static ArrayList<Product> createMenu(ArrayList<Product> m){
        String name;
        float price;
        File img;
        int exists;
        int deal;
        String desc;
        String shortName;
        
        name = "Pizza de Peperoni";
        price = 79.00f;
        img = new File(path + "images\\pizzaPepe.jpg");
        exists = 74;
        deal = 10;
        desc = "<html><body>\"La mejor pizza del área metropolitana, cocinada por los dioses de las pizzas\"</body></html>";
        shortName="pizza";
        m.add(new Product(name, price, img, exists, deal, desc, shortName));
        
        name = "Hamburguesa de Pollo";
        price = 53.00f;
        img = new File(path + "images\\hamPollo.jpg");
        exists = 13;
        deal = 15;
        desc = "<html><body>\"La mejor hamburguesa del área metropolitana, cocinada por Bob Esponja\"</body></html>";
        shortName="hamburguesa";
        m.add(new Product(name, price, img, exists, deal, desc, shortName));
        
        name = "Papas a la Francesa";
        price = 30.00f;
        img = new File(path + "images\\papas.jpg");
        exists = 27;
        deal = 5;
        desc = "<html><body>\"Deliciosas papas, no puedes comer solo una\"</body></html>";
        shortName="papas";
        m.add(new Product(name, price, img, exists, deal, desc, shortName));
        
        name = "Hot Dog";
        price = 45.00f;
        img = new File(path + "images\\hotdog.jpg");
        exists = 16;
        deal = 0;
        desc = "<html><body>\"Hot Dogs caseros. Come bien a un precio accesible\"</body></html>";
        shortName="hot dog";
        m.add(new Product(name, price, img, exists, deal, desc, shortName));
        
        name = "Torta";
        price = 30.00f;
        img = new File(path + "images\\tortas.jpg");
        exists = 27;
        deal = 5;
        desc = "<html><body>\"Las tortas más grandes del área metropolitana\"</body></html>";
        shortName="torta";
        m.add(new Product(name, price, img, exists, deal, desc, shortName));
        
        name = "Refresco";
        price = 15.00f;
        img = new File(path + "images\\refresco.jpg");
        exists = 99;
        deal = 0;
        desc = "<html><body>\"Refrescante, no te quedes con sed\"</body></html>";
        shortName="refresco";
        m.add(new Product(name, price, img, exists, deal, desc, shortName));
        
        return m;
    }
    
}
