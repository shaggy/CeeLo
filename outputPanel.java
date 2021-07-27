import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class outputPanel extends JPanel {
       private JTextArea output;
       private JScrollPane pane;
      
      outputPanel() {
         
         
         
         output = new JTextArea(20, 35);
         output.setEditable(false);
         output.setText("Enter bet amount and game will begin...\n");
         output.setLineWrap(true);
         
         pane = new JScrollPane(output);
         
         
         add(pane);
      
      }

JTextArea getOutputArea() {

   return output;

}


}