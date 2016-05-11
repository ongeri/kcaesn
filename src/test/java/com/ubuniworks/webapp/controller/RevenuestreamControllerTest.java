package com.ubuniworks.webapp.controller;

import org.appfuse.service.GenericManager;
import com.ubuniworks.model.Revenuestream;

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

public class RevenuestreamControllerTest extends BaseControllerTestCase {
    @Autowired
    private GenericManager revenuestreamManager;
    @Autowired
    private RevenuestreamController controller;

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
        mockMvc.perform(get("/revenuestreams"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("revenuestreamList"))
            .andExpect(view().name("revenuestreams"));
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        revenuestreamManager.reindex();

        Map<String,Object> model = mockMvc.perform((get("/revenuestreams")).param("q", "*"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("revenuestreamList"))
            .andReturn()
            .getModelAndView()
            .getModel();

        List results = (List) model.get("revenuestreamList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}
