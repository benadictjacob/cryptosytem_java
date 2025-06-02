/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ACER
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Traffic
{
Traffic()
{
JFrame T=new JFrame("TRAFFIC LIGHT");
JPanel p1=new JPanel();
JPanel p2=new JPanel();
JPanel p3=new JPanel();
JPanel p4=new JPanel();
JPanel p5=new JPanel();
JPanel p6=new JPanel();
JPanel p7=new JPanel();
JRadioButton b[]=new JRadioButton[3];
ButtonGroup bg=new ButtonGroup();
b[2]=new JRadioButton("GO");
b[2].setBackground(Color.WHITE);
b[1]=new JRadioButton("WAIT");
b[1].setBackground(Color.WHITE);
b[0]=new JRadioButton("STOP");
b[0].setBackground(Color.WHITE);
bg.add(b[0]);
bg.add(b[1]);
bg.add(b[2]);
p1.setBackground(Color.BLACK);
p2.setBackground(Color.BLACK);
p3.setBackground(Color.BLACK);
p4.setBackground(Color.BLACK);
p5.setBackground(Color.BLACK);
p6.setBackground(Color.BLACK);
p7.setBackground(Color.BLACK);
p2.add(p4);
p2.add(b[0]);
p2.add(p5);
p2.add(b[1]);
p2.add(p6);
p2.add(b[2]);
p2.add(p7);
p2.setLayout(new GridLayout(7,1));
T.add(p1);
T.add(p2);
T.add(p3);
T.setSize(600,600);
T.setLayout(new GridLayout(1,4));
T.setVisible(true);
T.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
b[0].addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
b[0].setBackground(Color.RED);
b[1].setBackground(Color.WHITE);
b[2].setBackground(Color.WHITE);
}
});
b[1].addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
b[0].setBackground(Color.WHITE);
b[1].setBackground(Color.YELLOW);
b[2].setBackground(Color.WHITE);
}
});
b[2].addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
b[0].setBackground(Color.WHITE);
b[1].setBackground(Color.WHITE);
b[2].setBackground(Color.GREEN);
}
});
}

public static void main(String args[])

{

new Traffic();

}

}
