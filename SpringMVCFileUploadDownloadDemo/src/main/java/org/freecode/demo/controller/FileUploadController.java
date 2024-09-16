package org.freecode.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.freecode.demo.model.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
/**
 * implements ServletContextAware to get ServletContext instead of using HttpSession
 */
public class FileUploadController implements ServletContextAware {
    @Autowired
    private Environment env; // get Environment information e.g. PATH, JAVA_HOME...
    private static final String UPLOAD_DIRECTORY = "/standalone/uploadFolder"; // relative path to the deployment

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext sc) {
        this.servletContext = sc;
    }

    @RequestMapping("/uploadForm")
    /**
     * page view/file_upload_form.jsp can upload single or multiple files
     * URL: http://<server>:<port>/<ContextPath>/uploadForm
     */
    public ModelAndView uploadForm() {
        return new ModelAndView("file_upload_form");
    }

    /**
     * save a multi-part file as the given file name in the specified upload path
     * @param fileName
     * @param file
     * @throws IOException
     * @throws FileNotFoundException
     */
    private void saveFile(String fileName, MultipartFile file) throws IOException, FileNotFoundException {
        String uploadPath = env.getProperty("JBOSS_HOME") + File.separator + UPLOAD_DIRECTORY;
        byte[] bytes = file.getBytes();

        BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream(
                        new File(uploadPath + File.separator + fileName)));
        stream.write(bytes);
        stream.flush();
        stream.close();
        // System.gc(); // cleanup temp file
    }

    @RequestMapping(value = "/uploadSingle", method = RequestMethod.POST)
    /**
     * URL: http://<server>:<port>/<ContextPath>/uploadSingle
     * @param fName
     * @param file
     * @return
     */
    public @ResponseBody String uploadSingleHandler(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
        	String fName = file.getOriginalFilename();
            String uploadPath = env.getProperty("JBOSS_HOME") + File.separator + UPLOAD_DIRECTORY;
            try {
                saveFile(fName, file);
            }
            catch (IOException ex) {
                return "IOException caught when uploading " + fName + "<br/>" + ex.getMessage();
            }

            return "File " + fName + " was uploaded successfully to " + uploadPath;
        }
        else {
            return "File is empty";
        }
    }

    @RequestMapping(value="/uploadMultiple", method = RequestMethod.POST)
    /**
     * URL: http://<server>:<port>/<ContextPath>/uploadMultiple
     */
    public @ResponseBody String uploadMultipleFiles(@RequestParam("fileName") String[] fileNames, @RequestParam("file") MultipartFile[] files) {
    	if (files.length != fileNames.length) {
            return "File upload information mismatch.";
        }
        StringBuilder msg = new StringBuilder("");

        for (int i = 0, len = files.length; i < len; ++i) {
            MultipartFile file = files[i];
            String fName = file.getOriginalFilename();

            try {
                saveFile(fName, file);
            }
            catch (Exception ex) {
                return "File " + fName + " failed to be uploaded.";
            }
            msg.append("File " + fName + " uploaded successfully." + "<br/>");
        }

        return msg.toString();
    }

    @RequestMapping(value="/uploadMultiSelection", method = RequestMethod.POST)
    /**
     * URL: http://<server>:<port>/<ContextPath>/uploadMultiSelection
     */
    public String uploadMultiSelectedFiles(HttpServletRequest req, @ModelAttribute Attachment attachments, Model model) {
        List<MultipartFile> files  = attachments.getFiles();
        List<String> names = new ArrayList<String>();
        if (files!= null && files.size() >0) {
            String uploadPath = env.getProperty("JBOSS_HOME") + File.separator + UPLOAD_DIRECTORY;
            for (MultipartFile file: files) {
                String name = file.getOriginalFilename();
                names.add(name);
                File f = new File(uploadPath + File.separator + name);
                try {
                    file.transferTo(f);
                }
                catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }

        model.addAttribute("attachments", attachments); // update model on the upload form and refresh to show
        return "file_upload_form";
    }
}
