package doctorPdf;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GeneratorPanel extends JPanel{

	private static final long serialVersionUID = 2067580022728473760L;

	public GeneratorPanel() {
		
		setLayout(null);
		
		JLabel label = new JLabel("Generator");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(108, 33, 239, 31);
		add(label);
	}
}
