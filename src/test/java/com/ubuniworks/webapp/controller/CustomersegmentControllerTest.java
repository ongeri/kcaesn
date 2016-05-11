package com.ubuniworks.webapp.controller;

import org.appfuse.service.GenericManager;
import com.ubuniworks.model.Customersegment;

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

public class CustomersegmentControllerTest extends BaseControllerTestCase {
    @Autowired
    private GenericManager customersegmentManager;
    @Autowired
    private CustomersegmentController controller;

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
        mockMvc.perform(get("/customersegments"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("customersegmentList"))
            .andExpect(view().name("customersegments"));
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        customersegmentManager.reindex();

        Map<String,Object> model = mockMvc.perform((get("/customersegments")).param("q", "*"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("customersegmentList"))
            .andReturn()
            .getModelAndView()
            .getModel();

        List results = (List) model.get("customersegmentList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}
