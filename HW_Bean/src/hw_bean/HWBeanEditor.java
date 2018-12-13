/*
* HW - BeanEditor
* @author Chieh Shih
 * CPE-552
 * Chieh Shih
 * 10431509
 *Ge Chang
 * 10410233
*/
package session13;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
/**
 *
 * @author user
 */
public class HWBeanEditor extends JFrame {
    private JPanel p;
    public HWBeanEditor(Object obj){
        super("BeanEditor");
        String[] attrs = buildGettersSetters(obj);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,800);
        p = new JPanel();
        setContentPane(p);
        
        JPanel jp1 = new JPanel();
        p.add(jp1);
        JLabel l1 = new JLabel("First Name:");
        JTextField textF1 = new JTextField();
        textF1.setColumns(15);
        jp1.add(l1);
        jp1.add(textF1);
        
        JPanel jp2 = new JPanel();
        p.add(jp2);
        JLabel l2 = new JLabel("Last Name:");
        JTextField textF2 = new JTextField();
        textF2.setColumns(15);
        jp2.add(l2);
        jp2.add(textF2);
        
        // add anonymous inner classes for textFields
        textF1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println(attrs[0]+ ": "+ textF1.getText()); 
            }
        });
        textF2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println(attrs[1]+ ": "+ textF2.getText()); 
            }
        });
        
        setVisible(true);
    
    }
    
    private String[] buildGettersSetters(Object obj){
        Method[] methods= obj.getClass().getMethods();
        ArrayList<String> Setters = new ArrayList<>();
        ArrayList<String> Getters = new ArrayList<>();
        for(Method m : methods){
            String name = m.getName();
            if (name.startsWith("set")){
                Setters.add(name.replaceAll("^set", ""));
            }
            if (name.startsWith("get")){
                Getters.add(name.replaceAll("^get", ""));
            }
        }
        ArrayList<String> attrs = new ArrayList<>();
        for(String g: Getters){
            if(Setters.contains(g)){
//                System.out.println(g);
                attrs.add(g);
            }
        }
//        System.out.println(attrs);
        String[] s = new String[attrs.size()];
        return attrs.toArray(s);
    }
    
    public static void main(String[] arg){
        new HWBeanEditor(new HWBean());
    }
}
