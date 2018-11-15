/*
* HW - TextEditor
* @author Chieh Shih
 * CPE-552
 * Chieh Shih
 * 10431509
 *Ge Chang
 * 10410233
*/
//package class5;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.io.BufferedWriter;


public class HW_TextEditor extends JFrame{
    
    private JMenuBar menubar;
    private JMenu File, Build;
    private JMenuItem New, save, open, quit, Compile,Run;
    private JTextArea t = new JTextArea();
    private FileDialog openFile;
    private boolean isSaved= false;
    
//    openDia = new FileDialog(this, "Open", FileDialog.LOAD);
//        saveDia = new FileDialog(f, "Save", FileDialog.SAVE);

    public HW_TextEditor(){
        super("MyTextEditor");
        setSize(800,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setBackground(Color.RED);
        
        Font f = new Font("TimeRoman", Font.BOLD, 50);
        t.setFont(f);      
//        FileDialog.LOAD;
        
        menubar = new JMenuBar();
        
        File = new JMenu("File");
        New = new JMenuItem("New");
        save = new JMenuItem("save");
        open = new JMenuItem("open");
        quit = new JMenuItem("quit");
        //add menuList onto Menu
        File.add(New);
        File.add(save);
        File.add(open);
        File.add(quit);
        
        Build = new JMenu("Build");
        Compile = new JMenuItem("Compile");
        Run = new JMenuItem("Rum");
        Build.add(Compile);
        Build.add(Run);
        
        Font fMenu = new Font("TimeRoman", Font.BOLD, 30);
        Font fMenuItem = new Font("TimeRoman", Font.BOLD, 20);
        // set Menu font
        File.setFont(fMenu);
        Build.setFont(fMenu);
        // set Menu items font
        New.setFont(fMenuItem);
        save.setFont(fMenuItem);
        open.setFont(fMenuItem);
        quit.setFont(fMenuItem);
        Compile.setFont(fMenuItem);
        Run.setFont(fMenuItem);
        
        //add Menu onto Menu Bar
        menubar.add(File);
        menubar.add(Build);
        
        // add JMenuItem into my ActionListener
        quit.addActionListener(new ActionHandler());
        // add New to my ActionListener
        New.addActionListener(new ActionHandler());
        // save
        save.addActionListener(new ActionHandler());
        //open
        open.addActionListener(new ActionHandler());
        //Compile
        Compile.addActionListener(new ActionHandler());
        
        
        this.setJMenuBar(menubar);
        c.add(t, BorderLayout.CENTER);
        setVisible(true);
    }
    
    public String lastSaved(){
        return t.getText();
    }
    public void save() throws IOException{        
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter the file Name (including '.txt'):");
            String fileName = sc.nextLine();
//            if(sc.nextLine().equals("")){
//                sc.close();
//            }
//            String lastSaved = t.getText();
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            for(String line: lastSaved().split("\\n")){
            bw.write(line);
            bw.newLine();
            }                     
            bw.flush();
    }
    public void open() throws FileNotFoundException, IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the file Name:");
        String fileName = sc.nextLine();

        try{        
        FileReader f = new FileReader(fileName);
        t.read(f, fileName);
        f.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        }
    
    public void runningCompile() throws Exception{
//        Scanner sc=new Scanner(System.in);
//        System.out.println("Input file name to compile:");
        openFile = new FileDialog(this, "Open", FileDialog.LOAD);
        openFile.setVisible(true);
        String dirPath = openFile.getDirectory();
        String fileName = openFile.getFile();
//        String fileName = sc.nextLine();
        Runtime r = Runtime.getRuntime();
        Process p = r.exec("javac "+ fileName);
        BufferedReader isr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        String line;
        while ((line = isr.readLine()) != null)
            System.out.println(line);
        int status = p.exitValue();
        System.out.println("Process ran with result: " + status);   
    }


    class ActionHandler implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e){
//           boolean isSaved = false;
           
//           System.out.println(isSaved);
            if(e.getSource() == quit){
//                System.out.println(isSaved);
                if(false == isSaved){
                    int dialog = JOptionPane.showConfirmDialog(null, "Would you like to save your file first?");
                    if(dialog == JOptionPane.YES_OPTION){
                        try {
                            save();
                            isSaved = true;
                        } catch (IOException ex) {
                            Logger.getLogger(HW_TextEditor.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        System.exit(0);
                    }
                }
                System.exit(0);
            }
            else if(e.getSource() == Compile){
                if(false == isSaved){
                    int dialog = JOptionPane.showConfirmDialog(null, "Would you like to save your file before compiling?");
                    if(dialog == JOptionPane.YES_OPTION){
                        try {
                            save();
                            isSaved = true;
                        } catch (IOException ex) {
                            Logger.getLogger(HW_TextEditor.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        try {
                            runningCompile();
                        } catch (Exception ex) {
                            Logger.getLogger(HW_TextEditor.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                else{
                try {
                    runningCompile();
                } catch (Exception ex) {
                    Logger.getLogger(HW_TextEditor.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            }
            else if(e.getSource() == New){
            t.setText("");
            }
            else if(e.getSource() == save){
                try {
                    save();
                    isSaved = true;
//                    System.out.println(isSaved);
                } catch (IOException ex) {
                    Logger.getLogger(HW_TextEditor.class.getName()).log(Level.SEVERE, null, ex);
                }    
//                System.out.println(isSaved);
            }
            
            else if(e.getSource() == open){
                try {
                    open();
                } catch (IOException ex) {
                    Logger.getLogger(HW_TextEditor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            System.out.println(isSaved);
        }
    }
    public static void main(String arg[]){
            new HW_TextEditor();
    }
}
