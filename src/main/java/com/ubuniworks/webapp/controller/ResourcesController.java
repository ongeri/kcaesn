package com.ubuniworks.webapp.controller;

import org.appfuse.dao.SearchException;
import org.appfuse.service.GenericManager;
import com.ubuniworks.model.Resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/resourcess*")
public class ResourcesController {
    private GenericManager<Resources, Integer> resourcesManager;

    @Autowired
    public void setResourcesManager(@Qualifier("resourcesManager") GenericManager<Resources, Integer> resourcesManager) {
        this.resourcesManager = resourcesManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(resourcesManager.search(query, Resources.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(resourcesManager.getAll());
        }
        return model;
    }
}
