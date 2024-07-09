// Fig. 13.5: ColorJPanel.java
// Changing drawing colors.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ColorJPanel extends JPanel 
{
    private JButton changeColorJButton;
    private Color color = Color.LIGHT_GRAY;

    // draw rectangles and Strings in different colors
   @Override
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g); 
      this.setBackground(Color.WHITE);

      // set new drawing color using integers
      g.setColor(new Color(255, 0, 0));
      g.fillRect(15, 25, 100, 20);
      g.drawString("Current RGB: " + g.getColor(), 130, 40);

      // set new drawing color using floats
      g.setColor(new Color(0.50f, 0.75f, 0.0f));
      g.fillRect(30, 50, 100, 20);
      g.drawString("Current RGB: " + g.getColor(), 130, 65);

      // set new drawing color using static Color objects
      g.setColor(Color.BLUE);
      g.fillRect(45, 75, 100, 20);
      g.drawString("Current RGB: " + g.getColor(), 130, 90);

      // set up changeColorJButton and register its event handler
      changeColorJButton = new JButton("Change Color");
      changeColorJButton.addActionListener(
         new ActionListener() // anonymous inner class
         {
            // display JColorChooser when user clicks button
            @Override
            public void actionPerformed(ActionEvent event)
            {
               color = JColorChooser.showDialog(
                  ColorJPanel.this, "Choose a color", color);

               // set default color, if no color is returned 
               if (color == null)
                  color = Color.LIGHT_GRAY;

               // change content pane's background color
               g.setColor(color);
            } 
         } // end anonymous inner class
      ); // end call to addActionListener

      add(changeColorJButton, BorderLayout.SOUTH);
    //   setSize(400, 180);
    //   setVisible(true);
    } 
} // end class ColorJPanel

/**************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
