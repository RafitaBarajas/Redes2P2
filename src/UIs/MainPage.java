package UIs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import p2.Order;
import p2.Product;
import p2.Ticket;

public class MainPage extends JFrame implements ActionListener{
    
    Socket cl;
    DataOutputStream dos;
    ArrayList <Product> menu;
    ObjectInputStream ois;
    Order order;
    JPanel bg, header;
    ImageIcon imgLogo, imgCart, imgP;
    JButton cart, products [], pImageB[];
    ImageIcon pImage [];
    JLabel title, bar, nameAP, details, logo;
    int r1=0, r2=0, r3=0, r4=0,r5=0, r6=0;
    
    public MainPage(Socket cl, DataOutputStream dos, ObjectInputStream ois, ArrayList <Product> menu, Order order){
        
        setSize(770,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Escomodo. Food Delivery Service");
        
        this.cl = cl;
        this.menu = menu;
        this.order = order;
        this.dos = dos;
        this.ois =  ois;
        
        initializeFrame();
        
        setVisible(true);
    }
    
    private void initializeFrame(){
        
        //Add background panel
        bg = new JPanel();
        bg.setBackground(Color.WHITE);
        bg.setLayout(null);
        
        //The logo image 
        imgLogo= new ImageIcon("escomodo.png");
        logo = new JLabel(new ImageIcon(imgLogo.getImage().getScaledInstance(180,80, Image.SCALE_SMOOTH)));
        logo.setBounds(10,10, 180,80);
        
        // Cart button
        imgCart= new ImageIcon("cart.png");
        cart = new JButton(new ImageIcon(imgCart.getImage().getScaledInstance(40,40, Image.SCALE_SMOOTH)));
        cart.setBounds(690,30, 40,40);
        cart.setOpaque(false);
        cart.setContentAreaFilled(false);
        cart.setBorderPainted(false);
        cart.addActionListener(this);
        
        //top orange bar 
        header = new JPanel();
        header.setBounds(0, 0, 768, 100);
        header.setBackground(new Color(245,72,40));
        header.setLayout(null);
        
        //"Productos populares title"
        title = new JLabel("Productos populares");
        title.setBounds(25,130, 200,20);
        title.setFont(new Font("Arial",1,20));
        title.setForeground(new Color(245,72,40));
        
        //orange bar to divide sections
        bar = new JLabel("_______________________________________________________________");
        bar.setBounds(25,145, 700,20);
        bar.setFont(new Font("Arial",0,20));
        bar.setForeground(new Color(245,72,40));
        
        //initializes arrays
        products = new JButton [6];
        pImageB= new JButton [6];
        pImage =  new ImageIcon [6];
        
        //Initialize products
        for ( int i = 0; i < menu.size(); i++) {

            //images
            pImage[i]= new ImageIcon(menu.get(i).getImage()[0].getAbsolutePath());
            pImageB[i] = new JButton(new ImageIcon(pImage[i].getImage().getScaledInstance(160,100, Image.SCALE_SMOOTH)));
            pImageB[i].setBounds(25+(240*i),190, 160,100);
            pImageB[i].setOpaque(false);
            pImageB[i].setContentAreaFilled(false);
            pImageB[i].setBorderPainted(false);
            pImageB[i].addActionListener(this);
            
            //product name and price
            nameAP = new JLabel(menu.get(i).getName()+" ($"+menu.get(i).getPrice()+")");
            nameAP.setBounds(25+(240*i),300, 240,20);
            nameAP.setFont(new Font("Arial",2,14));
            nameAP.setForeground(new Color(245,72,40));
            
            //product description
            details = new JLabel(menu.get(i).getDescription());
            details.setBounds(25+(240*i),325, 160,45);
            details.setFont(new Font("Arial",0,12));
            details.setForeground(Color.BLACK);
            
            //add to cart buttons
            products[i] = new JButton("Agregar "+menu.get(i).getShortName());
            products[i].setBounds(25+(240*i),380, 160,20);
            products[i].setFont(new Font("Arial",0,12));
            products[i].setForeground(Color.WHITE);
            products[i].setBackground(new Color(245,72,40));
            products[i].setBorderPainted(false);
            products[i].addActionListener(this);
            
            //bottom products
            if( i > 2){
                pImageB[i].setBounds(25+240*(i-3),190+265, 160,100);
                nameAP.setBounds(25+240*(i-3),300+265, 240,20);
                details.setBounds(25+240*(i-3),325+265, 160,45);
                products[i].setBounds(25+240*(i-3),380+265, 160,20);
            }
            
            bg.add(nameAP);
            bg.add(pImageB[i]);
            bg.add(details);
            bg.add(products[i]);
        }
        
        //add all components
        header.add(logo);
        header.add(cart);
        
        bg.add(header);
        bg.add(title);
        bg.add(bar);
        
        this.getContentPane().add(bg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == products[0]){
            try {
                dos.writeInt(-1);
            } catch (IOException ex) {}
            dispose();
            AddProductUI a= new AddProductUI(cl,dos,ois, menu, order,0);
        }
        else if(e.getSource() == products[1]){
            try {
                dos.writeInt(-1);
            } catch (IOException ex) {}
            dispose();
            AddProductUI a= new AddProductUI(cl,dos,ois, menu, order,1);
        }
        else if(e.getSource() == products[2]){
            try {
                dos.writeInt(-1);
            } catch (IOException ex) {}
            dispose();
            AddProductUI a= new AddProductUI(cl,dos, ois, menu, order,2);
        }
        else if(e.getSource() == products[3]){
            try {
                dos.writeInt(-1);
            } catch (IOException ex) {}
            dispose();
            AddProductUI a= new AddProductUI(cl,dos, ois, menu, order,3);
        } 
        else if(e.getSource() == products[4]){
            try {
                dos.writeInt(-1);
            } catch (IOException ex) {}
            dispose();
            AddProductUI a= new AddProductUI(cl,dos, ois, menu, order,4);
        }
        else if(e.getSource() == products[5]){
            try {
                dos.writeInt(-1);
            } catch (IOException ex) {}
            dispose();
            AddProductUI a= new AddProductUI(cl,dos, ois, menu, order,5);
        }
        else if(e.getSource() == pImageB[0]){
            r1=r1+1;
            if( r1 > (menu.get(0).getImage().length-1)){
                r1=0;
            }
            pImage[0]= new ImageIcon(menu.get(0).getImage()[r1].getAbsolutePath());
            pImageB[0].setIcon(new ImageIcon(pImage[0].getImage().getScaledInstance(160,100, Image.SCALE_SMOOTH)));
        }
        else if(e.getSource() == pImageB[1]){
            r2=r2+1;
            if( r2 > (menu.get(1).getImage().length-1)){
                r2=0;
            }
            pImage[1]= new ImageIcon(menu.get(1).getImage()[r2].getAbsolutePath());
            pImageB[1].setIcon(new ImageIcon(pImage[1].getImage().getScaledInstance(160,100, Image.SCALE_SMOOTH)));
        }
        else if(e.getSource() == pImageB[2]){
            r3=r3+1;
            if( r3 > (menu.get(2).getImage().length-1) ){
                r3=0;
            }
            pImage[2]= new ImageIcon(menu.get(2).getImage()[r3].getAbsolutePath());
            pImageB[2].setIcon(new ImageIcon(pImage[2].getImage().getScaledInstance(160,100, Image.SCALE_SMOOTH)));
        }
        else if(e.getSource() == pImageB[3]){
            r4=r4+1;
            if( r4 > (menu.get(3).getImage().length-1) ){
                r4=0;
            }
            pImage[3]= new ImageIcon(menu.get(3).getImage()[r4].getAbsolutePath());
            pImageB[3].setIcon(new ImageIcon(pImage[3].getImage().getScaledInstance(160,100, Image.SCALE_SMOOTH)));
        } 
        else if(e.getSource() == pImageB[4]){
            r5=r5+1;
            if( r5 > (menu.get(4).getImage().length-1) ){
                r5=0;
            }
            pImage[4]= new ImageIcon(menu.get(4).getImage()[r5].getAbsolutePath());
            pImageB[4].setIcon(new ImageIcon(pImage[4].getImage().getScaledInstance(160,100, Image.SCALE_SMOOTH)));
        }
        else if(e.getSource() == pImageB[5]){
            r6=r6+1;
            if( r6 > (menu.get(5).getImage().length-1) ){
                r6=0;
            }
            pImage[5]= new ImageIcon(menu.get(5).getImage()[r6].getAbsolutePath());
            pImageB[5].setIcon(new ImageIcon(pImage[5].getImage().getScaledInstance(160,100, Image.SCALE_SMOOTH)));
        }
        else if( e.getSource() == cart){
            String message = getOrder();
            int answ;
            
            if( order.getLenght() == 0){
                JOptionPane.showMessageDialog(null, message, "Orden actual", JOptionPane.WARNING_MESSAGE);
            }
            else{
                answ = JOptionPane.showConfirmDialog(null, message, "Orden actual", JOptionPane.YES_NO_OPTION);
                System.out.println(answ);
                if ( answ == 0){
                    try {
                        dos.writeInt(1);
                        
                        ObjectOutputStream oos = new ObjectOutputStream(cl.getOutputStream());
                        
                        oos.writeObject(order);
                        oos.flush();
                        System.out.println("Orden enviada");
                        
                        Ticket t = (Ticket)ois.readObject();
                        
                        System.out.println("Ticket recibido");
                       
                        oos.writeObject(menu);
                        
                        TicketUI tu = new TicketUI(t, cl, dos, ois);
                        
                        dispose();
                    } catch (Exception w) {w.printStackTrace();}
                }
            }
        }

    }
    
    public String getOrder(){
        String message = "";
        if( order.getLenght() == 0){
            message = "No se han agregado productos";
        }
        else{
            for (int i = 0; i < order.getLenght(); i++) {
                message = message + Product.findNameByID((int)order.getOrder().get(0).get(i))+" - "+ (int)order.getOrder().get(1).get(i);
                message = message +"\n";
            }
            message = message + "¿Desea pagar su orden?";
        }
        
        
        return message;
    }
}
