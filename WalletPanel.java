import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class WalletPanel extends JPanel {
      private JTextField amount;
      private JLabel walletLabel;
      
      WalletPanel() {
         setLayout(new FlowLayout());
         
         amount = new JTextField(7);
         amount.setEditable(false);
         amount.setText("100");
         
         walletLabel = new JLabel("Wallet: ");
         
         add(walletLabel);
         add(amount);
         
}

void setWallet(double x) {
   amount.setText(String.valueOf(x));

}

double getWalletAmount() {
   return Double.parseDouble(amount.getText());

}

}