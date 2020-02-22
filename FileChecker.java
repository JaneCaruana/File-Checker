import java.util.*;
import java.nio.file.*;
import static java.nio.file.AccessMode.*;
import java.nio.file.attribute.*;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;





public class FileChecker{
   public static void main(String args[]){

  JFrame f= new JFrame();
  //declaring objects
  
  //labels
  JLabel l1= new JLabel("Enter a file path:");
  
  //textfield
  JTextField t1= new JTextField();
  
  //buttons
  JButton b1= new JButton("Option 1");
  JButton b2= new JButton("Option 2");
  JButton b3= new JButton("Option 3");
  JButton b4= new JButton("CLEAR");
  JButton b5= new JButton("EXIT");
  
  JTextArea area= new JTextArea();
  JScrollPane scrollPane = new JScrollPane(area);
  JTextArea area1 = new JTextArea("\nOPTIONS:" + "\n\n" + "OPTION 1:Display file attributes." + "\n" + "OPTION 2: Display Elements of the file path." + "\n" + "OPTION 3: Delete a file");
  
     
  
  
  l1.setBounds(50,100,130,30);
  l1.setFont (new Font ("Helvetica", Font.BOLD| Font.ITALIC, 13));
  l1.setForeground(Color.green);
  //l1.setForeground(Color.blue);
  //l2.setBounds(150,220,120,120);
  t1.setBounds(180,100,300,30);
  b1.setBackground(Color.yellow);
  b1.setBounds(150,150,95,30);
  b2.setBounds(265,150,95,30);
  b2.setBackground(Color.yellow);
  b3.setBounds(380,150,95,30);
  b3.setBackground(Color.yellow);
  b4.setBounds(200,200,95,30);
  b4.setBackground(Color.lightGray);
  b4.setForeground(Color.white);
  b5.setBounds(320,200,95,30);
  b5.setBackground(Color.lightGray);
  b5.setForeground(Color.white);
  area.setBounds(120,350,400,180);
  area1.setBounds(120,240,280,100);
  area1.setBackground(Color.black);
  area1.setForeground(Color.white);
  
  
  //adding to frame
  f.add(l1);
  //f.add(l2);
  f.add(t1);
  f.add(b1);
  f.add(b2);
  f.add(b3);
  f.add(b4);
  f.add(b5);
  f.add(area);
  f.add(area1);
  
    

  
f.setTitle("File Checker");
/*f.setSize(800,800);
f.setVisible(true);
f.setLayout(null);
f.setBackground(Color.BLUE);*/
   f.setSize(620,620);
   f.setLocation(550,100);
   f.setLayout(null);
   f.setVisible(true);
   f.getContentPane().setBackground(Color.black);
   

f.addWindowListener(new WindowAdapter(){
         public void windowClosing(WindowEvent e){
            System.exit(0);
            }
            });
          
f.addMouseListener(new MouseAdapter() {
   public void mousePressed(MouseEvent me) {}
   public void mouseReleased(MouseEvent me) {}
   public void mouseClicked(MouseEvent me) {}
    }); 




   
   b1.addActionListener(new ActionListener(){

   @Override
   public void actionPerformed(ActionEvent e){
   
         
      String filename = t1.getText();
      Path filepath = Paths.get(filename);
      
      
      try{
         
         BasicFileAttributes attrib = Files.readAttributes(filepath, BasicFileAttributes.class);
         filepath.getFileSystem().provider().checkAccess(filepath, READ, WRITE, EXECUTE);
         
         area.setText("Permission to read, write and execute is allowed."+"\n"+"File: " + filepath.toString()+"\n"+ "Creation time: " + attrib.creationTime()+"\n"+
          "File size: "+ attrib.size()+"\n"+"Last access: "+ attrib.lastAccessTime()+"\n"
          +"Last modified: "+ attrib.lastModifiedTime());
         
      }
     catch(IOException ex){
         area.setText("File cannot be used.");
      }
}
}
   
);
 b2.addActionListener(new ActionListener(){

   @Override
   public void actionPerformed(ActionEvent e){
   
      String filename = t1.getText();
      Path filepath = Paths.get(filename);
      String sum= " ";
      String elements = " ";
   
            for (int i = 0; i<filepath.getNameCount();i++){
            
            sum += "Element " + i + " : " + filepath.getName(i)+"\n";
           
           }
          
           
            area.setText("Path name: " + filepath.toString()+"\n" +"File name: " + filepath.getFileName()+
      "\n" +"Number of elements: " + filepath.getNameCount()+"\n"+sum );
           
        } 
      
   });    b3.addActionListener(new ActionListener(){

   @Override
   public void actionPerformed(ActionEvent e){
   
      String filename = t1.getText();
      Path filepath = Paths.get(filename);
      
      try{
         Files.delete(filepath);
         area.setText("File or directory is deleted. ");
         
      }catch(NoSuchFileException b){
      
         area.setText("Error : No such file or directory.");
         
      }catch(DirectoryNotEmptyException c){
         area.setText("Error : Directory is not empty.");
         
      }catch(SecurityException d){
         area.setText("Error : No permission to delete. ");
         
      }catch(IOException f){
      
         area.setText("Error : IO Exception");
      } 
      }     
      
   });
   b4.addActionListener(new ActionListener(){

   @Override
   public void actionPerformed(ActionEvent e){
   area.setText(" ");
   t1.setText(" ");
   }
   }
  );

b5.addActionListener(new ActionListener(){

@Override
public void actionPerformed(ActionEvent e){
System.exit(0);
}
});

}

}



   
   




  
  
  