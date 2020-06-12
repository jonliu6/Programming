using System;
using System.Collections.Generic;
// using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using java.awt; // for Color
using java.io; // for File
using java.lang; // for Float
using org.apache.pdfbox.pdmodel;
using org.apache.pdfbox.pdmodel.font;
using org.apache.pdfbox.pdmodel.graphics.blend;
using org.apache.pdfbox.pdmodel.graphics.state;
using org.apache.pdfbox.util;

namespace PDFBoxWatermark
{
    class Program
    {
        static void Main(string[] args)
        {
            if (args == null || args.Length < 2)
            {
                System.Console.WriteLine("Usage: " + AppDomain.CurrentDomain.FriendlyName + " <original PDF filename> <watermark text> [new PDF filename]" + Environment.NewLine +
                    "  For example: " + AppDomain.CurrentDomain.FriendlyName + " myDoc.pdf \"This is a Draft\"");
            }
            else
            {
                string origName = args[0];
                string watermarkTxt = args[1];
                if (! System.IO.File.Exists(origName))
                {

                    System.Console.WriteLine("Error: cannot find the original PDF file(" + origName + "). Please correct the filename or the path and try again.");
                }
                else
                {
                    PDDocument origDoc = PDDocument.load(new java.io.File(origName)); // NOTE: PDDocument.load() only takes java.io.File, not System.IO.File from C#.Net
                    PDPageTree allPages = origDoc.getPages();
                    PDFont font = PDType1Font.HELVETICA_BOLD;
                    for (int i = 0, len = allPages.getCount(); i < len; ++i)
                    {
                        PDPage pg = (PDPage)allPages.get(i);
                        addWatermarkText(origDoc, pg, font, "This is a draft!!!");

                    }

                    origDoc.save("watermarked_" + origName);
                    origDoc.close();
                }
            }
        }

        /// <summary>
        /// The below method is an example from https://svn.apache.org/viewvc/pdfbox/trunk/examples/src/main/java/org/apache/pdfbox/examples/util/AddWatermarkText.java?revision=1873147&view=markup
        /// </summary>
        /// <param name="doc"></param>
        /// <param name="page"></param>
        /// <param name="font"></param>
        /// <param name="text"></param>
        static void addWatermarkText(PDDocument doc, PDPage page, PDFont font, string text)
        {
            using (PDPageContentStream cs
                         = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, true, true))
            {
                float fontHeight = 100; // arbitrary for short text
                float width = page.getMediaBox().getWidth();
                float height = page.getMediaBox().getHeight();
                float stringWidth = font.getStringWidth(text) / 1000 * fontHeight;
                float diagonalLength = (float)System.Math.Sqrt(width * width + height * height);
                float angle = (float)System.Math.Atan2(height, width);
                float x = (diagonalLength - stringWidth) / 2; // "horizontal" position in rotated world
                float y = -fontHeight / 4; // 4 is a trial-and-error thing, this lowers the text a bit
                cs.transform(Matrix.getRotateInstance(angle, 0, 0));
                cs.setFont(font, fontHeight);
                // cs.setRenderingMode(RenderingMode.STROKE) // for "hollow" effect

                PDExtendedGraphicsState gs = new PDExtendedGraphicsState();
                gs.setNonStrokingAlphaConstant(new Float(0.2f));
                gs.setStrokingAlphaConstant(new Float(0.2f));
                gs.setBlendMode(BlendMode.MULTIPLY);
                gs.setLineWidth(new Float(3f));
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
}
