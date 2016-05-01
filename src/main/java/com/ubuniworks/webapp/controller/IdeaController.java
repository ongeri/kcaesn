package com.ubuniworks.webapp.controller;

import com.ubuniworks.model.Idea;
import org.appfuse.dao.SearchException;
import org.appfuse.service.GenericManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ideas*")
public class IdeaController {
    private GenericManager<Idea, Integer> ideaManager;

    @Autowired
    public void setIdeaManager(@Qualifier("ideaManager") GenericManager<Idea, Integer> ideaManager) {
        this.ideaManager = ideaManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
            throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(ideaManager.search(query, Idea.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(ideaManager.getAll());
        }
        return model;
    }
}
