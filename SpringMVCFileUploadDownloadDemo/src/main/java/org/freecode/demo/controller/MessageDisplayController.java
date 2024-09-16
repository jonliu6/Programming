package org.freecode.demo.controller;

//import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
// import org.springframework.web.servlet.mvc.Controller;

import java.sql.SQLException;

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

    @RequestMapping("/exception")
    /**
     * URL: http://<server>:<port>/<ContextPath>/exception?name=sql
     * Alternatively can use Paths as below
     * @RequestMapping("/exception/{name}")
     * public String testException(@PathVariable("name") String exName, Model model)
     * ... // URL: http://<server>:<port>/<ContextPath>/exception/sql
     */
    public String testException(@RequestParam("name") String exName, Model model) throws SQLException{
        if ("SQL".equalsIgnoreCase(exName)) {
            throw new SQLException("SQLException was caught!");
        }
        else if ("IllegalArgument".equalsIgnoreCase(exName)) {
            throw new IllegalArgumentException("IllegalArgumentException was caught!!");
        }
        else if ("Runtime".equalsIgnoreCase(exName)) {
            throw new RuntimeException("RuntimeException was caught!!!");
        }

        // unmapped exceptions, goes back to home.jsp
        return "home";
    }

    @ExceptionHandler({IllegalArgumentException.class, SQLException.class})
    public ModelAndView handleException(Exception ex) {
        ModelAndView mv = new ModelAndView("error"); // default to error.jsp when catching IllegalArgumentException or SQLException
        mv.addObject("exceptionMessage", ex.getMessage());

        return mv;
    }
}
