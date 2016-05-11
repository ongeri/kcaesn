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

public class TagControllerTest extends BaseControllerTestCase {
    @Autowired
    private GenericManager tagManager;
    @Autowired
    private TagController controller;

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
        mockMvc.perform(get("/tags"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("tagList"))
            .andExpect(view().name("tags"));
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        tagManager.reindex();

        Map<String,Object> model = mockMvc.perform((get("/tags")).param("q", "*"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("tagList"))
            .andReturn()
            .getModelAndView()
            .getModel();

        List results = (List) model.get("tagList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}
