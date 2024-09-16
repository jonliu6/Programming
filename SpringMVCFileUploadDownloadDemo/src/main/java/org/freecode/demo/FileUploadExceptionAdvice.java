package org.freecode.demo;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class FileUploadExceptionAdvice {
     
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView handleMaxSizeException(
      MaxUploadSizeExceededException exc, 
      HttpServletRequest request,
      HttpServletResponse response) {
 
        ModelAndView modelAndView = new ModelAndView("uploadForm");
        modelAndView.getModel().put("uploadMessage", "File too large!");
        return modelAndView;
    }
}
