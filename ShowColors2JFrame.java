
// Fig. 13.7: ShowColors2JFrame.java
// Choosing colors with JColorChooser.
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class ShowColors2JFrame extends JPanel {
    private final JButton changeColorJButton;
    private Color color = Color.LIGHT_GRAY;
    private final JPanel colorJPanel;

    public class MyGraphics1 extends JComponent {
        // private static final long serialVersionUID = 1L;

        // MyGraphics1() {
        //     setPreferredSize(new Dimension(500, 100));
        // }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            this.setBackground(color);
            // set new drawing color using integers
            g.setColor(new Color(255, 0, 0));
            g.fillRect(15, 25, 100, 20);
            g.drawString("Current RGB: " + g.getColor(), 130, 40);
        }
    }

    public class MyGraphics2 extends JComponent {
        // private static final long serialVersionUID = 1L;

        // MyGraphics2() {
        //     setPreferredSize(new Dimension(500, 100));
        // }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            this.setBackground(color);
            // set new drawing color using floats
            g.setColor(new Color(0.50f, 0.75f, 0.0f));
            g.fillRect(30, 50, 100, 20);
            g.drawString("Current RGB: " + g.getColor(), 130, 65);
        }
    }

    public class MyGraphics3 extends JComponent {
        // private static final long serialVersionUID = 1L;

        // MyGraphics3() {
        //     setPreferredSize(new Dimension(500, 100));
        // }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            this.setBackground(color);
            // set new drawing color using static Color objects
            g.setColor(Color.BLUE);
            g.fillRect(45, 75, 100, 20);
            g.drawString("Current RGB: " + g.getColor(), 130, 90);
        }
    }

    // set up GUI
    public ShowColors2JFrame() {
        super("Using JColorChooser");

        // create JPanel for display color
        colorJPanel = new JPanel();
        colorJPanel.add(new MyGraphics1());
        // colorJPanel.add(new MyGraphics2());
        // colorJPanel.add(new MyGraphics3());
        colorJPanel.setBackground(color);

        // set up changeColorJButton and register its event handler
        changeColorJButton = new JButton("Change Color");
        changeColorJButton.addActionListener(
                new ActionListener() // anonymous inner class
                {
                    // display JColorChooser when user clicks button
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        color = JColorChooser.showDialog(
                                ShowColors2JFrame.this, "Choose a color", color);

                        // set default color, if no color is returned
                        if (color == null)
                            color = Color.LIGHT_GRAY;

                        // change content pane's background color
                        colorJPanel.setBackground(color);
                    }
                } // end anonymous inner class
        ); // end call to addActionListener

        add(colorJPanel, BorderLayout.CENTER);
        add(changeColorJButton, BorderLayout.SOUTH);

        // pack();
        setSize(600, 400);
        setVisible(true);
    } // end ShowColor2JFrame constructor
} // end class ShowColors2JFrame

/**************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and *
 * Pearson Education, Inc. All Rights Reserved. *
 * *
 * DISCLAIMER: The authors and publisher of this book have used their *
 * best efforts in preparing the book. These efforts include the *
 * development, research, and testing of the theories and programs *
 * to determine their effectiveness. The authors and publisher make *
 * no warranty of any kind, expressed or implied, with regard to these *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or *
 * consequential damages in connection with, or arising out of, the *
 * furnishing, performance, or use of these programs. *
 *************************************************************************/
