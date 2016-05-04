package com.ubuniworks.webapp.controller;

import org.appfuse.dao.SearchException;
import org.appfuse.service.GenericManager;
import com.ubuniworks.model.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/comments*")
public class CommentController {
    private GenericManager<Comment, Integer> commentManager;

    @Autowired
    public void setCommentManager(@Qualifier("commentManager") GenericManager<Comment, Integer> commentManager) {
        this.commentManager = commentManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(commentManager.search(query, Comment.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(commentManager.getAll());
        }
        return model;
    }
}
