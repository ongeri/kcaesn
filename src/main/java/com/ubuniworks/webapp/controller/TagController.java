package com.ubuniworks.webapp.controller;

import org.appfuse.dao.SearchException;
import org.appfuse.service.GenericManager;
import com.ubuniworks.model.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tags*")
public class TagController {
    private GenericManager<Tag, Integer> tagManager;

    @Autowired
    public void setTagManager(@Qualifier("tagManager") GenericManager<Tag, Integer> tagManager) {
        this.tagManager = tagManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(tagManager.search(query, Tag.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(tagManager.getAll());
        }
        return model;
    }
}
