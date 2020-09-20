package org.freecode.demo.controller;

import org.freecode.demo.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private Environment env; // get Environment information e.g. PATH, JAVA_HOME...
    private static final String UPLOAD_DIRECTORY = "/standalone/uploadFolder"; // relative path to the deployment

    @RequestMapping(value="/create", method = RequestMethod.GET)
    /**
     * URL: http://<server>:<port>/<ContextPath>/article/create
     */
    public String createArticle(Model model) {
        model.addAttribute("article", new Article());
        return "article_form";
    }

    @RequestMapping(value="/publish", method = RequestMethod.POST)
    /**
     * BindResult contains validation errors
     * Model parameter is for passing the object model to web page
     * URL: http://<server>:<port>/<ContextPath>/article/publish
     */
    public String publishArticle(@Valid @ModelAttribute("article") Article article, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "article_form"; // if any validation errors, navigate back to the form page for corrections.
        }
        return "publish_confirmed";
    }

    @RequestMapping("/uploadForm")
    /**
     * URL: http://<server>:<port>/<ContextPath>/article/uploadForm
     */
    public ModelAndView uploadForm() {
        return new ModelAndView("file_upload_form");
    }

    @RequestMapping(value="/uploadCommon", method = RequestMethod.POST)
    /**
     * action = "uploadCommon" in <form:form></form:form>
     * URL: http://<server>:<port>/<ContextPath>/article/uploadCommon
     */
    public ModelAndView uploadAction(@RequestParam CommonsMultipartFile file, HttpSession session) throws IOException {
        ServletContext sc = session.getServletContext();
        //String uploadPath = sc.getRealPath(UPLOAD_DIRECTORY);
        String uploadPath = env.getProperty("JBOSS_HOME") + File.separator + UPLOAD_DIRECTORY;
        System.out.println("######File Upload Directory: " + uploadPath);
        byte[] bytes = file.getBytes();
        BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream(
                        new File(uploadPath + File.separator + file.getOriginalFilename())));
        stream.write(bytes);
        stream.flush();
        stream.close();

        return new ModelAndView("file_upload_form", "uploadMessage", "File successfully uploaded");
    }
}
