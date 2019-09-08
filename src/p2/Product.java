
package p2;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {
    private int id;
    private String name;
    private float price;
    private File image;
    private int existance;
    private int deal;            //Percentage
    private String description;
    private String shortName;
    
    static ArrayList ids = new ArrayList();
    //static String path = "C:\\Users\\alumno\\Desktop\\";
    static String path = "C:\\Users\\grafi_000\\Desktop\\";
    
    public Product(){
        id = newID();
        name = "Producto " + id;
        price = 10.0f;
        image = new File(path + "Server\\images\\default.jpg");
        existance = 100;
        deal = 0;
        description = "Default Product";
        shortName="default";
        
        ids.add(id);
    }
    
    public Product(String name, float price, File img, int exists, int deal, String desc, String shortName){
        id = newID();
        this.name = name;
        this.price = price;
        this.image = img;
        this.existance = exists;
        this.deal = deal;
        this.description = desc;
        this.shortName = shortName;
        
        ids.add(id);
    }
    
    private static int newID(){
        int idcheck = 0;
        
        for (int i = 0; i < ids.size(); i++){
            if(idcheck == (int)ids.get(i)){
                idcheck++;
                i = 0;
            }
        }
        
        ids.add(idcheck);
        
        return idcheck;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public File getImage() {
        return image;
    }

    public int getExistance() {
        return existance;
    }

    public int getDeal() {
        return deal;
    }

    public String getDescription() {
        return description;
    }
    
    public String getShortName(){
        return shortName;
    }
    
    public void printProduct(){
        System.out.println(id + ". " + name + "\t $" + price);
    }
    
}
