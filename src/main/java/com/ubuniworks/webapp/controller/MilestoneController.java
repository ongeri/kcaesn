package com.ubuniworks.webapp.controller;

import com.ubuniworks.dao.SearchException;
import com.ubuniworks.model.Milestone;
import com.ubuniworks.service.GenericManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/milestones*")
public class MilestoneController {
    private GenericManager<Milestone, Integer> milestoneManager;

    @Autowired
    public void setMilestoneManager(@Qualifier("milestoneManager") GenericManager<Milestone, Integer> milestoneManager) {
        this.milestoneManager = milestoneManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(milestoneManager.search(query, Milestone.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(milestoneManager.getAll());
        }
        return model;
    }
}
