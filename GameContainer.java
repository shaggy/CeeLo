import javax.swing.*;
import java.awt.*;

public class GameContainer extends JPanel {
   private JLabel vs;
   private GamePanel innerLabel;
   private String playerName;

   GameContainer() {
      playerName = JOptionPane.showInputDialog("What's your name?");
      vs = new JLabel(playerName + " vs AI");
      innerLabel = new GamePanel();
      
      add(vs);
      add(innerLabel); 
      
}

String getPlayerName() {
      return playerName;
}

GamePanel getGamePanel() {
   return innerLabel;

}


}