package doctorPdf;

import java.awt.Font;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelpPanel extends JPanel{

	private static final long serialVersionUID = -8703837789556558889L;

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
