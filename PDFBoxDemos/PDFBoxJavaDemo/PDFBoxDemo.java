import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.blend.BlendMode;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.util.Matrix;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PDFBoxDemo {
    public static void main(String[] args) {
        float mrgn = 10;
        float ftSize= 12;
        if (args == null || args.length < 2) {
            System.out.println("Usage: PDFBoxDemo <source PDF filename> <watermark text>" );
        }
        else {
            String origName = args[0];
            String watermarkTxt = args[1];
            PDDocument doc = null;
            PDPage pg = null;
            PDPageContentStream ctstm = null;
            PDFont ft = null;

            try {
				
                if (!Files.exists(Paths.get(origName))) { // use Files.exists(Path.of(origName)) for JDK 8+
                    // create a sample PDF
                    doc = new PDDocument();
                    pg = new PDPage();
                    doc.addPage(pg);
                    ctstm = new PDPageContentStream(doc, pg);
                    ft = PDType1Font.HELVETICA_BOLD;
                    ctstm.setFont(ft, ftSize);
                    // write text
                    ctstm.beginText();
                    ctstm.newLineAtOffset(mrgn, pg.getMediaBox().getHeight() - mrgn);
                    ctstm.showText(watermarkTxt);

                    ctstm.endText();

                    // create and add image
                    BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
                    Graphics2D g = image.createGraphics();  // not sure on this line, but this seems more right
                    g.setColor(Color.blue);
                    g.fillRect(0, 0, 100, 100); // give the whole image a white background
                    File imgFile = new File(origName + ".png");
                    ImageIO.write(image, "png", imgFile);

                    PDImageXObject img = PDImageXObject.createFromFile(origName + ".png", doc);
                    ctstm.drawImage(img, mrgn, pg.getMediaBox().getHeight() - img.getHeight() - mrgn * 2, img.getWidth(), img.getHeight());

                    ctstm.close();
                    doc.save(origName);
                    doc.close();
                }
                else {

                }
                doc = PDDocument.load(new File(origName));
                PDPageTree allPgs = doc.getPages();
                for (int i = 0, len = allPgs.getCount(); i < len; ++i) {
                    pg = allPgs.get(i);
                    ft = PDType1Font.HELVETICA_BOLD;
                    addWatermarkText(doc, pg, ft, watermarkTxt);
                }
                doc.save("watermarked_" + origName);
                doc.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }



    }

    /**
     * From https://svn.apache.org/viewvc/pdfbox/trunk/examples/src/main/java/org/apache/pdfbox/examples/util/AddWatermarkText.java?revision=1873147&view=markup
     * @param doc
     * @param page
     * @param font
     * @param text
     * @throws IOException
     */
    private static void addWatermarkText(PDDocument doc, PDPage page, PDFont font, String text)
            throws IOException
    {
        try (PDPageContentStream cs
                     = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, true, true))
        {
            float fontHeight = 100; // arbitrary for short text
            float width = page.getMediaBox().getWidth();
            float height = page.getMediaBox().getHeight();
            float stringWidth = font.getStringWidth(text) / 1000 * fontHeight;
            float diagonalLength = (float) Math.sqrt(width * width + height * height);
            float angle = (float) Math.atan2(height, width);
            float x = (diagonalLength - stringWidth) / 2; // "horizontal" position in rotated world
            float y = -fontHeight / 4; // 4 is a trial-and-error thing, this lowers the text a bit
            cs.transform(Matrix.getRotateInstance(angle, 0, 0));
            cs.setFont(font, fontHeight);
            // cs.setRenderingMode(RenderingMode.STROKE) // for "hollow" effect

            PDExtendedGraphicsState gs = new PDExtendedGraphicsState();
            gs.setNonStrokingAlphaConstant(0.2f);
            gs.setStrokingAlphaConstant(0.2f);
            gs.setBlendMode(BlendMode.MULTIPLY);
            gs.setLineWidth(3f);
            cs.setGraphicsStateParameters(gs);

            cs.setNonStrokingColor(Color.red);
            cs.setStrokingColor(Color.red);

            cs.beginText();
            cs.newLineAtOffset(x, y);
            cs.showText(text);
            cs.endText();
        }
    }
}
