package doctorPdf;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.parser.listener.IPdfTextLocation;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.pdfcleanup.PdfCleaner;
import com.itextpdf.pdfcleanup.autosweep.CompositeCleanupStrategy;
import com.itextpdf.pdfcleanup.autosweep.RegexBasedCleanupStrategy;

import javax.swing.*;

public class main extends JFrame{
	
	private static final long serialVersionUID = 8552414523972247073L;
	protected static final int windowHeight = 450;
	protected static final int windowWidth = 750;
	private static final int windowInitialPosition = 100;
	
	static JPanel MainPanel;

	public static void main(String[] args) {
		
		main frame = new main();
		frame.setVisible(true);
		
		System.out.println("Finished");
	}
	
	public main() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(windowInitialPosition, windowInitialPosition, windowWidth, windowHeight);
		
		setTitle("PDF Generator");
		
		getContentPane().add(new MainPanel());
		
		JMenuBar menuBar = new JMenuBar();
        
		JMenuItem menuMain = new JMenuItem("Gerador");
		JMenuItem menuHelper = new JMenuItem("Ajuda");
        
        menuMain.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		GeneratorPanel genPanel = new GeneratorPanel();
        		genPanel.setBorder(BorderFactory.createTitledBorder("Gerador"));
        		getContentPane().removeAll();
        		getContentPane().add(genPanel, BorderLayout.CENTER);
        		getContentPane().doLayout();
    		}
        });
        
        menuHelper.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		HelpPanel helpPanel = new HelpPanel();
        		helpPanel.setBorder(BorderFactory.createTitledBorder("Ajuda"));
        		getContentPane().removeAll();
        		getContentPane().add(helpPanel, BorderLayout.CENTER);
        		getContentPane().doLayout();
    		}
        });
        
        menuBar.add(menuMain);
        menuBar.add(menuHelper);
        setJMenuBar(menuBar);
        
	}
}

