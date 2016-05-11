package com.ubuniworks.webapp.controller;

import org.appfuse.dao.SearchException;
import org.appfuse.service.GenericManager;
import com.ubuniworks.model.Valueproposition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/valuepropositions*")
public class ValuepropositionController {
    private GenericManager<Valueproposition, Integer> valuepropositionManager;

    @Autowired
    public void setValuepropositionManager(@Qualifier("valuepropositionManager") GenericManager<Valueproposition, Integer> valuepropositionManager) {
        this.valuepropositionManager = valuepropositionManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(valuepropositionManager.search(query, Valueproposition.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(valuepropositionManager.getAll());
        }
        return model;
    }
}
