package doctorPdf;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextField;

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

public class GeneratorService {

	public GeneratorService() {
	}
	
	public void generatePdfs(InputData inputs) throws FileNotFoundException {
		
		PdfReader reader;
		PdfWriter writer;
		PdfDocument pdfDocument;

		Map<String, String> answerByKeyword = new HashMap<String, String>(){{}};
		
		try {
			
			reader = new PdfReader("src/main/resources/test2.pdf");
			
			String patientName = answerByKeyword.get("!name");
			String pdfName = (patientName == null || patientName.isBlank()) ? "/test.pdf" : "/test-" + patientName + ".pdf";
			
			writer = new PdfWriter(inputs.getSelectedPath() + pdfName);
			
			List<JTextField> answerFields = inputs.getAnswers();
			
			for(JTextField answerField : answerFields) {
				answerByKeyword.put (answerField.getName(), answerField.getText() );
			}
			
		    pdfDocument = new PdfDocument(reader, writer);
			
			CompositeCleanupStrategy strategy = new CompositeCleanupStrategy();
			for(JTextField answerField : answerFields) {
				strategy.add(new RegexBasedCleanupStrategy( answerField.getName() ).setRedactionColor(ColorConstants.WHITE));
			}
			PdfCleaner.autoSweepCleanUp(pdfDocument, strategy);

			for (IPdfTextLocation location : strategy.getResultantLocations()) {
			    PdfPage page = pdfDocument.getPage(location.getPageNumber() + 1);
			    PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamAfter(), page.getResources(), page.getDocument());
			    Canvas canvas = new Canvas(pdfCanvas, location.getRectangle());
			    
			    String answer = answerByKeyword.get( location.getText() );
			    
		    	canvas.add(
				    	new Paragraph( answer )
				    		.setFontSize( inputs.getFontSize() )
				    		.setMarginTop(0f)
				    );
			    
			    canvas.close();
			}
		    
			pdfDocument.close();
			
		} catch (IOException e) {
			System.out.println("[ error : "+ e.getMessage() + " - " + e.getStackTrace() + " ]");
		}
	    
	}
}
