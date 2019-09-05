
package p2;

import java.io.ObjectOutputStream;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    
    //static String path = "C:\\Users\\alumno\\Desktop\\Server\\";
    static String path = "C:\\Users\\grafi_000\\Desktop\\Server\\";

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
                
                System.out.println("Entregando carta...");
                oos.writeObject(menu);
                oos.flush();
                
                System.out.println("Tomando orden...");
                
                /*
                Recibe la orden
                ObjectInputStream ois para recibir
                se añade a orders ( orders.add(ois.read()) )
                */
                
                /*
                Generar ticket antes de la desconexión del cliente
                se manda el menu y la orden del cliente 
                al constructor del ticket
                */
                
                
                oos.close();
                //cerrar ois
            }
        
        } catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    public String sendMenu(){
        
        
        return "";
    }
    
    public static ArrayList<Product> createMenu(ArrayList<Product> m){
        String name;
        float price;
        File img;
        int exists;
        int deal;
        String desc;
        
        name = "Pizza de Peperoni";
        price = 79.00f;
        img = new File(path + "images\\pizzaPepe.jpg");
        exists = 74;
        deal = 10;
        desc = "La mejor pizza del área metropolitana, cocinada por los mismos dioses de las pizzas";
        m.add(new Product(name, price, img, exists, deal, desc));
        
        name = "Hamburguesa de Pollo";
        price = 53.00f;
        img = new File(path + "images\\hamPollo.jpg");
        exists = 13;
        deal = 15;
        desc = "La mejor hamburguesa del área metropolitana, cocinada por los mismos dioses de las pizzas";
        m.add(new Product(name, price, img, exists, deal, desc));
        
        name = "Papas a la Francesa";
        price = 30.00f;
        img = new File(path + "images\\papas.jpg");
        exists = 27;
        deal = 5;
        desc = "Las mejores papas del área metropolitana, cocinadas por los mismos dioses de las pizzas";
        m.add(new Product(name, price, img, exists, deal, desc));
        
        name = "Hot Dog";
        price = 45.00f;
        img = new File(path + "images\\hotdog.jpg");
        exists = 16;
        deal = 0;
        desc = "El mejor Hot Dog del área metropolitana, cocinado por los mismos dioses de las pizzas";
        m.add(new Product(name, price, img, exists, deal, desc));
        
        name = "Papas a la Francesa";
        price = 30.00f;
        img = new File(path + "images\\papas.jpg");
        exists = 27;
        deal = 5;
        desc = "Las mejores papas del área metropolitana, cocinadas por los mismos dioses de las pizzas";
        m.add(new Product(name, price, img, exists, deal, desc));
        
        name = "Refresco";
        price = 15.00f;
        img = new File(path + "images\\refresco.jpg");
        exists = 99;
        deal = 0;
        desc = "El mejor refresco del área metropolitana, cocinado por los mismos dioses de las pizzas";
        m.add(new Product(name, price, img, exists, deal, desc));
        
        return m;
    }
    
}
