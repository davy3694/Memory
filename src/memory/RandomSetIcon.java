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
import java.util.Vector;
class Point
{ 
  int x;
  Point(int i)
  {
    x=i;
  }
  public int getInt()
  {
    return x;
  }
}
public class  RandomSetIcon 
 {
  public RandomSetIcon()
    {
    }
  public void 随机设置图标(Block[] block,ImageIcon icon[])
    {
      Vector vector=new Vector();                   
      int n=icon.length;
     for(int i=0;i<block.length;i++)                
       { 
         int x=i%n;
         Point p=new Point(x);
         vector.addElement(p);
       } 
     for(int i=0;i<block.length;i++)
       {
         int m=(int)(Math.random()*vector.size()); 
         Point p=(Point)vector.elementAt(m);       
         int x=p.getInt();                         
         block[i].设置翻开时的图标(icon[x]); 
         vector.remove(m);           
       }
    } 
 } 

