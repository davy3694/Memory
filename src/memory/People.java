/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package memory;

/**
 *
 * @author Administrator
 */
public class People implements java.io.Serializable
{
  String name=null;
  int time=0;                          
 public People(String name,int t)
  {
    this.name=name;
    time=t;
  }
 public int getTime()
  {
    return time;
  }
 public String getName()
  {
   return name;
  }
}
