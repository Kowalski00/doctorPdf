package doctorPdf;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	
	private List<JTextField> answerFields = new ArrayList<JTextField>();
	private JComboBox comboBoxFontSize;
	private JButton generateButton, pathSelectionButton;
	private JTextField selectedPathLabel;
	
	private String selectedPath;

	public GeneratorPanel() {
		
		setLayout(null);
		
		KeywordsPanel keywordsPanel = new KeywordsPanel();
		String[][] keywordsData = keywordsPanel.getDataCsv();
		
		this.createForm(keywordsData);
		
		this.createTargetFileInput();
		
//		JLabel fontLabel = new JLabel("Selecione o tamanho da fonte: ");
//		fontLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		fontLabel.setBounds(initialXPosition, 120, 350, 30);
//		add(fontLabel);
//		
//		comboBoxFontSize = new JComboBox( fontSize );
//		comboBoxFontSize.setBounds(200,125,50,20);
//		add(comboBoxFontSize);
		
		generateButton = new JButton("Gerar");
		generateButton.setBounds(initialXPosition,(main.windowHeight - 100),100,20);
		add(generateButton);
		
		generateButton.addActionListener(this);
		 
	}

	public void actionPerformed(ActionEvent e) {
		
		InputData formData = new InputData();
		
		if(e.getSource() == generateButton) {
		
//			String fontSize = (String) comboBoxFontSize.getItemAt( comboBoxFontSize.getSelectedIndex() );  
			
			if( selectedPath == null || selectedPath.isBlank() ) {
				System.out.println("selectedPath not selected");
				return;
			}
			
			formData.setFontSize( 8 );
//			formData.setFontSize( Integer.parseInt( fontSize ) );
			formData.setSelectedPath( selectedPath );
			formData.setAnswers(this.answerFields);
			
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
		Integer indexAnswer = 0;
		
		for (String[] strings : keywordsData) {
			
			String question = strings[0];
			String keyword = strings[1];
			
			JLabel inputLabel = new JLabel( question );
			inputLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			inputLabel.setBounds(initialXPosition, questionHeights, 350, 30);
			add(inputLabel);
			
			JTextField inputField = new JTextField();
			inputField.setName( keyword );
			inputField.setBounds(initialXPosition,inputHeights,350,20);
			add(inputField);
			this.answerFields.add(inputField);
			
			questionHeights += 60;
			inputHeights += 60;
			indexAnswer++;
		}
	}
	
	private void createTargetFileInput() {
		
		JLabel pathLabel = new JLabel("Escolha o destino dos arquivos:");
		pathLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pathLabel.setBounds(initialXPosition, (main.windowHeight - 200), 350, 30);
		add(pathLabel);
		
		selectedPathLabel = new JTextField();  
		selectedPathLabel.setBounds(200, (main.windowHeight - 200), 300, 30);  
		selectedPathLabel.setEditable(false);
		add(selectedPathLabel);
		
		pathSelectionButton = new JButton("Selecione...");
		pathSelectionButton.setBounds(initialXPosition,(main.windowHeight - 170),100,20);
		selectedPathLabel.setVisible(false);
		add(pathSelectionButton);

		pathSelectionButton.addActionListener(this);
	}
}
