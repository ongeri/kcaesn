package com.ubuniworks.webapp.controller;

import org.appfuse.dao.SearchException;
import org.appfuse.service.GenericManager;
import com.ubuniworks.model.Revenuestream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/revenuestreams*")
public class RevenuestreamController {
    private GenericManager<Revenuestream, Integer> revenuestreamManager;

    @Autowired
    public void setRevenuestreamManager(@Qualifier("revenuestreamManager") GenericManager<Revenuestream, Integer> revenuestreamManager) {
        this.revenuestreamManager = revenuestreamManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(revenuestreamManager.search(query, Revenuestream.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(revenuestreamManager.getAll());
        }
        return model;
    }
}
