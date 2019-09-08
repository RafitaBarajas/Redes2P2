package UIs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import p2.Order;
import p2.Product;

public class MainPage extends JFrame{
    
    public MainPage(Socket cl, ArrayList <Product> menu, Order order){
        
        setSize(770,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Escomodo. Food Delivery Service");
        
        initializeFrame(menu);
        
        setVisible(true);
    }
    
    private void initializeFrame(ArrayList <Product> menu){
        
        //Add background panel
        JPanel bg = new JPanel();
        bg.setBackground(Color.WHITE);
        bg.setLayout(null);
        
        //The logo image 
        ImageIcon imgLogo= new ImageIcon("escomodo.png");
        JLabel logo = new JLabel(new ImageIcon(imgLogo.getImage().getScaledInstance(180,80, Image.SCALE_SMOOTH)));
        logo.setBounds(10,10, 180,80);
        
        // Cart button
        ImageIcon imgCart= new ImageIcon("cart.png");
        JButton cart = new JButton(new ImageIcon(imgCart.getImage().getScaledInstance(40,40, Image.SCALE_SMOOTH)));
        cart.setBounds(690,30, 40,40);
        cart.setOpaque(false);
        cart.setContentAreaFilled(false);
        cart.setBorderPainted(false);
        
        //top orange bar 
        JPanel header = new JPanel();
        header.setBounds(0, 0, 768, 100);
        header.setBackground(new Color(245,72,40));
        header.setLayout(null);
        
        //Cart's product counter
        JLabel q = new JLabel("0");
        q.setBounds(650,30, 40,40);
        q.setFont(new Font("Arial",0,36));
        q.setForeground(Color.WHITE);
        
        //"Productos populares title"
        JLabel title = new JLabel("Productos populares");
        title.setBounds(25,130, 200,20);
        title.setFont(new Font("Arial",1,20));
        title.setForeground(new Color(245,72,40));
        
        //orange bar to divide sections
        JLabel bar = new JLabel("_______________________________________________________________");
        bar.setBounds(25,145, 700,20);
        bar.setFont(new Font("Arial",0,20));
        bar.setForeground(new Color(245,72,40));
        
        //Initialize 2 products
        for ( int i = 0; i < menu.size(); i++) {
            
            //images
            ImageIcon imgP= new ImageIcon("defaultFood.png");//cambiar por la ruta
            JButton p = new JButton(new ImageIcon(imgP.getImage().getScaledInstance(160,100, Image.SCALE_SMOOTH)));
            p.setBounds(25+(240*i),190, 160,100);
            p.setOpaque(false);
            p.setContentAreaFilled(false);
            p.setBorderPainted(false);
            
            //product name and price
            JLabel nameAP = new JLabel(menu.get(i).getName()+" ($"+menu.get(i).getPrice()+")");
            nameAP.setBounds(25+(240*i),300, 240,20);
            nameAP.setFont(new Font("Arial",2,14));
            nameAP.setForeground(new Color(245,72,40));
            
            //product description
            JLabel details = new JLabel(menu.get(i).getDescription());
            details.setBounds(25+(240*i),325, 160,45);
            details.setFont(new Font("Arial",0,12));
            details.setForeground(Color.BLACK);
            
            //add to cart button
            JButton add = new JButton("Agregar "+menu.get(i).getShortName());
            add.setBounds(25+(240*i),380, 160,20);
            add.setFont(new Font("Arial",0,12));
            add.setForeground(Color.WHITE);
            //add.setContentAreaFilled(false);
            add.setBackground(new Color(245,72,40));
            add.setBorderPainted(false);
            
            //bottom products
            if( i > 2){
                p.setBounds(25+240*(i-3),190+265, 160,100);
                nameAP.setBounds(25+240*(i-3),300+265, 240,20);
                details.setBounds(25+240*(i-3),325+265, 160,45);
                add.setBounds(25+240*(i-3),380+265, 160,20);
            }
            
            bg.add(nameAP);
            bg.add(p);
            bg.add(details);
            bg.add(add);
        }
        
        //add all components
        header.add(logo);
        header.add(cart);
        header.add(q);
        
        bg.add(header);
        bg.add(title);
        bg.add(bar);
        
        this.getContentPane().add(bg);
    }
    
    
}
