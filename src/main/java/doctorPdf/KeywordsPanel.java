package doctorPdf;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class KeywordsPanel extends JPanel{
	
	private static final long serialVersionUID = 2460322863048039115L;
	
	private static final int initialXPosition = main.windowWidth / 8;
	private static final int initialYPosition = 25;
	private static final int tableWidth = (main.windowWidth * 6/8);
	private static final int tableHeigth = (main.windowHeight - 300);
	
	private static final String columnsLabels[]={"Título","Palavra-Chave"};
	
	JFrame f;

	public KeywordsPanel() {
		
		setLayout(null);
		
	    String data[][]={ {"Amit","670000"},{"Jai","780000"},{"Sachin","700000"},{"Jai","780000"},{"Sachin","700000"},{"Jai","780000"},{"Sachin","700000"},{"Jai","780000"}};
	    
	    final JTable keywordsTable = new JTable(data,columnsLabels);
	    keywordsTable.setCellSelectionEnabled(true);
	    
        ListSelectionModel cellSelection = keywordsTable.getSelectionModel();
        cellSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        cellSelection.addListSelectionListener(new ListSelectionListener() {
        	
            public void valueChanged(ListSelectionEvent event) {
            	
            	String Data = null;
            	int[] row = keywordsTable.getSelectedRows();
            	int[] columns = keywordsTable.getSelectedColumns();
            	
            	for (int i = 0; i < row.length; i++) {
            		for (int j = 0; j < columns.length; j++) {
            			Data = (String) keywordsTable.getValueAt(row[i], columns[j]);
            		}
            	}
            	System.out.println("Table element selected is: " + Data);
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(keywordsTable);
	    
	    scrollPane.setBounds(initialXPosition, initialYPosition, tableWidth, tableHeigth);
	    
	    add(scrollPane);
	}
}
