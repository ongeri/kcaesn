package com.ubuniworks.webapp.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
public class IdeabodyFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private IdeabodyFormController controller;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();
    }

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        mockMvc.perform(get("/ideabodyform")
                .param("idideabody", "-1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ideabody"));
    }

    @Test
    public void testSave() throws Exception {
        HttpSession session = mockMvc.perform(post("/ideabodyform")
                .param("content", "BsLqHbKzQfUfZtDhPuGtAnEsLgJrBmLbVhElVdZfBwOpXcYcImYeOdYkOiHaWwXeXgArXfTdZcHeCaVuPdMwQpAsXmHuRzVyQgMtLuTkYaSmDpXxOkFcRaTjWuFoRfDsTqKpPvHcAtQpQkKqPrWlWcSrGwHdVzLkHrEjEcEnYjBlYpDnOcBeIcMuZpFrAgDbNyWzJgZmJfVjAvBrRsCnGrPwCyEuZpPhFeQkOuVwNkKdXyKtFsGlPeWvZcJeXzX")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(model().hasNoErrors())
                .andReturn()
                .getRequest()
                .getSession();

        assertNotNull(session.getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        HttpSession session = mockMvc.perform((post("/ideabodyform"))
                .param("delete", "").param("idideabody", "-2"))
                .andExpect(status().is3xxRedirection())
                .andReturn().getRequest().getSession();

        assertNotNull(session.getAttribute("successMessages"));
    }
}
