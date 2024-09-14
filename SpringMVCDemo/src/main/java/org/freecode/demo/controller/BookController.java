package org.freecode.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.freecode.demo.model.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletOutputStream;

@Controller
@RequestMapping("/library")
@SessionAttributes("newBook")
public class BookController {
	
	@Value("${upload.folder}")
	private String rootFolderName;
	
	private final static int BUFFER_SIZE = 8192;
	
	@ModelAttribute
	/**
	 * add session attribute to model
	 * @param book
	 */
	public void addBookToModel(Model book) {
		if (!book.containsAttribute("newBook")) {
			Book bk = new Book();
			book.addAttribute("newBook", bk);
		}
	}

	/**
	 * URL: http://<server name>:<port#>/<Context Path>/controller/library/add
	 * @param aBook
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	/**
	 * handler of /add navigates to addBook.jsp
	 * @param aBook
	 * @return
	 */
	public String addBook(@ModelAttribute ("newBook") Book aBook) {
		// System.out.println(aBook);
		// in case of add a new book, clear the object first
		if (aBook != null) {
			aBook.setTitle(null);
			aBook.setIsbn(null);
			aBook.setPageCount(null);
			aBook.setPublishedOn(null);
			aBook.setAttachment(null);
		}
		
		return "addBook"; // navigate to addBook.jsp
	}
	
	/**
	 * use POST to hide the session attributes passed in
	 * @param aBook
	 * @param result, validation results
	 * @return
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	/**
	 * save the model, including file upload, and then redirect to the handler of /view
	 * @param aBook
	 * @param result
	 * @return
	 */
	public String saveBook(@Valid @ModelAttribute("newBook") Book aBook, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println(result.getErrorCount() + " error(s) when saveBook");
			return "addBook";
		}
		
		// NOT Implemented yet, save aBook to storage
				
		// upload file
		if (aBook != null) {
			MultipartFile file = aBook.getAttachment(); // get uploaded file
			try {
				if (!file.isEmpty()){
					byte[] bytes = file.getBytes();
					String fn = file.getOriginalFilename();
					FileOutputStream fos = new FileOutputStream(rootFolderName + File.separator + fn);
					fos.write(bytes);
					fos.flush();
					fos.close();
					System.out.println("Saved " + rootFolderName +  File.separator + fn);
				}
				else {
					System.out.println("Nothing to be saved.");
				}
			}
			catch (FileNotFoundException fne) {
				fne.printStackTrace();
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
			 
		}
		
		System.out.println("Saved " + aBook);
		return "redirect:view"; // redirect to view, which viewBook.jsp 
	}
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	/**
	 * set model attributes to viewBook.jsp and navigate to it
	 * URL: http://<server name>:<port #>/<Context path>/controller/library/view
	 * @param aBook
	 * @param bookModel
	 * @return
	 */
	public String viewBook(@ModelAttribute ("newBook") Book aBook, Model bookModel) {
		
		bookModel.addAttribute("bookTitle", aBook.getTitle());
		bookModel.addAttribute("bookIsbn", aBook.getIsbn());
		bookModel.addAttribute("bookPageCount", aBook.getPageCount());
		bookModel.addAttribute("bookPublishDate", aBook.getPublishedOn());
		bookModel.addAttribute("bookAttachment", aBook.getAttachment().getOriginalFilename());
		
		return "viewBook"; // navigate to viewBook.jsp
	}
	
	@RequestMapping(value="/download", method=RequestMethod.GET)
	/**
	 * similar concept to HTTP Servlet
	 * URL: http://<server name>:<port#>/<Context Path>/controller/library/download?filename=xxxx.xxx
	 * @param req
	 * @param resp
	 */
	public void downloadAttachment(HttpServletRequest req, HttpServletResponse resp) {
		String fn = req.getParameter("filename");
		String fullPath = rootFolderName + File.separator + fn;
		System.out.println("DOWNLOADING..." + fullPath);
		
		File downloadFile = new File(fullPath);
        FileInputStream inputStream = null;
        OutputStream out = null;
        try {
        	inputStream = new FileInputStream(downloadFile);
//        	String mimeType = req.getServletContext().getMimeType(fullPath); //option 1, requires defining mime types in deployment descriptors
        	Path path = Paths.get(fullPath);
        	String mimeType = Files.probeContentType(path);
        	String openFlag = "inline";
        	if (mimeType == null || mimeType.length() < 1) {
        		mimeType = "application/octet-stream";
        	}
        	if (mimeType.toLowerCase().startsWith("application/")) {
        		openFlag = "attachment";
        	}
        	resp.setContentType(mimeType);
        	//resp.setContentLengthLong(downloadFile.length());
        	resp.setHeader("Content-Disposition", openFlag + "; filename=" + fn); // can specify Inline or Attachment in header, so the browser will directly open it or prompt to open or save. Inline may trigger MS authentication when opening 
        	
        	out = resp.getOutputStream();
        	byte[] buffer = new byte[BUFFER_SIZE];
        	int bytesRead = -1;
        	while ((bytesRead = inputStream.read(buffer)) != -1) {
        		out.write(buffer, 0, bytesRead);
        	}
        }
        catch (FileNotFoundException fne) {
        	fne.printStackTrace();
        }
        catch (IOException ioe) {
        	ioe.printStackTrace();
        }
        finally {
        	try {
				if (inputStream != null) {
					inputStream.close();
				}
				if (out != null) {
					out.flush();
					out.close();
				}
        	}
        	catch (IOException ioe1) {
        		ioe1.printStackTrace();
        	}
		}
	}
}
