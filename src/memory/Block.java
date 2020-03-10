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
public class Block extends JButton implements ActionListener
{
 ImageIcon 翻开时的图标=null,关闭时的图标=null;
 public Block()
  {
    addActionListener(this);
  } 
 public ImageIcon 获取翻开时的图标()
  {
    return 翻开时的图标;
  }  
 public ImageIcon 获取关闭时的图标()
  {
    return 关闭时的图标;
  }  
 public void 设置翻开时的图标(ImageIcon icon)
  {
    翻开时的图标=icon;
  }
 public void 设置关闭时的图标(ImageIcon icon)
  {
    关闭时的图标=icon;
  }
 public void 设置图标(ImageIcon icon)
  {
    setIcon(icon);    
  }
 public void actionPerformed(ActionEvent e)
  {
    this.setIcon(翻开时的图标);
  }
}