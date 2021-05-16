package org.freecode.demo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * when using Ubuntu with OpenJDK, install JRE as well for missing libraries
 * e.g. sudo apt-get install -y openjdk-11-jre
 * to solve Can't load library: /usr/lib/jvm/java-11-openjdk-amd64/lib/libawt_xawt.so
 */
public class PDFCreatorDemo {
	private final static PDRectangle PAGE_SIZE = PDPage.PAGE_SIZE_LETTER;
	private final static PDFont TITLE_FONT = PDType1Font.TIMES_BOLD;
	private final static PDFont TEXT_FONT = PDType1Font.TIMES_ROMAN;
	// private final static float MAX_CHARACTER_WIDTH = 6.3f; // because the character widths are different (showed above), use 6.2f as average width per character.
	private final static float PAGE_MARGIN = 10.0f;
	private final static float LINE_MARGIN = 20.0f;
	private final static float FONT_SIZE = 11.0f;
	private final static float MAX_PAGE_WIDTH = PAGE_SIZE.getWidth() - 2 * PAGE_MARGIN;
	private final static float MAX_PAGE_HEIGHT = PAGE_SIZE.getHeight() - 2 * PAGE_MARGIN;
	
	private final static int START_ASCII_IDX = 32; // space
	private final static int END_ASCII_IDX = 126; // ~
	
	private PDDocument pdfDoc = null;
	private PDPage pdfPage = null;
	private PDPageContentStream pdfContent = null;
	private float currentX = PAGE_MARGIN; // starting X value
	private float currentY = MAX_PAGE_HEIGHT; // starting Y value
	
	public static void main(String[] args) {
		
		PDFCreatorDemo demo = new PDFCreatorDemo();
		demo.printOutCharacters();
		demo.generatePdf("pdfSample.pdf");

	}
	
	public void printOutCharacters() {
		float totalSize = 0.0f;
		float minSize = Float.MAX_VALUE;
		float maxSize = Float.MIN_VALUE;
		try {
			for (int start = START_ASCII_IDX, end = END_ASCII_IDX; start <= end; ++start) {
				char c = (char) start;
				float size = FONT_SIZE * TEXT_FONT.getStringWidth(String.valueOf(c)) / 1000;
				if (size < minSize) {
					minSize = size;
				}
				if (size > maxSize) {
					maxSize = size;
				}
				totalSize += size;
				System.out.println(start + " - " + c + "(" + size + "f)");
			}
			System.out.println("Average Character Width: " + totalSize / (END_ASCII_IDX - START_ASCII_IDX + 1));
			System.out.println("Minimum Character Width: " + minSize);
			System.out.println("Maximum Character Width: " + maxSize);
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}		
	}
	
	public void createText(PDPageContentStream contentStream, PDFont fnt, float fntSize, String text, float offsetX, float offsetY) throws IOException {
		contentStream.setFont(fnt, fntSize);
		contentStream.moveTextPositionByAmount(offsetX, offsetY);
		contentStream.drawString(text);
	}
	
	public void createText(PDFont font, String text) throws IOException {
		if (currentY > 0) { // not need to create a new page
			if (MAX_PAGE_HEIGHT == currentY) { // first line of the page, use default values, equals to PAGE_MARGIN, MAX_PAGE_HEIGHT
				pdfContent.moveTextPositionByAmount(currentX, currentY);
			}
			else {
			    pdfContent.moveTextPositionByAmount(0, -1 * LINE_MARGIN);
			}
		}
		else { // create a new page
			pdfPage = new PDPage(PAGE_SIZE);
			pdfDoc.addPage(pdfPage);
			if (pdfContent != null) {
				pdfContent.endText();
				pdfContent.close();
			}
			pdfContent = new PDPageContentStream(pdfDoc, pdfPage);
			pdfContent.beginText();
			
			currentX = PAGE_MARGIN;
			currentY = MAX_PAGE_HEIGHT;
			pdfContent.moveTextPositionByAmount(currentX, currentY);
		}
		pdfContent.setFont(font, FONT_SIZE);
		pdfContent.drawString(text);
		currentY -= LINE_MARGIN;
	}
	
	/**
	 * solution of handling long text from https://stackoverflow.com/questions/19635275/how-to-generate-multiple-lines-in-pdf-using-apache-pdfbox
	 * it wraps the text by white spaces, end-of-line character and maximum characters per line.
	 * @param font
	 * @param text
	 * @throws IOException
	 */
	public void createMultiLineText(PDFont font, String originalText) throws IOException {
		List<String> lines = new ArrayList<String>();
		if (originalText != null) {
			originalText = originalText.replaceAll("(\r\n|\n\r|\r|\n)", "\n");
		}
        String[] lineArray = originalText.split("\n"); // Unix uses only 0x0A - "\n", while Windows uses 0x0D 0x0A - "\r\n"
		for (int i = 0, len = lineArray.length; i < len; ++i) {
			String text = lineArray[i];
			if (text != null) {
				
				// int maxCharactersPerLine =  (int) (MAX_PAGE_WIDTH / MAX_CHARACTER_WIDTH); 
				float size = FONT_SIZE * font.getStringWidth(text) / 1000;
				
				if (size > MAX_PAGE_WIDTH) {
					int lastSpace = -1;
			        while (text.length() > 0)
			        {
			        	int spaceIndex = text.indexOf(' ', lastSpace + 1);
			        	if (spaceIndex < 0) {
			        		spaceIndex = text.length();
			        	}
			        	String subString = text.substring(0, spaceIndex);
			        	size = FONT_SIZE * font.getStringWidth(subString) / 1000;
			        	if (size > MAX_PAGE_WIDTH)
			            {
			        		if (lastSpace < 0) {
			        			// find the index to the closest place to MAX_PAGE_WIDTH in case of a long text without any white spaces
			        			StringBuilder sbuf = new StringBuilder();
			        			for (int j = 0, tmpLen = subString.length(); j < tmpLen; ++j) {
			        				sbuf.append(subString.charAt(j));
			        				if (FONT_SIZE * font.getStringWidth(sbuf.toString()) / 1000 > MAX_PAGE_WIDTH) {
			        					lastSpace = j;
			        					break;
			        				}
			        			}
			        			// lastSpace = spaceIndex;
			        		}
//			        		if (lastSpace > maxCharactersPerLine) { // not allow the end of the substring greater than maximum characters per line.
//			        			lastSpace = maxCharactersPerLine;
//			        		}
			        		subString = text.substring(0, lastSpace);
			        		lines.add(subString);
			        		text = text.substring(lastSpace).trim();
			        		lastSpace = -1;
			            }
			        	else if (spaceIndex == text.length()) {
			        		lines.add(text);
			        		text = "";
			        	}
			        	else {
			        		lastSpace = spaceIndex;
			        	}
			        }
			        
				}
				else {
					lines.add(text);
				}				
			}			
		}
		
		for (String aLine: lines) {
			if (aLine != null && aLine.length() > 0) {
				createText(font, aLine);
			}
		}
//		int charWidth1 = (int) (FONT_SIZE * font.getStringWidth("1") / 1000);
//		int charWidth2 = (int) (FONT_SIZE * font.getStringWidth("9") / 1000);
//		int charWidth3 = (int) (FONT_SIZE * font.getStringWidth("Z") / 1000);
//		int charWidth4 = (int) (FONT_SIZE * font.getStringWidth("a") / 1000);
//		int charWidth5 = (int) (FONT_SIZE * font.getStringWidth(" ") / 1000);
//		int charWidth6 = (int) (FONT_SIZE * font.getStringWidth("-") / 1000);
//		int charWidth7 = (int) (FONT_SIZE * font.getStringWidth("_") / 1000);
//		System.out.println("1: " + charWidth1 + "\n9: " + charWidth2 + "\nZ: " + charWidth3 + "\na: " + charWidth4 + "\n[Space]: " + charWidth5 + "\n-: " + charWidth6 + "\n_: " + charWidth7);
		
	}
	
	public void generatePdf(String fileName) {
		try {
			pdfDoc = new PDDocument();
			pdfPage = new PDPage(PAGE_SIZE);
			pdfDoc.addPage(pdfPage);
			pdfContent = new PDPageContentStream(pdfDoc, pdfPage);
			pdfContent.beginText();
			
			createText(TITLE_FONT, "ODCR #00001 Summary");
			createText(TITLE_FONT, "ODCR Created by at When:");
			createText(TEXT_FONT, "John Smith at 2018-11-21 17:31:55");
			createText(TITLE_FONT, "Region:");
			createText(TEXT_FONT, "Lower Mainland South");
			createText(TITLE_FONT, "Circuits:");
			createText(TEXT_FONT, "2555 MUR, 1266 SPG");
			createText(TITLE_FONT, "Substations:");
			createText(TEXT_FONT, "MRT");
			createText(TITLE_FONT, "Operting Drawings:");
			createText(TEXT_FONT, "DOD - GTP 25F46");
			createText(TEXT_FONT, "UDD - BOK9E2");
			createText(TEXT_FONT, "OTHERS - None");
			createText(TITLE_FONT, "Notes:");
			createText(TEXT_FONT, "UDD A006A4 also needs to be changed.");
			createText(TITLE_FONT, "Contact Information - Verified by Field Personnel:");
			createText(TEXT_FONT, "Bob Dylen");
			createMultiLineText(TITLE_FONT, "Attachments:");
			createMultiLineText(TEXT_FONT, "Super long text: 1234567890\n1234567890\n1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890");
			createMultiLineText(TEXT_FONT, "Super long text without spaces: 1234567890\n1234567890\n123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
			createMultiLineText(TEXT_FONT, "Super long alphabetics: abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
			
			StringBuilder sbd = new StringBuilder();
			sbd.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ\n");
			sbd.append("abcdefghijklmnopqrstuvwxyz\r");
			sbd.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz\r\n");
			sbd.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz\n\r");
			sbd.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz\n");
			createMultiLineText(TEXT_FONT, sbd.toString());
			
			if (pdfContent != null) {
				pdfContent.endText();
				pdfContent.close();
			}			
			
			pdfDoc.save(fileName);
			pdfDoc.close();
		}
		catch (COSVisitorException cosve) {
			cosve.printStackTrace();			
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void generatePdfOld() {
		
		try {
			pdfDoc = new PDDocument();			
			float posX = PAGE_MARGIN;
			float posY = MAX_PAGE_HEIGHT;
			float offsetX = 0;
			float offsetY = -1 * LINE_MARGIN;
			
			pdfPage = new PDPage(PAGE_SIZE);
			pdfDoc.addPage(pdfPage);
			pdfContent = new PDPageContentStream(pdfDoc, pdfPage);
			
			pdfContent.beginText();
			
			pdfContent.setFont(TITLE_FONT, FONT_SIZE);
			pdfContent.moveTextPositionByAmount(posX, posY);
			pdfContent.drawString("ODCR #00001 Summary");
						
			if (posY - LINE_MARGIN < 0) {
				pdfContent.endText();
				pdfContent.close();
				pdfPage = new PDPage(PAGE_SIZE);
				pdfDoc.addPage(pdfPage);
				pdfContent = new PDPageContentStream(pdfDoc, pdfPage);
				posY = MAX_PAGE_HEIGHT;
				offsetX = posX;
				offsetY = posY;
				pdfContent.beginText();
			}
			else {
				posY -= LINE_MARGIN;
				offsetX = 0.0f;
				offsetY = -1 * LINE_MARGIN;
			}
			
			pdfContent.setFont(TITLE_FONT, FONT_SIZE);
			pdfContent.moveTextPositionByAmount(offsetX, offsetY);
			pdfContent.drawString("ODCR Created by at When:");
			
			if (posY - LINE_MARGIN < 0) {
				pdfContent.endText();
				pdfContent.close();
				pdfPage = new PDPage(PAGE_SIZE);
				pdfDoc.addPage(pdfPage);
				pdfContent = new PDPageContentStream(pdfDoc, pdfPage);
				posY = MAX_PAGE_HEIGHT;
				offsetX = posX;
				offsetY = posY;
				pdfContent.beginText();
			}
			else {
				posY -= LINE_MARGIN;
				offsetX = 0.0f;
				offsetY = -1 * LINE_MARGIN;
			}
			pdfContent.setFont(TEXT_FONT, FONT_SIZE);
			pdfContent.moveTextPositionByAmount(offsetX, offsetY);
			pdfContent.drawString("John Smith at 2018-11-21 15:36:55");
			
			if (posY - LINE_MARGIN < 0) {
				pdfContent.endText();
				pdfContent.close();
				pdfPage = new PDPage(PAGE_SIZE);
				pdfDoc.addPage(pdfPage);
				pdfContent = new PDPageContentStream(pdfDoc, pdfPage);
				posY = MAX_PAGE_HEIGHT;
				offsetX = posX;
				offsetY = posY;
				pdfContent.beginText();
			}
			else {
				posY -= LINE_MARGIN;
				offsetX = 0.0f;
				offsetY = -1 * LINE_MARGIN;
			}
			pdfContent.setFont(TITLE_FONT, FONT_SIZE);
			pdfContent.moveTextPositionByAmount(offsetX, offsetY);
			pdfContent.drawString("Region:");
			
			if (posY - LINE_MARGIN < 0) {
				pdfContent.endText();
				pdfContent.close();
				pdfPage = new PDPage(PAGE_SIZE);
				pdfDoc.addPage(pdfPage);
				pdfContent = new PDPageContentStream(pdfDoc, pdfPage);
				posY = MAX_PAGE_HEIGHT;
				offsetX = posX;
				offsetY = posY;
				pdfContent.beginText();
			}
			else {
				posY -= LINE_MARGIN;
				offsetX = 0.0f;
				offsetY = -1 * LINE_MARGIN;
			}
			pdfContent.setFont(TEXT_FONT, FONT_SIZE);
			pdfContent.moveTextPositionByAmount(offsetX, offsetY);
			pdfContent.drawString("Lower Mainland South");
			
			if (posY - LINE_MARGIN < 0) {
				pdfContent.endText();
				pdfContent.close();
				pdfPage = new PDPage(PAGE_SIZE);
				pdfDoc.addPage(pdfPage);
				pdfContent = new PDPageContentStream(pdfDoc, pdfPage);
				posY = MAX_PAGE_HEIGHT;
				offsetX = posX;
				offsetY = posY;
				pdfContent.beginText();
			}
			else {
				posY -= LINE_MARGIN;
				offsetX = 0.0f;
				offsetY = -1 * LINE_MARGIN;
			}
			pdfContent.setFont(TITLE_FONT, FONT_SIZE);
			pdfContent.moveTextPositionByAmount(offsetX, offsetY);
			pdfContent.drawString("Circuits:");
			
			if (posY - LINE_MARGIN < 0) {
				pdfContent.endText();
				pdfContent.close();
				pdfPage = new PDPage(PAGE_SIZE);
				pdfDoc.addPage(pdfPage);
				pdfContent = new PDPageContentStream(pdfDoc, pdfPage);
				posY = MAX_PAGE_HEIGHT;
				offsetX = posX;
				offsetY = posY;
				pdfContent.beginText();
			}
			else {
				posY -= LINE_MARGIN;
				offsetX = 0.0f;
				offsetY = -1 * LINE_MARGIN;
			}
			pdfContent.setFont(TEXT_FONT, FONT_SIZE);
			pdfContent.moveTextPositionByAmount(offsetX, offsetY);
			pdfContent.drawString("2555 MUR, 1266 SPG");
						
			if (posY - LINE_MARGIN < 0) {
				pdfContent.endText();
				pdfContent.close();
				pdfPage = new PDPage(PAGE_SIZE);
				pdfDoc.addPage(pdfPage);
				pdfContent = new PDPageContentStream(pdfDoc, pdfPage);
				posY = MAX_PAGE_HEIGHT;
				offsetX = posX;
				offsetY = posY;
				pdfContent.beginText();
			}
			else {
				posY -= LINE_MARGIN;
				offsetX = 0.0f;
				offsetY = -1 * LINE_MARGIN;
			}
			pdfContent.setFont(TITLE_FONT, FONT_SIZE);
			pdfContent.moveTextPositionByAmount(offsetX, offsetY);
			pdfContent.drawString("Substations:");
						
			if (posY - LINE_MARGIN < 0) {
				pdfContent.endText();
				pdfContent.close();
				pdfPage = new PDPage(PAGE_SIZE);
				pdfDoc.addPage(pdfPage);
				pdfContent = new PDPageContentStream(pdfDoc, pdfPage);
				posY = MAX_PAGE_HEIGHT;
				offsetX = posX;
				offsetY = posY;
				pdfContent.beginText();
			}
			else {
				posY -= LINE_MARGIN;
				offsetX = 0.0f;
				offsetY = -1 * LINE_MARGIN;
			}
			pdfContent.setFont(TEXT_FONT, FONT_SIZE);
			pdfContent.moveTextPositionByAmount(offsetX, offsetY);
			pdfContent.drawString("MRT");
			
			if (posY - LINE_MARGIN < 0) {
				pdfContent.endText();
				pdfContent.close();
				pdfPage = new PDPage(PAGE_SIZE);
				pdfDoc.addPage(pdfPage);
				pdfContent = new PDPageContentStream(pdfDoc, pdfPage);
				posY = MAX_PAGE_HEIGHT;
				offsetX = posX;
				offsetY = posY;
				pdfContent.beginText();
			}
			else {
				posY -= LINE_MARGIN;
				offsetX = 0.0f;
				offsetY = -1 * LINE_MARGIN;
			}
			pdfContent.setFont(TITLE_FONT, FONT_SIZE);
			pdfContent.moveTextPositionByAmount(offsetX, offsetY);
			pdfContent.drawString("Operting Drawings:");
			
			if (posY - LINE_MARGIN < 0) {
				pdfContent.endText();
				pdfContent.close();
				pdfPage = new PDPage(PAGE_SIZE);
				pdfDoc.addPage(pdfPage);
				pdfContent = new PDPageContentStream(pdfDoc, pdfPage);
				posY = MAX_PAGE_HEIGHT;
				offsetX = posX;
				offsetY = posY;
				pdfContent.beginText();
			}
			else {
				posY -= LINE_MARGIN;
				offsetX = 0.0f;
				offsetY = -1 * LINE_MARGIN;
			}
			pdfContent.setFont(TEXT_FONT, FONT_SIZE);
			pdfContent.moveTextPositionByAmount(offsetX, offsetY);
			pdfContent.drawString("DOD - GTP 25F46");
			
			if (posY - LINE_MARGIN < 0) {
				pdfContent.endText();
				pdfContent.close();
				pdfPage = new PDPage(PAGE_SIZE);
				pdfDoc.addPage(pdfPage);
				pdfContent = new PDPageContentStream(pdfDoc, pdfPage);
				posY = MAX_PAGE_HEIGHT;
				offsetX = posX;
				offsetY = posY;
				pdfContent.beginText();
			}
			else {
				posY -= LINE_MARGIN;
				offsetX = 0.0f;
				offsetY = -1 * LINE_MARGIN;
			}
			pdfContent.setFont(TEXT_FONT, FONT_SIZE);
			pdfContent.moveTextPositionByAmount(offsetX, offsetY);
			pdfContent.drawString("UDD - BOK9E2");
			
			if (posY - LINE_MARGIN < 0) {
				pdfContent.endText();
				pdfContent.close();
				pdfPage = new PDPage(PAGE_SIZE);
				pdfDoc.addPage(pdfPage);
				pdfContent = new PDPageContentStream(pdfDoc, pdfPage);
				posY = MAX_PAGE_HEIGHT;
				offsetX = posX;
				offsetY = posY;
				pdfContent.beginText();
			}
			else {
				posY -= LINE_MARGIN;
				offsetX = 0.0f;
				offsetY = -1 * LINE_MARGIN;
			}
			pdfContent.setFont(TEXT_FONT, FONT_SIZE);
			pdfContent.moveTextPositionByAmount(offsetX, offsetY);
			pdfContent.drawString("OTHERS - None");
						
			if (posY - LINE_MARGIN < 0) {
				pdfContent.endText();
				pdfContent.close();
				pdfPage = new PDPage(PAGE_SIZE);
				pdfDoc.addPage(pdfPage);
				pdfContent = new PDPageContentStream(pdfDoc, pdfPage);
				posY = MAX_PAGE_HEIGHT;
				offsetX = posX;
				offsetY = posY;
				pdfContent.beginText();
			}
			else {
				posY -= LINE_MARGIN;
				offsetX = 0.0f;
				offsetY = -1 * LINE_MARGIN;
			}
			pdfContent.setFont(TITLE_FONT, FONT_SIZE);
			pdfContent.moveTextPositionByAmount(offsetX, offsetY);
			pdfContent.drawString("Notes:");
						
			if (posY - LINE_MARGIN < 0) {
				pdfContent.endText();
				pdfContent.close();
				pdfPage = new PDPage(PAGE_SIZE);
				pdfDoc.addPage(pdfPage);
				pdfContent = new PDPageContentStream(pdfDoc, pdfPage);
				posY = MAX_PAGE_HEIGHT;
				offsetX = posX;
				offsetY = posY;
				pdfContent.beginText();
			}
			else {
				posY -= LINE_MARGIN;
				offsetX = 0.0f;
				offsetY = -1 * LINE_MARGIN;
			}
			pdfContent.setFont(TEXT_FONT, FONT_SIZE);
			pdfContent.moveTextPositionByAmount(offsetX, offsetY);
			pdfContent.drawString("UDD A006A4 also needs to be changed.");
						
			if (posY - LINE_MARGIN < 0) {
				pdfContent.endText();
				pdfContent.close();
				pdfPage = new PDPage(PAGE_SIZE);
				pdfDoc.addPage(pdfPage);
				pdfContent = new PDPageContentStream(pdfDoc, pdfPage);
				posY = MAX_PAGE_HEIGHT;
				offsetX = posX;
				offsetY = posY;
				pdfContent.beginText();
			}
			else {
				posY -= LINE_MARGIN;
				offsetX = 0.0f;
				offsetY = -1 * LINE_MARGIN;
			}
			pdfContent.setFont(TITLE_FONT, FONT_SIZE);
			pdfContent.moveTextPositionByAmount(offsetX, offsetY);
			pdfContent.drawString("Contact Information - Verified by Field Personnel:");
						
			if (posY - LINE_MARGIN < 0) {
				pdfContent.endText();
				pdfContent.close();
				pdfPage = new PDPage(PAGE_SIZE);
				pdfDoc.addPage(pdfPage);
				pdfContent = new PDPageContentStream(pdfDoc, pdfPage);
				posY = MAX_PAGE_HEIGHT;
				offsetX = posX;
				offsetY = posY;
				pdfContent.beginText();
			}
			else {
				posY -= LINE_MARGIN;
				offsetX = 0.0f;
				offsetY = -1 * LINE_MARGIN;
			}
			pdfContent.setFont(TEXT_FONT, FONT_SIZE);
			pdfContent.moveTextPositionByAmount(offsetX, offsetY);
			pdfContent.drawString("Bob Robson");
			
			if (posY - LINE_MARGIN < 0) {
				pdfContent.endText();
				pdfContent.close();
				pdfPage = new PDPage(PAGE_SIZE);
				pdfDoc.addPage(pdfPage);
				pdfContent = new PDPageContentStream(pdfDoc, pdfPage);
				posY = MAX_PAGE_HEIGHT;
				offsetX = posX;
				offsetY = posY;
				pdfContent.beginText();
			}
			else {
				posY -= LINE_MARGIN;
				offsetX = 0.0f;
				offsetY = -1 * LINE_MARGIN;
			}
			pdfContent.setFont(TITLE_FONT, FONT_SIZE);
			pdfContent.moveTextPositionByAmount(offsetX, offsetY);
			pdfContent.drawString("Other File Attachment:");
			
			if (posY - LINE_MARGIN < 0) {
				pdfContent.endText();
				pdfContent.close();
				pdfPage = new PDPage(PAGE_SIZE);
				pdfDoc.addPage(pdfPage);
				pdfContent = new PDPageContentStream(pdfDoc, pdfPage);
				posY = MAX_PAGE_HEIGHT;
				offsetX = posX;
				offsetY = posY;
				pdfContent.beginText();
			}
			else {
				posY -= LINE_MARGIN;
				offsetX = 0.0f;
				offsetY = -1 * LINE_MARGIN;
			}
			pdfContent.setFont(TEXT_FONT, FONT_SIZE);
			pdfContent.moveTextPositionByAmount(offsetX, offsetY);
			pdfContent.drawString("None");
			posY -= LINE_MARGIN;
			
			pdfContent.endText();
			
			pdfContent.close();			
			pdfDoc.save("c:\\temp\\pdfSample.pdf");
			pdfDoc.close();
		}
		catch (COSVisitorException cosve) {
			cosve.printStackTrace();			
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void generatePdfOlder() {
		PDDocument pd = null;
		PDPage pg = null;
		PDPageContentStream pc = null;
		
		float startX = 10.0f;
		float startY = 780.0f;
		PDFont titleFnt = PDType1Font.TIMES_BOLD;
		PDFont textFnt = PDType1Font.TIMES_ROMAN;
		float fntSize = 11.0f;
		int lineHeight = 20;
		float currentY = 0;
		
		try {
			pd = new PDDocument();
			pg = new PDPage(PDPage.PAGE_SIZE_LETTER);
			pd.addPage(pg);
			pc = new PDPageContentStream(pd,  pg);
			
			pc.beginText();

			createText(pc, titleFnt, fntSize, "ODCR Report", startX, startY);
			for (int i = 0, len = 30; i < len; i++) {
				
				if ((currentY + 5 * lineHeight) >= startY) {
					pc.endText();
					pc.close();
					pg = new PDPage(PDPage.PAGE_SIZE_LETTER);
					pd.addPage(pg);
					pc = new PDPageContentStream(pd,  pg);
					pc.beginText();
					createText(pc, titleFnt, fntSize, "ODCR Report " + i, startX, startY);
					currentY = 0;
				}
				createText(pc, titleFnt, fntSize, "ODCR# :" + (i + 1), 0, -1 * lineHeight);
				currentY += 1 * lineHeight;
				createText(pc, titleFnt, fntSize, "Created by and When:", 0, -1 * lineHeight);
				currentY += 1 * lineHeight;
				createText(pc, textFnt, fntSize, "John Smith at " + getCurrentTimeInString(), 0, -1 * lineHeight);
				currentY += 1 * lineHeight;
				createText(pc, titleFnt, fntSize, "Region:", 0, -1 * lineHeight);
				currentY += 1 * lineHeight;
				createText(pc, textFnt, fntSize, "Lower Mainland South", 0, -1 * lineHeight);
				currentY += 1 * lineHeight;
			}			

			pc.endText();
			
			pc.close();
			
			pd.save("c:\\temp\\Hello_world.pdf");
			pd.close();
		}
		catch (COSVisitorException cosve) {
			cosve.printStackTrace();			
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public String getCurrentTimeInString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
}
