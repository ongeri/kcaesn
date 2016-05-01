package com.ubuniworks.webapp.controller;

import com.ubuniworks.model.Ideabody;
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
@RequestMapping("/ideabodies*")
public class IdeabodyController {
    private GenericManager<Ideabody, Integer> ideabodyManager;

    @Autowired
    public void setIdeabodyManager(@Qualifier("ideabodyManager") GenericManager<Ideabody, Integer> ideabodyManager) {
        this.ideabodyManager = ideabodyManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
            throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(ideabodyManager.search(query, Ideabody.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(ideabodyManager.getAll());
        }
        return model;
    }
}
