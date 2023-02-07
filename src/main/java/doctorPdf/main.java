package doctorPdf;

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

public class main {

	public static void main(String[] args) throws IOException {
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
		
		System.out.println("Finished");
	}
	
	public static void addContentToDocument(PdfDocument pdfDocument) {
		
		PdfFormField personal = PdfFormField.createEmptyField(pdfDocument);
		personal.setFieldName("information");
		PdfTextFormField name = PdfFormField.createText(pdfDocument, new Rectangle(35, 400, 100, 30), "name", "");
		personal.addKid(name);
		PdfAcroForm.getAcroForm(pdfDocument, true).addField(personal, pdfDocument.getFirstPage());
		
		PdfAnnotation ann = new PdfTextAnnotation(new Rectangle(40, 435, 0, 0)).setTitle(new PdfString("name")).setContents("Your name");
		pdfDocument.getPage(2).addAnnotation(ann);
		
		
	}

}

