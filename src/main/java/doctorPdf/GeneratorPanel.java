package doctorPdf;

import java.awt.Font;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GeneratorPanel extends JPanel{

	public GeneratorPanel() {
		
		setLayout(null);
		
		JLabel label = new JLabel("Generator");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(108, 33, 239, 31);
		add(label);
	}
}
