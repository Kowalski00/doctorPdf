package doctorPdf;

import java.io.FileNotFoundException;
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

public class GeneratorService {

	public GeneratorService() {
	}
	
	public void generatePdfs(String patientName, String selectedPath) throws FileNotFoundException {
		
		PdfReader reader;
		PdfWriter writer;
		PdfDocument pdfDocument;
		
		try {
			
			reader = new PdfReader("src/main/resources/test.pdf");
			writer = new PdfWriter(selectedPath + "/test-" + patientName + ".pdf");
			
		    pdfDocument = new PdfDocument(reader, writer);
			
			CompositeCleanupStrategy strategy = new CompositeCleanupStrategy();
			strategy.add(new RegexBasedCleanupStrategy("DESENVOLVIMENTO DE SOFTWARES LTDA").setRedactionColor(ColorConstants.WHITE));
			PdfCleaner.autoSweepCleanUp(pdfDocument, strategy);

			for (IPdfTextLocation location : strategy.getResultantLocations()) {
			    PdfPage page = pdfDocument.getPage(location.getPageNumber() + 1);
			    PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamAfter(), page.getResources(), page.getDocument());
			    Canvas canvas = new Canvas(pdfCanvas, location.getRectangle());
			    canvas.add(new Paragraph( patientName ).setFontSize(8).setMarginTop(0f));
			    canvas.close();
			}
		    
			pdfDocument.close();
			
		} catch (IOException e) {
			System.out.println("[ error : "+ e.getMessage() + " - " + e.getStackTrace() + " ]");
		}
	    
	}
}
