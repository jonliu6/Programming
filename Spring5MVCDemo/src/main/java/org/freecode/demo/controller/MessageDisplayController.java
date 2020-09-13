package org.freecode.demo.controller;

//import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
// import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * handle incoming HTTPServletRequest/Response
 * option 1: use Controller annotation
 * option 2: implements Controller class and override handleRequest()
 */
@Controller
public class MessageDisplayController {
//    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) {
//        return new ModelAndView("home"); // look for home.jsp in web application root folder (configured in viewResolver)
//    }

    @RequestMapping("/sayHello")
    /**
     * URL: http://<server>:<port>/<ContextPath>/sayHello
     */
    public ModelAndView sayHello(Model model) {
        model.addAttribute("message", "Hello, world!");
        // view name will be handled by InternalResourceViewResolver: prefix + <viewName> + suffix
        // e.g. http://<server>:<port>/<ContextPath>/message/home.jsp
        return new ModelAndView("home");
    }

    @RequestMapping("/sayHi")
    /**
     * URL: http://<server>:<port>/<ContextPath>/sayHi
     */
    public ModelAndView sayHi(Model model) {
        model.addAttribute("message", "Hi, there!");

        // view name will be handled by InternalResourceViewResolver: prefix + <viewName> + suffix
        // e.g. http://<server>:<port>/<ContextPath>/message/home.jsp
        return new ModelAndView("home");
    }
}
