package doctorPdf;

import java.util.List;

import javax.swing.JTextField;

public class InputData {
	
	private String selectedPath;
	private int fontSize;
	private List<JTextField> answers;
	
	public String getSelectedPath() {
		return selectedPath;
	}
	public void setSelectedPath(String selectedPath) {
		this.selectedPath = selectedPath;
	}
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	
	public List<JTextField> getAnswers() {
		return this.answers;
	}
	
	public void setAnswers(List<JTextField> answers) {
		this.answers = answers;
	}
}
