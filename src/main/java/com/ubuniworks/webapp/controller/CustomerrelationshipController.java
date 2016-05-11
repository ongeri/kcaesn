package com.ubuniworks.webapp.controller;

import org.appfuse.dao.SearchException;
import org.appfuse.service.GenericManager;
import com.ubuniworks.model.Customerrelationship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customerrelationships*")
public class CustomerrelationshipController {
    private GenericManager<Customerrelationship, Integer> customerrelationshipManager;

    @Autowired
    public void setCustomerrelationshipManager(@Qualifier("customerrelationshipManager") GenericManager<Customerrelationship, Integer> customerrelationshipManager) {
        this.customerrelationshipManager = customerrelationshipManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(customerrelationshipManager.search(query, Customerrelationship.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(customerrelationshipManager.getAll());
        }
        return model;
    }
}
