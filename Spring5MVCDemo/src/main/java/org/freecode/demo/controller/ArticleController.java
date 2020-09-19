package org.freecode.demo.controller;

import org.freecode.demo.model.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @RequestMapping("/create")
    /**
     * URL: http://<server>:<port>/<ContextPath>/article/create
     */
    public String createArticle(Model model) {
        model.addAttribute("article", new Article());
        return "article_form";
    }

    @RequestMapping("/publish")
    public String publishArticle(@ModelAttribute("article") Article article) {
        return "publish_confirmed";
    }
}
