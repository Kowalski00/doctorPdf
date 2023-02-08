package doctorPdf;

import java.awt.Font;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelpPanel extends JPanel{

	public HelpPanel() {
		
		setLayout(null);
		
		JLabel label = new JLabel("Helperrrr");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(108, 33, 239, 31);
		add(label);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(193, 217, 141, 20);
		add(formattedTextField);
	}
}
