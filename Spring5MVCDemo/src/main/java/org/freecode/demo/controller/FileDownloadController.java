package org.freecode.demo.controller;

import org.apache.commons.io.FilenameUtils;
import org.freecode.demo.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
public class FileDownloadController {
    @Autowired
    private Environment env;

    private Stack<String> folderHierachy = new Stack<>(); // storing the folder navigation information

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

    @RequestMapping(value = "/browse", method = RequestMethod.GET)
    /**
     * URL: http://<server>:<port>/<ContextPath>/browse
     */
    public String browseFolder(HttpServletRequest req, Model model) {
        model.addAttribute("fileList", getFileNames());
        model.addAttribute("currentFolder", getCurrentFolderName());
        return "file_browser";
    }

    private boolean isRootFolder() {
        return folderHierachy == null || folderHierachy.isEmpty();
    }

    private String getCurrentFolderName() {
        String rootFolderName = env.getProperty("JBOSS_HOME") + File.separator + UPLOAD_DIRECTORY;
        StringBuilder currentFolderNames = new StringBuilder(rootFolderName);
        if (! isRootFolder()) {
            Object[] folderNames = folderHierachy.toArray();
            if (folderNames != null) {
                for (int i = 0, len = folderNames.length; i < len; ++i) {
                    currentFolderNames.append(File.separator);
                    currentFolderNames.append(folderNames[i]);
                }
            }
        }

        return currentFolderNames.toString();
    }

    /**
     * return a list of folder and file names under a given folder name
     * @return
     */
    private List<String> getFileNames() {
        Set<String> folderNames = new TreeSet<String>();
        Set<String> fileNames = new TreeSet<String>();
        List<String> allNames = new ArrayList<String>();

        try {
            DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(getCurrentFolderName()));
            for (Path path: stream) {
                if (Files.isDirectory(path)) {
                    folderNames.add("[" + path.getFileName() + "]");
                }
                else {
                    fileNames.add(path.getFileName().toString());
                }
            }
            allNames.addAll(folderNames);
            allNames.addAll(fileNames);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return allNames;
    }

    private void navigateToFolder(String folderName) {
        String newFolderName = null;
        if (folderName != null) {
            newFolderName = folderName.replaceAll("\\[|\\]", "");
            if (newFolderName.contains("..")) {
                if (! isRootFolder()) {
                    folderHierachy.pop();
                }
            }
            else {
                folderHierachy.push(newFolderName);
            }
        }

    }
}
