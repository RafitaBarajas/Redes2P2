
package p2;

import UIs.MainPage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    static int pto = 9000;
    static String host = "localhost";
    //static String path = "C:\\Users\\Rafael Barajas\\Desktop\\";
    //static String path = "C:\\Users\\alumno\\Desktop\\";
    static String path = "C:\\Users\\chistopher\\Desktop\\Client\\";
    
    public static void main(String[] args) {
        
        ArrayList<Product> menu = new ArrayList<Product>();
        Order order;
        int nFiles;
        
        try{
            Socket cl = new Socket(host, pto);
            System.out.println("Entrando al restaurante...");
            
            /*descargar imagenes
            download(cl);
            */
            
            DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(cl.getInputStream());
            
            System.out.println("Recibiendo carta");
            menu = (ArrayList<Product>)ois.readObject();
            //ois.close();
            
            order= new Order();
            
            
            MainPage r = new MainPage(cl,dos, ois,menu, order);
            

            
            //oos.close();

            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void download(Socket cl){
        try{
            
            DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
            DataInputStream dis = new DataInputStream(cl.getInputStream());
            
            
            int type =  dis.readInt();
            
            if(type == 2){
                downloadFile(cl, dos, dis);
            } else if(type == 1){
                downloadDirectory(cl, dos, dis);
            }

            dos.close();
            dis.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void downloadFile(Socket cl, DataOutputStream dos, DataInputStream dis){
        try{
            
            String nombre;
            long tam;
            //Socket cl = new Socket(host, pto);
            
            //DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
            //DataInputStream dis = new DataInputStream(cl.getInputStream());
            
            nombre = dis.readUTF();
            tam = dis.readLong();
            
            System.out.println("Preparando para recibir el archivo "
                + nombre + " de " + tam + " bytes, desde " + cl.getInetAddress() 
                + ":" + cl.getPort() + "\n \n");
            
            FileOutputStream fos = new FileOutputStream(path + nombre);
            DataOutputStream dos2 = new DataOutputStream(fos);
            
            long recibidos = 0;
            int n;
            
            while(recibidos < tam){
                byte[] b = new byte[2000];
                n = dis.read(b);
                recibidos = recibidos + n;

                dos2.write(b, 0, n);
                dos2.flush();
            }
            
            System.out.println("Archivo recibido.");
                        
            fos.close();
            //dos.close();
            dos2.close();
            //dis.close();
            //cl.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void downloadDirectory(Socket cl, DataOutputStream dos, DataInputStream dis){
        try{
            //DataInputStream dis = new DataInputStream(cl.getInputStream());
            
            String nombre = dis.readUTF();
            
            System.out.println("Preparando para recibir el directorio "
                + nombre + " desde " + cl.getInetAddress() 
                + ":" + cl.getPort() + "\n \n");
            
            File f = new File (path + nombre);
            f.mkdir();
            f.setWritable(true);

            System.out.println("Directorio recibido.");
            
            int more = dis.readInt();
            int type;
            
            if (more > 0) {
                for (int i = 0; i < more; i++) {
                    type = dis.readInt();
                    if (type == 1) {
                        downloadDirectory(cl, dos, dis);
                    } else if (type == 2){
                        downloadFile(cl, dos ,dis);
                    }
                }
            }
            
            //dis.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
