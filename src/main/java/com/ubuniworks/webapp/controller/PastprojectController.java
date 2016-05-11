package com.ubuniworks.webapp.controller;

import com.ubuniworks.dao.SearchException;
import com.ubuniworks.service.GenericManager;
import com.ubuniworks.model.Pastproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pastprojects*")
public class PastprojectController {
    private GenericManager<Pastproject, Integer> pastprojectManager;

    @Autowired
    public void setPastprojectManager(@Qualifier("pastprojectManager") GenericManager<Pastproject, Integer> pastprojectManager) {
        this.pastprojectManager = pastprojectManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(pastprojectManager.search(query, Pastproject.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(pastprojectManager.getAll());
        }
        return model;
    }
}
