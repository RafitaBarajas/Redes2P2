
package UIs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import p2.Ticket;

public class TicketUI extends JFrame implements ActionListener{
    
    Ticket t;
    Socket cl;
    DataOutputStream dos;
    ObjectInputStream ois;
    
    JLabel thanks,product, total, bar;
    JPanel bg;
    JButton ok;
    
    public TicketUI(Ticket t, Socket cl, DataOutputStream dos, ObjectInputStream ois){
        setSize(550,550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Escomodo. Food Delivery Service");
        
        this.t = t;
        this.cl = cl;
        this.dos = dos;
        this.ois = ois;
        
        bg= new JPanel();
        bg.setBackground(Color.WHITE);
        bg.setLayout(null);
        
        // ok button
        ok = new JButton("Ok");
        ok.setBounds(25,450, 140,40);
        ok.setFont(new Font("Arial",0,20));
        ok.setForeground(Color.WHITE);
        ok.setBackground(new Color(245,72,40));
        ok.setBorderPainted(false);
        ok.addActionListener(this);
        bg.add(ok);
        
        //thanks label
        thanks = new JLabel("¡Gracias por su compra!");
        thanks.setBounds(25,25, 400,20);
        thanks.setFont(new Font("Arial",1,20));
        thanks.setForeground(new Color(245,72,40));
        bg.add(thanks);
        
        //orange bar to divide sections
        bar = new JLabel("___________________________________");
        bar.setBounds(25,50, 400,20);
        bar.setFont(new Font("Arial",0,20));
        bar.setForeground(new Color(245,72,40));
        bg.add(bar);
        
        for (int i = 0; i < t.getNumProducts(); i++) {
            //product name, quantity and price
            product = new JLabel(t.getProducts().get(0).get(i).toString()+"--- C("+t.getProducts().get(1).get(i).toString()+")--- T("+t.getProducts().get(2).get(i).toString()+")");
            product.setBounds(25,90+(40*i), 500,20);
            product.setFont(new Font("Arial",1,14));
            product.setForeground(Color.BLACK);
            bg.add(product);
        }
        
        //product name, quantity and price
        total = new JLabel("Total= "+t.getTotal());
        total.setBounds(25,350, 500,20);
        total.setFont(new Font("Arial",1,20));
        total.setForeground(Color.BLACK);
        bg.add(total);
        
        this.getContentPane().add(bg);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        try{
            ois.close();
            dos.close();
            cl.close();
        }
        catch(Exception w){
            w.printStackTrace();
        }
        
        dispose();
    }
}
