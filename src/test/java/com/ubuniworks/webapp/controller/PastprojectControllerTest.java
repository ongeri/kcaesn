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

public class PastprojectControllerTest extends BaseControllerTestCase {
    @Autowired
    private GenericManager pastprojectManager;
    @Autowired
    private PastprojectController controller;

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
        mockMvc.perform(get("/pastprojects"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("pastprojectList"))
            .andExpect(view().name("pastprojects"));
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        pastprojectManager.reindex();

        Map<String,Object> model = mockMvc.perform((get("/pastprojects")).param("q", "*"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("pastprojectList"))
            .andReturn()
            .getModelAndView()
            .getModel();

        List results = (List) model.get("pastprojectList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}
