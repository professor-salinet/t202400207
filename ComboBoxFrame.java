// Fig. 12.21: ComboBoxFrame.java
// JComboBox that displays a list of image names.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ComboBoxFrame extends JFrame 
{
   private final JComboBox<String> imagesJComboBox; // hold icon names
   private final JLabel label; // displays selected icon

   private static final String[] names = 
      {"Gustavo", "Gabriel", "Lucas", "Matheus", "Daniel", "Julio",
         "Angelo", "Enrique", "Germano", "Farias", "Douglas", "Rafael",
         "Felipe"};

   // ComboBoxFrame constructor adds JComboBox to JFrame
   public ComboBoxFrame()
   {
      super("Testing JComboBox");
      setLayout(new FlowLayout()); // set frame layout     

      imagesJComboBox = new JComboBox<String>(names); // set up JComboBox
      imagesJComboBox.setMaximumRowCount(7); // display three rows

      add(imagesJComboBox); // add combobox to JFrame
      label = new JLabel(names[0]); // display first icon
      add(label); // add label to JFrame
      imagesJComboBox.addItemListener(event -> { 
            // System.out.println("event.getStateChange(): " + event.getStateChange());
            // System.out.println("ItemEvent.SELECTED: " + ItemEvent.SELECTED);
           if (event.getStateChange() /* 1 */ == ItemEvent.SELECTED /* 1 */)
              label.setText(names[imagesJComboBox.getSelectedIndex()]);
           }); // end call to addItemListener
   } // end ComboBoxFrame constructor
} // end class ComboBoxFrame


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
