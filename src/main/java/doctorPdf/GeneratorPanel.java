package doctorPdf;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GeneratorPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 2067580022728473760L;
	
	private static final int initialXPosition = 25;
	private static final int firstQuestionLabelYPosition = 50;
	private static final int firstInputYPosition = 75;
	private static final String[] fontSize = {"4","5","6","7","8","9","10","11","12","14","16","18"};
	
	private JTextField inputNameTextField;
	private JComboBox comboBoxFontSize;
	private JButton generateButton, pathSelectionButton;
	private JTextField selectedPathLabel;
	
	private String selectedPath;

	public GeneratorPanel() {
		
		setLayout(null);
		
		KeywordsPanel keywordsPanel = new KeywordsPanel();
		String[][] keywordsData = keywordsPanel.getDataCsv();
		
		this.createForm(keywordsData);
		
//		JLabel fontLabel = new JLabel("Selecione o tamanho da fonte: ");
//		fontLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		fontLabel.setBounds(initialXPosition, 120, 350, 30);
//		add(fontLabel);
//		
//		comboBoxFontSize = new JComboBox( fontSize );
//		comboBoxFontSize.setBounds(200,125,50,20);
//		add(comboBoxFontSize);
		
//		JLabel pathLabel = new JLabel("Escolha o destino dos arquivos:");
//		pathLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		pathLabel.setBounds(initialXPosition, 180, 350, 30);
//		add(pathLabel);
//		
//		selectedPathLabel = new JTextField();  
//		selectedPathLabel.setBounds(200, 180, 300, 30);  
//		selectedPathLabel.setEditable(false);
//		add(selectedPathLabel);
//		
//		pathSelectionButton = new JButton("Selecione...");
//		pathSelectionButton.setBounds(initialXPosition,210,100,20);
//		selectedPathLabel.setVisible(false);
//		add(pathSelectionButton);
		
		generateButton = new JButton("Gerar");
		generateButton.setBounds(initialXPosition,(main.windowHeight - 100),100,20);
		add(generateButton);
		
		generateButton.addActionListener(this);
//		pathSelectionButton.addActionListener(this);
		 
	}

	public void actionPerformed(ActionEvent e) {
		
		InputData formData = new InputData();
		
		if(e.getSource() == generateButton) {
		
			String patientName = inputNameTextField.getText();
			String fontSize = (String) comboBoxFontSize.getItemAt( comboBoxFontSize.getSelectedIndex() );  
			
			if( selectedPath == null || selectedPath.isBlank() ) {
				System.out.println("selectedPath not selected");
				return;
			}
			
			if( patientName == null || patientName.isBlank() ) {
				System.out.println("patientName is blank");
				return;
			}
			
			formData.setPatientFullName( patientName );
			formData.setFontSize( Integer.parseInt( fontSize ) );
			formData.setSelectedPath( selectedPath );
			
			GeneratorService service = new GeneratorService();
			try {
				
				service.generatePdfs( formData );
				
			} catch (FileNotFoundException error) {
				error.printStackTrace();
			}
		}
		
		if(e.getSource() == pathSelectionButton) {
			
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
			fileChooser.showSaveDialog(null);
	        
	        selectedPath = fileChooser.getSelectedFile().toString();
	        
	        selectedPathLabel.setText(selectedPath);
	        selectedPathLabel.setVisible(true);

	        System.out.println( fileChooser.getSelectedFile() ); // f.getSelectedFile() is the correct function
		}
	}
	
	private void createForm(String[][] keywordsData) {
		
		Integer questionHeights = firstQuestionLabelYPosition;
		Integer inputHeights = firstInputYPosition;
		
		for (String[] strings : keywordsData) {
			System.out.println("strings[0] : "+strings[0]);
			System.out.println("strings[1] : "+strings[1]);
			
			JLabel inputLabel = new JLabel( strings[0] );
			inputLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			inputLabel.setBounds(initialXPosition, questionHeights, 350, 30);
			add(inputLabel);
			
			inputNameTextField = new JTextField();  
			inputNameTextField.setBounds(initialXPosition,inputHeights,350,20);
			add(inputNameTextField);
			
			questionHeights += 60;
			inputHeights += 60;
		}
	}
}
