package com.ubuniworks.webapp.controller;

import com.ubuniworks.dao.SearchException;
import com.ubuniworks.service.GenericManager;
import com.ubuniworks.model.Channes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/channess*")
public class ChannesController {
    private GenericManager<Channes, Integer> channesManager;

    @Autowired
    public void setChannesManager(@Qualifier("channesManager") GenericManager<Channes, Integer> channesManager) {
        this.channesManager = channesManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(channesManager.search(query, Channes.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(channesManager.getAll());
        }
        return model;
    }
}
