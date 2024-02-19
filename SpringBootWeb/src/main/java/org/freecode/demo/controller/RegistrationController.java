package org.freecode.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.freecode.demo.model.RegistrationFormObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
/**
 * @Controller annotation, the return values of the methods are defaulted to logic view names unless specified with @ResponseBody
 */
public class RegistrationController {

    private final static List<String> professionList = Arrays.asList("Student", "Unemployed", "Engineer", "Developer", "Investor");

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("registrationDetails", new RegistrationFormObject());
        model.addAttribute("professions", professionList);
        model.addAttribute("currentTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));

        return "registrationForm";
    }

    @PostMapping("/register")
    public String register(@Valid  @ModelAttribute("registrationDetails") RegistrationFormObject formObj, // th:object=${registrationDetails} in registrationForm.html
                           BindingResult bindingResult,
                           Model model) {
        model.addAttribute("registrationDetails", formObj);

        if (bindingResult.hasErrors()) {
            System.err.println("Errors found during the user registration!");
            model.addAttribute("professions", professionList);
            return "registrationForm";
        }

        return "registrationConfirmation";
    }
}
