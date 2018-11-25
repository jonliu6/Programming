package org.freecode.demo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFCreatorDemo {
	private final static PDRectangle PAGE_SIZE = PDPage.PAGE_SIZE_LETTER;
	private final static PDFont TITLE_FONT = PDType1Font.TIMES_BOLD;
	private final static PDFont TEXT_FONT = PDType1Font.TIMES_ROMAN;
	private final static float PAGE_MARGIN = 10.0f;
	private final static float LINE_MARGIN = 20.0f;
	private final static float FONT_SIZE = 11.0f;
	private final static float MAX_PAGE_WIDTH = PAGE_SIZE.getWidth() - PAGE_MARGIN;
	private final static float MAX_PAGE_HEIGHT = PAGE_SIZE.getHeight() - PAGE_MARGIN;
	
	private PDDocument pdfDoc = null;
	private PDPage pdfPage = null;
	private PDPageContentStream pdfContent = null;
	private float currentX = PAGE_MARGIN; // starting X value
	private float currentY = MAX_PAGE_HEIGHT; // starting Y value
	
	public static void main(String[] args) {
		
		PDFCreatorDemo demo = new PDFCreatorDemo();
		demo.generatePdf("c:\\temp\\pdfSample.pdf");

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
			createText(TITLE_FONT, "Attachments:");
			createText(TEXT_FONT, "None");
						
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
