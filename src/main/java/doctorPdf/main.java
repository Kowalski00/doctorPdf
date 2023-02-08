package doctorPdf;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.forms.fields.PdfTextFormField;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfTextAnnotation;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.parser.listener.IPdfTextLocation;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.pdfcleanup.PdfCleaner;
import com.itextpdf.pdfcleanup.autosweep.CompositeCleanupStrategy;
import com.itextpdf.pdfcleanup.autosweep.RegexBasedCleanupStrategy;

import javax.swing.*;

public class main extends JFrame{
	
	static JPanel MainPanel;

	public static void main(String[] args) {
		
		main frame = new main();
		frame.setVisible(true);
		
		System.out.println("Finished");
	}
	
	public main() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 375);
		
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
	
	private void generatePdf() throws IOException{
		
		PdfReader reader = new PdfReader("src/main/resources/test.pdf");
	    PdfWriter writer = new PdfWriter("src/main/resources/test-fixed.pdf");
	    PdfDocument pdfDocument = new PdfDocument(reader, writer);
		
		CompositeCleanupStrategy strategy = new CompositeCleanupStrategy();
		strategy.add(new RegexBasedCleanupStrategy("DESENVOLVIMENTO DE SOFTWARES LTDA").setRedactionColor(ColorConstants.WHITE));
		PdfCleaner.autoSweepCleanUp(pdfDocument, strategy);

		for (IPdfTextLocation location : strategy.getResultantLocations()) {
		    PdfPage page = pdfDocument.getPage(location.getPageNumber() + 1);
		    PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamAfter(), page.getResources(), page.getDocument());
		    Canvas canvas = new Canvas(pdfCanvas, location.getRectangle());
		    canvas.add(new Paragraph("PDF Editor-Generator").setFontSize(8).setMarginTop(0f));
		}
	    
		pdfDocument.close();
		
	}

}

