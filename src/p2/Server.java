
package p2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    
    static String pathO = "C:\\Users\\chistopher\\Desktop\\Server\\";
    static String pathD = "C:\\Users\\chistopher\\Desktop\\Client\\";

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
                
                // Enviar imagenes 
                //DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
                   
                //File f = new File(pathO);

                //if(f.isDirectory()){
                //    sendDirectory(cl, dos, f, "");
                //} else {
                //    sendFile(cl, dos, f, "");
                //}

                //dos.close();
                System.out.println("Enviando galería...");
                
                ObjectOutputStream oos = new ObjectOutputStream(cl.getOutputStream());
                DataInputStream dis = new DataInputStream(cl.getInputStream());
                
                System.out.println("Entregando carta...");
                oos.writeObject(menu);
                oos.flush();
                //oos.close();
                
                
                int option = -1;
                
                while( option == -1){
                    System.out.println("Esperando orden..");
                    option = dis.readInt();
                }
                
                if( option == 1 ){
                    //Server receives an Order object, then sends a Ticket
                    ObjectInputStream ois = new ObjectInputStream(cl.getInputStream());
                    
                    Order o = (Order) ois.readObject();
                    System.out.println("Orden recibida, generando ticket...");
                    
                    Ticket t = new Ticket(menu, o);
                    oos.writeObject(t);
                    oos.flush();
                    System.out.println("Ticket enviado");
                    
                    menu = (ArrayList<Product>)ois.readObject();
                    System.out.println("Disponibilidad actualizada");
                    
                    ois.close();
                    dis.close();
                    oos.close();
                    cl.close();
                    
                }
                
            }
        
        } catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    public static ArrayList<Product> createMenu(ArrayList<Product> m){
        String name;
        float price;
        File [] img;
        int exists;
        int deal;
        String desc;
        String shortName;
        
        name = "Pizza de Peperoni";
        price = 79.00f;
        img = new File[3];
        img[0]=new File(pathD + "pizzaPepe0.jpg");
        img[1]=new File(pathD + "pizzaPepe1.jpg");
        img[2]=new File(pathD + "pizzaPepe2.jpg");
        exists = 8;
        deal = 10;
        desc = "<html><body>\"La mejor pizza del área metropolitana, cocinada por los dioses de las pizzas\"</body></html>";
        shortName="pizza";
        m.add(new Product(name, price, img, exists, deal, desc, shortName));
        
        name = "Hamburguesa de Pollo";
        price = 53.00f;
        img = new File[3];
        img[0]=new File(pathD + "hamPollo0.jpg");
        img[1]=new File(pathD + "hamPollo1.jpg");
        img[2]=new File(pathD + "hamPollo2.jpg");
        exists = 13;
        deal = 15;
        desc = "<html><body>\"La mejor hamburguesa del área metropolitana, cocinada por Bob Esponja\"</body></html>";
        shortName="hamburguesa";
        m.add(new Product(name, price, img, exists, deal, desc, shortName));
        
        name = "Papas a la Francesa";
        price = 30.00f;
        img = new File[3];
        img[0]=new File(pathD + "papas0.jpg");
        img[1]=new File(pathD + "papas1.jpg");
        img[2]=new File(pathD + "papas2.jpg");
        exists = 27;
        deal = 5;
        desc = "<html><body>\"Deliciosas papas, no puedes comer solo una\"</body></html>";
        shortName="papas";
        m.add(new Product(name, price, img, exists, deal, desc, shortName));
        
        name = "Hot Dog";
        price = 45.00f;
        img = new File[3];
        img[0]=new File(pathD + "hotdog0.jpg");
        img[1]=new File(pathD + "hotdog1.jpg");
        img[2]=new File(pathD + "hotdog2.jpg");
        exists = 16;
        deal = 0;
        desc = "<html><body>\"Hot Dogs caseros. Come bien a un precio accesible\"</body></html>";
        shortName="hot dog";
        m.add(new Product(name, price, img, exists, deal, desc, shortName));
        
        name = "Torta";
        price = 30.00f;
        img = new File[3];
        img[0]=new File(pathD + "tortas0.jpg");
        img[1]=new File(pathD + "tortas1.jpg");
        img[2]=new File(pathD + "tortas2.jpg");
        exists = 27;
        deal = 5;
        desc = "<html><body>\"Las tortas más grandes del área metropolitana\"</body></html>";
        shortName="torta";
        m.add(new Product(name, price, img, exists, deal, desc, shortName));
        
        name = "Refresco";
        price = 15.00f;
        img = new File[3];
        img[0]=new File(pathD + "refresco0.jpg");
        img[1]=new File(pathD + "refresco1.jpg");
        img[2]=new File(pathD + "refresco2.jpg");
        exists = 99;
        deal = 0;
        desc = "<html><body>\"Refrescante, no te quedes con sed\"</body></html>";
        shortName="refresco";
        m.add(new Product(name, price, img, exists, deal, desc, shortName));
        
        return m;
    }
    
    public static void sendFile(Socket cl, DataOutputStream dos, File f, String parent){
        
        try{
            String nombre = parent + "\\" + f.getName();
            long tam = f.length();
            String ruta = f.getAbsolutePath();
            
            FileInputStream fis = new FileInputStream(ruta);
            DataInputStream dis = new DataInputStream(fis);

            System.out.println("Ruta " + ruta);
            
            dos.writeInt(2);
            dos.flush();
            dos.writeUTF(nombre);
            dos.flush();
            dos.writeLong(tam);
            dos.flush();

            long enviados = 0;
            int n;

            while(enviados < tam){
                byte[] b = new byte[2000];
                n = dis.read(b);
                enviados = enviados + n;

                dos.write(b, 0, n);
                dos.flush();
            }

            System.out.println("Archivo enviado.");

        
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    public static void sendDirectory(Socket cl, DataOutputStream dos, File f, String parent){
        try{
            
            String nombre = parent + "\\" + f.getName();
            String ruta = f.getAbsolutePath();
            
            
            System.out.println("Ruta " + ruta);
            
            dos.writeInt(1);
            dos.flush();
            dos.writeUTF(nombre);
            dos.flush();

            System.out.println("Directorio enviado.");
            
            File[] files = f.listFiles();
            
            if(files != null){
                dos.writeInt(files.length);
                dos.flush();
                for (int i = 0; i < files.length; i++) {
                    
                    if(files[i].isDirectory()){
                        sendDirectory(cl, dos, files[i], nombre);
                    } else {
                        sendFile(cl, dos, files[i], nombre);
                    }
                }
            } else {
                dos.writeInt(0);
                dos.flush();
            }
            
        
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
}
