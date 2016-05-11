package com.ubuniworks.webapp.controller;

import org.appfuse.service.GenericManager;

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

public class ExperienceControllerTest extends BaseControllerTestCase {
    @Autowired
    private GenericManager experienceManager;
    @Autowired
    private ExperienceController controller;

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
        mockMvc.perform(get("/experiences"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("experienceList"))
            .andExpect(view().name("experiences"));
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        experienceManager.reindex();

        Map<String,Object> model = mockMvc.perform((get("/experiences")).param("q", "*"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("experienceList"))
            .andReturn()
            .getModelAndView()
            .getModel();

        List results = (List) model.get("experienceList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}
