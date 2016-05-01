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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IdeabodyControllerTest extends BaseControllerTestCase {
    @Autowired
    private GenericManager ideabodyManager;
    @Autowired
    private IdeabodyController controller;

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
        mockMvc.perform(get("/ideabodies"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ideabodyList"))
                .andExpect(view().name("ideabodies"));
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        ideabodyManager.reindex();

        Map<String, Object> model = mockMvc.perform((get("/ideabodies")).param("q", "*"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ideabodyList"))
                .andReturn()
                .getModelAndView()
                .getModel();

        List results = (List) model.get("ideabodyList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}
