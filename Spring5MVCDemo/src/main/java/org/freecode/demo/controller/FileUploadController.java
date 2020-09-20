package org.freecode.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

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

    @RequestMapping(value = "/uploadSingle", method = RequestMethod.GET)
    /**
     * URL: http://<server>:<port>/<ContextPath>/uploadSingle
     */
    public ModelAndView uploadSingleFile() {
        return new ModelAndView("single_upload_form"); // view/single_upload_form.jsp

    }

    @RequestMapping(value = "/uploadSingle", method = RequestMethod.POST)
    /**
     * URL: same as above, but the method is POST for submission
     * @param fName
     * @param file
     * @return
     */
    public @ResponseBody String uploadSingleHandler(@RequestParam("fileName") String fName, @RequestParam("file") MultipartFile file) {
        String uploadPath = env.getProperty("JBOSS_HOME") + File.separator + UPLOAD_DIRECTORY;
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(
                                new File(uploadPath + File.separator + fName)));
                stream.write(bytes);
                stream.flush();
                stream.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

            return "File " + fName + " was uploaded successfully to " + uploadPath;
        }
        else {
            return "File is empty";
        }
    }
}
