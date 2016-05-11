package com.ubuniworks.webapp.controller;

import org.appfuse.service.GenericManager;
import com.ubuniworks.model.Customerrelationship;

import com.ubuniworks.webapp.controller.BaseControllerTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CustomerrelationshipControllerTest extends BaseControllerTestCase {
    @Autowired
    private GenericManager customerrelationshipManager;
    @Autowired
    private CustomerrelationshipController controller;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();
    }

    @Test
    public void testHandleRequest() throws Exception {
        mockMvc.perform(get("/customerrelationships"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("customerrelationshipList"))
            .andExpect(view().name("customerrelationships"));
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        customerrelationshipManager.reindex();

        Map<String,Object> model = mockMvc.perform((get("/customerrelationships")).param("q", "*"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("customerrelationshipList"))
            .andReturn()
            .getModelAndView()
            .getModel();

        List results = (List) model.get("customerrelationshipList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}
