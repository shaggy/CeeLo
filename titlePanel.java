import java.awt.*;
import javax.swing.*;

class titlePanel extends JPanel {
   private JLabel title;
   
   titlePanel() {
      title = new JLabel( "The Corner");
      title.setFont(new Font("", Font.BOLD, 20));
      
      add(title);
   
   }




}