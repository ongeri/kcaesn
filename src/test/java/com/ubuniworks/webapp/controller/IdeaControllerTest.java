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

public class IdeaControllerTest extends BaseControllerTestCase {
    @Autowired
    private GenericManager ideaManager;
    @Autowired
    private IdeaController controller;

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
        mockMvc.perform(get("/ideas"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ideaList"))
                .andExpect(view().name("ideas"));
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        ideaManager.reindex();

        Map<String, Object> model = mockMvc.perform((get("/ideas")).param("q", "*"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ideaList"))
                .andReturn()
                .getModelAndView()
                .getModel();

        List results = (List) model.get("ideaList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}
