package com.ubuniworks.webapp.controller;

import com.ubuniworks.dao.SearchException;
import com.ubuniworks.service.GenericManager;
import com.ubuniworks.model.Experience;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/experiences*")
public class ExperienceController {
    private GenericManager<Experience, Integer> experienceManager;

    @Autowired
    public void setExperienceManager(@Qualifier("experienceManager") GenericManager<Experience, Integer> experienceManager) {
        this.experienceManager = experienceManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(experienceManager.search(query, Experience.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(experienceManager.getAll());
        }
        return model;
    }
}
