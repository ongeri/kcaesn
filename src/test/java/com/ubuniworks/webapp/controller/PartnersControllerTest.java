package com.ubuniworks.webapp.controller;

import org.appfuse.service.GenericManager;
import com.ubuniworks.model.Partners;

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

public class PartnersControllerTest extends BaseControllerTestCase {
    @Autowired
    private GenericManager partnersManager;
    @Autowired
    private PartnersController controller;

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
        mockMvc.perform(get("/partnerss"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("partnersList"))
            .andExpect(view().name("partnerss"));
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        partnersManager.reindex();

        Map<String,Object> model = mockMvc.perform((get("/partnerss")).param("q", "*"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("partnersList"))
            .andReturn()
            .getModelAndView()
            .getModel();

        List results = (List) model.get("partnersList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}
