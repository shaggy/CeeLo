import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class CeeLoGUI extends JFrame {
   //panels
   private GameContainer gameContain;
   private JPanel buttonPanel;
   private titlePanel heading;
  
  //jframe size
   private final int WIDTH;
   private final int HEIGHT;
  //ceelo stuff
   private CeeLo game;
   private Player human;
   private Player computer;
   private double bet;
   private int scoreHuman;
   private int scoreComputer;
   
   
   private JButton betButton;
   private JTextField betAmount;
   private JButton exitButton;
     //construct
      CeeLoGUI() {
         WIDTH = 1000;
         HEIGHT = 500;
         
         // Display a title.     
         setTitle("CeeLo");     
         setSize(WIDTH,HEIGHT);
    
         // Specify an action for the close button.     
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
    
         // Create a BorderLayout manager.     
         setLayout(new BorderLayout());   
      
         //create panels
         gameContain = new GameContainer();
         heading = new titlePanel();
         buildButtonPanel();   
          
         //create players as per previous console CeeLo game flow pre loop, and ceelo instance
         human = new Player();
         human.setName(gameContain.getPlayerName());
         computer = new Player();
         computer.setName("Computer");
         
         game = new CeeLo();
        
         // Add the components to the content pane.     
         add(heading, BorderLayout.NORTH);      
         add(gameContain, BorderLayout.CENTER);         
         add(buttonPanel, BorderLayout.SOUTH);     
    
         // Pack the contents of the window and display it.     
         pack();
         setLocationRelativeTo(null);//to center 
         setResizable(false);
    
         setVisible(true);    
      
}

void buildButtonPanel() {

   buttonPanel = new JPanel();
   
   betButton = new JButton("Bet");
   betAmount = new JTextField(7);
   betButton.addActionListener(new BetListener());
  
   exitButton = new JButton("Exit");
   exitButton.addActionListener(new ExitListener());
  
   buttonPanel.add(exitButton);
   buttonPanel.add(betButton);
   buttonPanel.add(betAmount); 
}
private class ExitListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
         if(e.getSource() == exitButton) {
            
           System.exit(0);
         }
}
}
//called from bet action listener
public void startGame() {
      bet = Double.parseDouble(betAmount.getText());
      
      //clear output
      getOutput().setText("");
      
      human.playerRolls();
      computer.playerRolls();
      
      boolean validRoll = true;
      
      getOutput().append(human.getName() + " rolls...\n\n");
      
      for(int i = 0; i < 3; i++) {
          getOutput().append(String.valueOf(game.showRoll(human)[i]+ " ")); 
      }
      
          getOutput().append("\n\n");
      
          getOutput().append(computer.getName() + " rolls...\n\n");

      for(int i = 0; i < 3; i++) {
          getOutput().append(String.valueOf(game.showRoll(computer)[i]+ " ")); 
      }
      //continue the game
      getOutput().append("\n");

      scoreHuman = game.gameLogic(human.getDieValues(), human);
      
      //validate player score
      while(scoreHuman == 0) {
         
         getOutput().append("\n" + human.getName() + " got nothin...\nRe-rolling...\n\n");
         human.playerRolls();
         
         for(int i = 0; i < 3; i++) {
         getOutput().append( String.valueOf(game.showRoll(human)[i]+ " ")); 
      }

         scoreHuman = game.gameLogic(human.getDieValues(), human);
         getOutput().append("\n\n");

    }
      scoreComputer = game.gameLogic(computer.getDieValues(), computer);
      //validate computer score
      while(scoreComputer == 0) {
         
         getOutput().append("\n" + computer.getName() + " got nothin... \nRe-rolling...\n\n");
         computer.playerRolls();
         
         for(int i = 0; i < 3; i++) {
         getOutput().append( String.valueOf(game.showRoll(computer)[i]+ " ")); 
      }
         scoreComputer = game.gameLogic(computer.getDieValues(), computer);
         getOutput().append("\n\n");
  }

      
        String victor = game.calculateWinner(scoreHuman,scoreComputer, human, computer);
        getOutput().append("\n\n The winner is: " + victor);
        
        double wallet;
        
        wallet = game.adjustWallet(victor, getMoney(), bet, human, computer);
        
        gameContain.getGamePanel().getWalletPanel().setWallet(wallet); 
        
        if(getMoney() <= 0) {
            JOptionPane.showMessageDialog(null, "Damn, you lost all your money! Better luck next time.");
        }
         

}

double getMoney() {
   
   return gameContain.getGamePanel().getWalletPanel().getWalletAmount();

}
JTextArea getOutput() {
   
   return  gameContain.getGamePanel().getGameResultsPanel().getOutputArea();

}

private class BetListener implements ActionListener {
   public void actionPerformed(ActionEvent e) {
     System.out.println("Bet button pressed");
     
     if(getMoney() <= 0) {
         
         JOptionPane.showMessageDialog(null,"No money in wallet, exiting game.");
        
         System.exit(0);
     }
     
     if(Double.parseDouble(betAmount.getText()) > getMoney()){
     
         JOptionPane.showMessageDialog(null, "You don't have enough money to bet that amount...");
         System.out.println("Wants to bet more than they have");
        //return nothing, cancel      
      return;
     }
     startGame();
   
   }
}
public static void main(String[] args) {
   new CeeLoGUI();
}

}