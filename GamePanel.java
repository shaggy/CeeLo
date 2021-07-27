import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 

public class GamePanel extends JPanel {

        private WalletPanel currentWallet;
        private outputPanel gameResults;
   
      
      
        GamePanel() {
        
         setLayout(new BorderLayout());
        
         currentWallet = new WalletPanel();
         gameResults = new outputPanel();
                  
         add(currentWallet, BorderLayout.NORTH);
         add(gameResults, BorderLayout.CENTER);
        
}

outputPanel getGameResultsPanel() {

   return gameResults;

}

WalletPanel getWalletPanel() {

   return currentWallet;
}
}