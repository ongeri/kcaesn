package com.ubuniworks.webapp.controller;

import com.ubuniworks.dao.SearchException;
import com.ubuniworks.service.GenericManager;
import com.ubuniworks.model.Coststructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/coststructures*")
public class CoststructureController {
    private GenericManager<Coststructure, Integer> coststructureManager;

    @Autowired
    public void setCoststructureManager(@Qualifier("coststructureManager") GenericManager<Coststructure, Integer> coststructureManager) {
        this.coststructureManager = coststructureManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(coststructureManager.search(query, Coststructure.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(coststructureManager.getAll());
        }
        return model;
    }
}
