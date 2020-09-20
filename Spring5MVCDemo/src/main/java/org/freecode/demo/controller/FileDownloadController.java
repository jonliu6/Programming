package org.freecode.demo.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileDownloadController {
    @Autowired
    private Environment env;

    private static final String UPLOAD_DIRECTORY = "/standalone/uploadFolder"; // relative path to the deployment

    @RequestMapping("/download")
    public void downloadFile(HttpServletRequest req, HttpServletResponse resp, Model model) {
        String fileName = req.getParameter("fileName");
        String downloadPath = env.getProperty("JBOSS_HOME") + File.separator + UPLOAD_DIRECTORY;
        Path file = Paths.get(downloadPath, fileName);

        if (Files.exists(file)) {
            String fileExt = FilenameUtils.getExtension(fileName);

            if ("png".equalsIgnoreCase(fileExt.toLowerCase())) {
                resp.setContentType("image/png");
            } else if ("jpg".equalsIgnoreCase(fileExt.toLowerCase())) {
                resp.setContentType("image/jpeg");
            } else if ("pdf".equalsIgnoreCase(fileExt.toLowerCase())) {
                resp.setContentType("application/pdf");
            } else {
                resp.setContentType("application/octet-stream");
            }

            resp.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            try {
                Files.copy(file, resp.getOutputStream());
                resp.getOutputStream().flush();
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
