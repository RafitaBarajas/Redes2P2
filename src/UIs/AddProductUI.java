package UIs;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import p2.Order;
import p2.Product;

public class AddProductUI extends JFrame implements ActionListener{
    
    Socket cl;
    DataOutputStream dos;
    ArrayList <Product> menu;
    ObjectInputStream ois;
    Order order;
    int position;
    
    JPanel bg;
    JLabel name, bar, deal, unavailable, q, e;
    JButton back, plus, minus, confirm;
    
    public AddProductUI(Socket cl, DataOutputStream dos, ObjectInputStream ois, ArrayList <Product> menu, Order order, int position){
        //basic properties
        setSize(450,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Escomodo. Food Delivery Service");
        
        //initialize variable values
        this.cl = cl;
        this.menu = menu;
        this.order = order;
        this.position = position;
        this.dos = dos ;
        this.ois = ois;
        
        initializeFrame();
        
        setVisible(true);
    }
    
    private void initializeFrame(){
        
        //Add background panel
        bg = new JPanel();
        bg.setBackground(Color.WHITE);
        bg.setLayout(null);
        
        //product name label
        name = new JLabel(menu.get(position).getName()+ "($"+menu.get(position).getPrice()+")");
        name.setBounds(25,25, 400,20);
        name.setFont(new Font("Arial",1,20));
        name.setForeground(new Color(245,72,40));
        
        //orange bar to divide sections
        bar = new JLabel("___________________________________");
        bar.setBounds(25,50, 400,20);
        bar.setFont(new Font("Arial",0,20));
        bar.setForeground(new Color(245,72,40));
        
        //product deal label
        deal = new JLabel("Descuento de "+menu.get(position).getDeal()+"% por tiempo limitado. ¡Aprovéchalo!");
        deal.setBounds(25,75, 400,20);
        deal.setFont(new Font("Arial",1,14));
        deal.setForeground(Color.BLACK);
        
        //product existance label
        e = new JLabel("Disponibilidad: "+menu.get(position).getExistance()+" unidades");
        e.setBounds(25,85, 400,20);
        e.setFont(new Font("Arial",0,14));
        e.setForeground(Color.BLACK);
        
        //add-one-more button
        plus= new JButton("+");
        plus.setBounds(145,120, 50,50);
        plus.setFont(new Font("Arial",1,14));
        plus.setForeground(Color.WHITE);
        plus.setBackground(new Color(245,72,40));
        plus.setBorderPainted(false);
        plus.addActionListener(this);
        
        q = new JLabel("1");
        q.setBounds(105,120, 50,50);
        q.setFont(new Font("Arial",1,18));
        q.setForeground(Color.BLACK);
        
        //remove-one button
        minus= new JButton("-");
        minus.setBounds(25,120, 50,50);
        minus.setFont(new Font("Arial",1,14));
        minus.setForeground(Color.WHITE);
        minus.setBackground(new Color(245,72,40));
        minus.setBorderPainted(false);
        minus.addActionListener(this);
        
        //confirm button
        confirm= new JButton("Confirmar producto");
        confirm.setBounds(25,215, 170,20);
        confirm.setFont(new Font("Arial",0,12));
        confirm.setForeground(Color.WHITE);
        confirm.setBackground(new Color(245,72,40));
        confirm.setBorderPainted(false);
        confirm.addActionListener(this);
        
        //check if there are products available
        if( menu.get(position).getExistance() == 0){
            unavailable = new JLabel("Producto agotado :(");
            unavailable.setBounds(25,90, 400,20);
            unavailable.setFont(new Font("Arial",1,18));
            unavailable.setForeground(Color.BLACK);
            bg.add(unavailable);
        }
        else{
            //check if there is any deal
            if( menu.get(position).getDeal() != 0){
                
                e.setBounds(25,100, 400,20);
                minus.setBounds(25,135, 50,50);
                q.setBounds(105,135, 50,50);
                plus.setBounds(145,135, 50,50);
                
                bg.add(deal);
            }
            
            bg.add(e);
            bg.add(plus);
            bg.add(minus);
            bg.add(q);
            bg.add(confirm);
        }
        
        //return button
        back= new JButton("Cancelar");
        back.setBounds(250,215, 160,20);
        back.setFont(new Font("Arial",0,12));
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(245,72,40));
        back.setBorderPainted(false);
        back.addActionListener(this);
        
        //add components
        bg.add(back);
        bg.add(name);
        bg.add(bar);
        
        this.getContentPane().add(bg);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == back ){
            try {
                dos.writeInt(-1);
            } catch (IOException ex) {
                Logger.getLogger(AddProductUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            dispose();
            MainPage mp = new MainPage(cl,dos,ois, menu, order);
        }
        else if( e.getSource() == plus){
            int a = Integer.parseInt(q.getText().toString());
            
            if( a < menu.get(position).getExistance()){
                q.setText(Integer.toString(a+1));
            }
        }
        else if( e.getSource() == minus){
            int a = Integer.parseInt(q.getText().toString());
            
            if( a != 1){
                q.setText(Integer.toString(a-1));
            }
        }
        else if( e.getSource() == confirm){
            
            int a = Integer.parseInt(q.getText().toString());
            order.add(position, a);
            menu.get(position).setExistance(menu.get(position).getExistance()-a);
            
            try {
                dos.writeInt(-1);
            } catch (IOException ex) {
                Logger.getLogger(AddProductUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            dispose();
            MainPage mp = new MainPage(cl, dos, ois, menu, order);
        }
    }
    
}
