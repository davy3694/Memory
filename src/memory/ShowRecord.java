/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package memory;

/**
 *
 * @author Administrator
 */
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class ShowRecord extends JDialog implements ActionListener
{ 
  File gradeFile=null;
  JButton 确定,清除;
  JTextArea show=null;
 public ShowRecord(JFrame frame,File f)
  {
    super(frame,"记忆测试排行榜:"+f.toString());
    gradeFile=f;
    show=new JTextArea(6,4);
    确定=new JButton("显示排行榜");
    确定.addActionListener(this);
    清除=new JButton("清空排行榜");
    清除.addActionListener(this);
    Container con=getContentPane(); 
    con.add(new JScrollPane(show),BorderLayout.CENTER);
    JPanel p=new JPanel();
    p.add(确定);
    p.add(清除);
    con.add(p,BorderLayout.SOUTH);
    setBounds(100,100,320,185);
    setVisible(false);
    setModal(true); 
   
    addWindowListener(new WindowAdapter()
                        {
                          public void windwoClosing(WindowEvent e)
                            {
                              setVisible(false);
                              dispose();
                            }
                        }
                     ); 
   }

 public void actionPerformed(ActionEvent e)
  { 
    
    if(e.getSource()==确定)
     { 
       try
         {
           show.setText(null);
           FileInputStream in=new FileInputStream(gradeFile);
           ObjectInputStream object_in=new ObjectInputStream(in);
           LinkedList 成绩=(LinkedList)object_in.readObject();
           object_in.close();
           sort(成绩);                                        
           for(int i=0;i<成绩.size();i++)
            {
              People people=(People)成绩.get(i);
              show.append("\n"+people.getName()+"成绩:"+people.getTime());
            }
         }
        catch(Exception ee)
         {
         }
     }
   if(e.getSource()==清除)
     { 
       try
         {
           FileInputStream in=new FileInputStream(gradeFile);
           ObjectInputStream object_in=new ObjectInputStream(in);
           LinkedList 成绩=(LinkedList)object_in.readObject();
           object_in.close();
           成绩.clear();
           FileOutputStream out=new FileOutputStream(gradeFile);
           ObjectOutputStream object_out=new ObjectOutputStream(out);
           object_out.writeObject(成绩);
           out.close();
           object_out.close(); 
           show.setText("排行榜被清空");
         }
        catch(Exception ee)
         {
         }
     }    
  }
 public void sort(LinkedList list)
  {
    for(int i=0;i<list.size()-1;i++)
      {
        
         for(int j=i+1;j<list.size();j++)
            {
             if(((People)list.get(i)).getTime()>((People)list.get(j)).getTime())
                {
                   People temp=(People)list.get(j);
                   list.set(j,(People)list.get(i));
                   list.set(i,temp);
                }
            } 
      }
  }
}
