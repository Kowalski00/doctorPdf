package doctorPdf;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainPanel extends JPanel{
	
	private static final long serialVersionUID = 7759048602308033969L;

	public MainPanel() {
		setLayout(null);
		
		JLabel lblPanelMain = new JLabel("Panel Main");
		lblPanelMain.setHorizontalAlignment(SwingConstants.CENTER);
		lblPanelMain.setBounds(105, 110, 239, 31);
		add(lblPanelMain);
	}
}
