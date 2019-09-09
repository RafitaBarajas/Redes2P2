
package p2;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable{
    private int id;
    private ArrayList<ArrayList> order; //Product ID with quantity
    
    static ArrayList ids = new ArrayList();
    
    //Constructor, inicialices list.
    public Order(){                             
        id = newID();
        order = new ArrayList();
        order.add(new ArrayList());
        order.add(new ArrayList());
    }
    
    //Constructor, inicialices list and introduces the product with the quantity
    public Order(int productID, int qty){
        id = newID();
        order = new ArrayList();
        order.add(new ArrayList());
        order.add(new ArrayList());
        
        order.get(0).add(productID);
        order.get(1).add(qty);
    }
    
    //Adds a product and its cuantity.
    public void add(int productID, int qty){
        int pos = checkProductID(productID);
        
        if(pos == -1){
            order.get(0).add(productID);
            order.get(1).add(qty);
        } else {
            int newQty = (int)order.get(1).get(pos) + qty;
            order.get(1).set(pos, newQty);
        }
    }
    
    //Deletes a product from the order.
    public void delete(int productID){
        int pos = checkProductID(productID);
        
        if(pos != -1){
            order.get(0).remove(pos);
            order.get(1).remove(pos);
        }
    }
    
    //Returns the total products of the order (Not the sum of the quantities).
    public int getLenght(){
        return order.get(0).size(); 
    }
    
    //Returns the id of the whole order.
    public int getId() {
        return id;
    }

    //Returns the whole list of the order (product id with quantities).
    public ArrayList<ArrayList> getOrder() {
        return order;
    }
    
    public int getProductID(int position){
        return (int) order.get(0).get(position);
    }
    
    public int getQty(int position){
        return (int) order.get(1).get(position);
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
    
    private int checkProductID(int pID){
        int position = -1;
        
        for (int i = 0; i < this.getLenght(); i++) {
            if((int)order.get(0).get(i) == pID){
                position = i;
            }
        }
        
        return position;
    }
    
}
