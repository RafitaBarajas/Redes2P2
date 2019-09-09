
package p2;

import java.io.Serializable;
import java.util.ArrayList;

public class Ticket implements Serializable{
    
    private ArrayList<ArrayList> products;
    
    public Ticket(){
        products = new ArrayList();
        products.add(new ArrayList()); // 0 Product name
        products.add(new ArrayList()); // 1 Quantity
        products.add(new ArrayList()); // 2 Price
    }
    
    public Ticket(ArrayList<Product> menu, Order order){
        products = new ArrayList();
        products.add(new ArrayList());
        products.add(new ArrayList());
        products.add(new ArrayList());
        
        for (int i = 0; i < order.getLenght(); i++) {
            for (int j = 0; j < menu.size(); j++) {
                if (order.getProductID(i) == menu.get(j).getId()){
                    float discount = (menu.get(j).getPrice() * ((float)menu.get(j).getDeal()/100));
                    products.get(0).add(menu.get(j).getName());
                    products.get(2).add((float)order.getQty(i) * (menu.get(j).getPrice()-discount));
                }
            }
            
            products.get(1).add(order.getQty(i));
        }
    }
    
    public int getNumProducts(){
        return products.get(0).size();
    }
    
    public ArrayList<ArrayList> getProducts(){
        return products;
    }
    
    public float getTotal(){
        float total = 0;
        
        for(int i = 0; i < this.getNumProducts(); i++){
            total = total + (float)products.get(2).get(i);
        }
        
        return total;
    }
    
    
    
}
