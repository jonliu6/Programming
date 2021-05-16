import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1CFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.io.IOException;

public class BlankGuitarTabSheet {
    private PDDocument pdfDoc = null;
    private PDPage pg = null;
    private PDPageContentStream pgContent = null;

    private final static PDRectangle DEFAULT_PAGE_SIZE = PDRectangle.A4;
    private final static float DEFAULT_PAGE_MARGIN = 20.0f;
    private final static float DEFAULT_FONT_SIZE = 12.0f;
    private final static float DEFAULT_LINE_THICKNESS = 0.5f;
    private final static PDFont DEFAULT_FONT = PDType1Font.COURIER;
    private final static Color DEFAULT_COLOR = Color.BLACK;
    private final static int DEFAULT_NUMBER_OF_LINES = 6; // number of strings of guitar tab
    private final static int DEFAULT_VERTICAL_LINES_PER_SECTION = 4; // number of vertical lines per section
    private final static float DEFAULT_LINE_SPACE = 10.0f;

    public static void main(String[] args) {
        BlankGuitarTabSheet tabSheet = new BlankGuitarTabSheet();
        tabSheet.createSheet(BlankGuitarTabSheet.class.getName() + ".pdf");
    }

    public void createSheet(String fileName) {
        pdfDoc = new PDDocument();
        pg = new PDPage(DEFAULT_PAGE_SIZE);
        pdfDoc.addPage(pg);

        try {
            pgContent = new PDPageContentStream(pdfDoc, pg);
            pgContent.setFont(DEFAULT_FONT, DEFAULT_FONT_SIZE);
            pgContent.setLineWidth(DEFAULT_LINE_THICKNESS);
            pgContent.setStrokingColor(DEFAULT_COLOR);

            float contentHeight = DEFAULT_PAGE_SIZE.getHeight() - 2 * DEFAULT_PAGE_MARGIN;
            float contentWidth = DEFAULT_PAGE_SIZE.getWidth() - 2 * DEFAULT_PAGE_MARGIN;
            int numOfSections = (int) (contentHeight / (DEFAULT_NUMBER_OF_LINES * DEFAULT_LINE_SPACE + 2 * DEFAULT_LINE_SPACE));
            float startX = DEFAULT_PAGE_MARGIN;
            float endX = DEFAULT_PAGE_SIZE.getWidth() - DEFAULT_PAGE_MARGIN;
            float y = DEFAULT_PAGE_MARGIN;
            float spaceBetweenVerticalLines = contentWidth / DEFAULT_VERTICAL_LINES_PER_SECTION;

            for (int i = 0; i < numOfSections; ++i) {
                // System.out.println("Section: " + i + " . starting at y = " + y);
                // for each section print "TAB"
                pgContent.beginText();
                pgContent.newLineAtOffset(DEFAULT_PAGE_MARGIN, contentHeight - y);
                pgContent.showText("T");
                pgContent.newLineAtOffset(0, -2 * DEFAULT_LINE_SPACE);
                pgContent.showText("A");
                pgContent.newLineAtOffset(0, -2 * DEFAULT_LINE_SPACE);
                pgContent.showText("B");
                pgContent.endText();

                // for each section, print 6 guitar strings
                float startY = y;
                for (int j = 0; j < DEFAULT_NUMBER_OF_LINES; ++j) {
                    pgContent.moveTo(startX, y);
                    pgContent.lineTo(endX, y);
                    pgContent.stroke();
                    y += DEFAULT_LINE_SPACE;
                }
                y += 2 * DEFAULT_LINE_SPACE;

                // for each section, print vertical lines
                float x = DEFAULT_PAGE_MARGIN;
                for (int k = 0; k <= DEFAULT_VERTICAL_LINES_PER_SECTION; ++k) {
                    // System.out.println("drawing " + k + " from " + x + ", " + startY + " to " + startY + (DEFAULT_NUMBER_OF_LINES - 1) * DEFAULT_LINE_SPACE);
                    pgContent.moveTo(x, startY);
                    pgContent.lineTo(x, startY + (DEFAULT_NUMBER_OF_LINES - 1) * DEFAULT_LINE_SPACE);
                    pgContent.stroke();
                    if (k == 0) { // add additional vertical line after TAB text
                        pgContent.moveTo(x + DEFAULT_FONT_SIZE, startY);
                        pgContent.lineTo(x + DEFAULT_FONT_SIZE, startY + (DEFAULT_NUMBER_OF_LINES - 1) * DEFAULT_LINE_SPACE);
                        pgContent.stroke();
                    }
                    x += spaceBetweenVerticalLines;
                }
                spaceBetweenVerticalLines = contentWidth / DEFAULT_VERTICAL_LINES_PER_SECTION;

                // System.out.println("Section: " + i + " completed.");
            }

            pgContent.close();
            pdfDoc.save(fileName);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally {
            if (pdfDoc != null) {
                try {
                    pdfDoc.close();
                }
                catch (IOException ioe2) {
                    ioe2.printStackTrace();
                }
            }
        }
    }
}
