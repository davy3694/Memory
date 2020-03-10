package memory;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.LinkedList;
public class Memory extends JFrame implements ActionListener
{ 
  JMenuBar bar;
  JMenu fileMenu;
  JMenuItem 初级,中级,高级,初级排行榜,中级排行榜,高级排行榜;
  Block block[];
  ImageIcon icon[];
  MemoryTestPane 记忆测试板=null;
  File file初级=new File("初级记忆排行榜.txt"),
       file中级=new File("中级记忆排行榜.txt"),
       file高级=new File("高级记忆排行榜.txt");

  LinkedList 成绩表=null;
  ShowRecord 显示成绩表对话框=null;
  int m=5,n=6;                                        
  int 图标个数=0;
  Container con=null;
  JTextField 提示条=null;
  File gradeFile=null;            
  public  Memory()
  { 
    block=new Block[m*n];
    图标个数=m; 
    icon=new ImageIcon[图标个数];
    for(int i=0;i<icon.length;i++)
       {
         icon[i]=new ImageIcon("a"+i+".gif");
       } 
    for(int i=0;i<block.length;i++)
       {
         block[i]=new Block();
         block[i].设置关闭时的图标(new ImageIcon("关闭.gif"));
       }
  
    bar=new JMenuBar();
    fileMenu=new JMenu("记忆力测试");
    初级=new JMenuItem("初级");
    中级=new JMenuItem("中级");
    高级=new JMenuItem("高级");
    fileMenu.setMnemonic('G');
    初级.setMnemonic('B');
    中级.setMnemonic('I');
    高级.setMnemonic('E');
    高级排行榜=new JMenuItem("高级排行榜");
    中级排行榜=new JMenuItem("中级排行榜");
    初级排行榜=new JMenuItem("初级排行榜");
    初级.setMnemonic('T');
    fileMenu.add(初级);
    fileMenu.add(中级);
    fileMenu.add(高级);
    fileMenu.add(初级排行榜);
    fileMenu.add(中级排行榜);
    fileMenu.add(高级排行榜);
    bar.add(fileMenu);
    setJMenuBar(bar);
    初级.addActionListener(this);
    中级.addActionListener(this);
    高级.addActionListener(this);
    初级排行榜.addActionListener(this);
    中级排行榜.addActionListener(this);
    高级排行榜.addActionListener(this);
    成绩表=new LinkedList();
    if(!file初级.exists())                         
     {
      try{
          FileOutputStream out=new FileOutputStream("初级记忆排行榜.txt");
          ObjectOutputStream object_out=new ObjectOutputStream(out);
          object_out.writeObject(成绩表);
          object_out.close();
          out.close();
         }
      catch(IOException e)
         {
         }
     } 
     if(!file中级.exists())
     {
      try{
          FileOutputStream out=new FileOutputStream("中级记忆排行榜.txt");
          ObjectOutputStream object_out=new ObjectOutputStream(out);
          object_out.writeObject(成绩表);
          object_out.close();
          out.close();
         }
      catch(IOException e)
         {
         }
     } 
     if(!file高级.exists())
     {
      try{
          FileOutputStream out=new FileOutputStream("高级记忆排行榜.txt");
          ObjectOutputStream object_out=new ObjectOutputStream(out);
          object_out.writeObject(成绩表);
          object_out.close();
          out.close();
         }
      catch(IOException e)
         {
         }
     } 
    gradeFile=file初级;
    setBounds(100,100,300,260);
    setVisible(true);
    addWindowListener(new WindowAdapter()
      {
        public void windowClosing(WindowEvent e)
         {
          System.exit(0);
         }
      });
    con=getContentPane(); 
    记忆测试板=new MemoryTestPane(block,icon,m,n,gradeFile);
    提示条=new JTextField("初级：您需要连续找出"+6+"个相同图标的方块"); 
    提示条.setEditable(false);
    提示条.setForeground(Color.red);
    con.add(记忆测试板,BorderLayout.CENTER);
    con.add(提示条,BorderLayout.SOUTH);
    con.validate();
    this.validate();
 }
 public void 给出级别测试(int 宽,int 高,File f)
 {    
    m=宽;
    n=高;
    图标个数=m;
    gradeFile=f;
    block=new Block[m*n];
    icon=new ImageIcon[图标个数];
       for(int i=0;i<icon.length;i++)
       {
         icon[i]=new ImageIcon("a"+i+".gif");
       } 
    for(int i=0;i<block.length;i++)
       {
         block[i]=new Block();
         block[i].设置关闭时的图标(new ImageIcon("关闭.gif"));
       }
    记忆测试板=new MemoryTestPane(block,icon,m,n,gradeFile);
    con.removeAll();
    con.add(记忆测试板,BorderLayout.CENTER);
    con.add(提示条,BorderLayout.SOUTH);
    con.validate();
    this.validate();
 }
 public void actionPerformed(ActionEvent event)
 { 
   if(event.getSource()==初级) 
       {  
         给出级别测试(5,6,file初级);
         setBounds(100,100,300,260);
         this.validate();
         提示条.setText("初级：您需要连续找出"+6+"个相同图标的方块");
       }
   if(event.getSource()==中级) 
       {
         给出级别测试(6,7,file中级);
         setBounds(100,100,340,280);
         this.validate();
         提示条.setText("中级：您需要连续找出"+7+"个相同图标的方块");
       }
   if(event.getSource()==高级) 
       {  
         给出级别测试(7,8,file高级);
         setBounds(100,100,360,300);
         this.validate();
         提示条.setText("高级：您需要连续找出"+8+"个相同图标的方块");
       }
   if(event.getSource()==高级排行榜)
       {
         显示成绩表对话框=new ShowRecord(this,file高级);
         显示成绩表对话框.setVisible(true);
       }
    if(event.getSource()==中级排行榜)
       {
         显示成绩表对话框=new ShowRecord(this,file中级);
         显示成绩表对话框.setVisible(true);
       }
     if(event.getSource()==初级排行榜)
       {
         显示成绩表对话框=new ShowRecord(this,file初级);
         显示成绩表对话框.setVisible(true);
       }
 } 

 public static void main(String args[])
  {
    new Memory();
  }
}