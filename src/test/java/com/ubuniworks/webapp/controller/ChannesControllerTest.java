package com.ubuniworks.webapp.controller;

import org.appfuse.service.GenericManager;
import com.ubuniworks.model.Channes;

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

public class ChannesControllerTest extends BaseControllerTestCase {
    @Autowired
    private GenericManager channesManager;
    @Autowired
    private ChannesController controller;

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
        mockMvc.perform(get("/channess"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("channesList"))
            .andExpect(view().name("channess"));
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        channesManager.reindex();

        Map<String,Object> model = mockMvc.perform((get("/channess")).param("q", "*"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("channesList"))
            .andReturn()
            .getModelAndView()
            .getModel();

        List results = (List) model.get("channesList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}
