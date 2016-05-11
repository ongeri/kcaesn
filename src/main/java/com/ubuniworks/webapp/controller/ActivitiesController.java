package com.ubuniworks.webapp.controller;

import com.ubuniworks.dao.SearchException;
import com.ubuniworks.service.GenericManager;
import com.ubuniworks.model.Activities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/activitiess*")
public class ActivitiesController {
    private GenericManager<Activities, Integer> activitiesManager;

    @Autowired
    public void setActivitiesManager(@Qualifier("activitiesManager") GenericManager<Activities, Integer> activitiesManager) {
        this.activitiesManager = activitiesManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(activitiesManager.search(query, Activities.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(activitiesManager.getAll());
        }
        return model;
    }
}
