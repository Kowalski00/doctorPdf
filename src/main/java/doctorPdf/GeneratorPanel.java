package doctorPdf;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GeneratorPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 2067580022728473760L;
	
	private JTextField inputNameTextField;
	private JButton generateButton, pathSelectionButton;
	private JLabel selectedPathLabel;
	
	private String selectedPath;

	public GeneratorPanel() {
		
		setLayout(null);
		
		JLabel inputLabel = new JLabel("Insira o nome completo do Paciente:");
		inputLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		inputLabel.setBounds(25, 50, 350, 30);
		add(inputLabel);
		
		inputNameTextField = new JTextField();  
		inputNameTextField.setBounds(25,90,350,20);
		add(inputNameTextField);
		
		JLabel pathLabel = new JLabel("Escolha o destino dos arquivos:");
		pathLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pathLabel.setBounds(25, 150, 350, 30);
		add(pathLabel);
		
		selectedPathLabel = new JLabel();
		selectedPathLabel.setFont(new Font("Tahoma", Font.ITALIC, 12));
		selectedPathLabel.setBounds(25, 150, 375, 30);
		add(selectedPathLabel);
		
		pathSelectionButton = new JButton("Selecione...");
		pathSelectionButton.setBounds(25,180,100,20);
		selectedPathLabel.setVisible(false);
		add(pathSelectionButton);
		
		generateButton = new JButton("Gerar");
		generateButton.setBounds(25,250,100,20);
		add(generateButton);
		
		generateButton.addActionListener(this);
		pathSelectionButton.addActionListener(this);
		 
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == generateButton) {
		
			String patientName = inputNameTextField.getText();
			System.out.println("patientName : " +patientName);
			
			if( selectedPath == null || selectedPath.isBlank() ) {
				System.out.println("selectedPath not selected");
				return;
			}
			
			if( patientName == null || patientName.isBlank() ) {
				System.out.println("patientName is blank");
				return;
			}
			
			GeneratorService service = new GeneratorService();
			try {
				
				service.generatePdfs(patientName, selectedPath);
				
			} catch (FileNotFoundException error) {
				error.printStackTrace();
			}
		}
		
		if(e.getSource() == pathSelectionButton) {
			
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
			fileChooser.showSaveDialog(null);
	        
	        selectedPath = fileChooser.getSelectedFile().toString();
	        
	        selectedPathLabel.setName(selectedPath);
	        selectedPathLabel.setVisible(true);

	        System.out.println( fileChooser.getSelectedFile() ); // f.getSelectedFile() is the correct function
		}
	}
}
