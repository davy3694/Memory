/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package memory;

/**
 *
 * @author Administrator
 */ 
import javax.swing.*; 
import java.awt.event.*;
import java.awt.*;
import java.util.LinkedList;
import java.io.*;

public class MemoryTestPane extends JPanel implements ActionListener
{
  RandomSetIcon  排列图标=null;
  Block block[];                         
  ImageIcon icon[];                      
  LinkedList  listIocn=null,             
              listBlock=null;            
  int 行数=0,列数=0;
  int success=0;                         
                                         
  int time=0;                            
  javax.swing.Timer 计时器=null;         
  JTextField 显示时间=null;
  File gradeFile=null;
  boolean 计时器是否启动=false;
  public  MemoryTestPane(Block[] block,ImageIcon[] icon,int m,int n,File f)
   {
     排列图标=new RandomSetIcon();
     this.block=block;
     this.icon=icon;
     行数=m;
     列数=n;
     gradeFile=f;
     listIocn=new LinkedList();
     listBlock=new LinkedList();
     setLayout(new BorderLayout());
     JPanel center=new JPanel();
     center.setLayout(new GridLayout(行数,列数));
     for(int i=0;i<block.length;i++)
        {
          center.add(block[i]);
          block[i].addActionListener(this);
        }
     JPanel south=new JPanel();
     显示时间=new JTextField(12);
     显示时间.setEditable(false);
     显示时间.setForeground(Color.red);
     south.add(显示时间);
     add(center,BorderLayout.CENTER);
     add(south,BorderLayout.SOUTH); 
     排列图标.随机设置图标(block,icon);
     计时器=new Timer(1000,this);                              
     计时器是否启动=false;
   }
  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource() instanceof Block)
    { 
        Block 方块=(Block)e.getSource();
        ImageIcon 翻开时的图标=方块.获取翻开时的图标();
        方块.设置图标(翻开时的图标);
        if(listIocn.size()==0)                                  
         {
          listIocn.add(翻开时的图标);
          listBlock.add(方块);
          success=1;                                            
         }
        else
         {
           ImageIcon temp=(ImageIcon)listIocn.getLast();        
           if(temp==翻开时的图标&&!(listBlock.contains(方块)))  
              {
                success=success+1;                              
                listIocn.add(翻开时的图标);                     
                listBlock.add(方块);
                if(success==列数) 
                  {
                    for(int i=0;i<block.length;i++)           
                     {
                       block[i].setEnabled(false);
                     }
                    for(int j=0;j<listBlock.size();j++)
                     {
                       Block b=(Block)listBlock.get(j);
                       b.setDisabledIcon(b.获取翻开时的图标());
                     } 
                    计时器.stop();
                    Record record=new Record(gradeFile);
                    record.setTime(time);
                    record.setVisible(true);
                  }    
               }
           else if((temp!=翻开时的图标)&&(!(listBlock.contains(方块)))) 
               {
                listIocn.clear();                          
                listBlock.clear();
                listIocn.add(翻开时的图标);                
                listBlock.add(方块);
                success=1;                                 
                for(int i=0;i<block.length;i++)            
                  {
                    if(方块!=block[i])
                       {
                         block[i].设置图标(block[i].获取关闭时的图标());
                       }
                 }                
               }  
         }
       
       if(计时器是否启动==false)
         {
          time=0;
          计时器.start();                                   
          计时器是否启动=true;                              
         }
    }
   if(e.getSource()==计时器)
    {
      time=time+1;
      显示时间.setText("您的用时:"+time+"秒");
    }
     
 }
}