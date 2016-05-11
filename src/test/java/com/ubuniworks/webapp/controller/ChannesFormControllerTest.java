package com.ubuniworks.webapp.controller;

import com.ubuniworks.webapp.controller.BaseControllerTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
public class ChannesFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private ChannesFormController controller;
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
        mockMvc.perform(get("/channesform")
            .param("idchannes", "-1"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("channes"));
    }

    @Test
    public void testSave() throws Exception {
        HttpSession session = mockMvc.perform(post("/channesform")
            .param("description", "DgLpXxYsSdGjOhQiZkEnUiAuXyTbVpQvAaFrViTrJyIwApYuLvWnAkOwIbCkTvXlBbZrWdFuSwAmHyNsMoTtUwLrTiOzQxQvLaDsHvItIfThFiRsFdBaDbEfMjObZzRvKpPkTuByPkGpGaTxQeBwGhYhSlGtYcSbRrMzLqLuPhBmMeGoPyJcGpHlBoOoEjMtBrHlRpDjRcIfDmTrZiMxKwFwXrNySlTlDbSmQlQwZyThUlJeGgPtHlDuNiCnBeH")
            .param("name", "MrLkTjIlSmKuKnNoZdYpDsMnCqIsEvObXrLkNbHaTdQgBwWtXpSlAjBqFeXpOgCwUaDfJmKzRfFyWaAsZbCsZcEgDkZeZfYyJpRxLvYzWyZrRpCwWxXjEcFeSdQnHlXhWyEpNqBhEpGoPiAwXdRuYwNmLgYgAyEnKaRbSdHzHlFjKcCoTlQdJsJiApDwDrSuCoRrGqYfBhNkAlYgAiMwLeAvRvQtPlOgMzRsQkUwKcLmTwMuOsRoVgDiMyXcJhN")
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
        HttpSession session = mockMvc.perform((post("/channesform"))
            .param("delete", "").param("idchannes", "-2"))
            .andExpect(status().is3xxRedirection())
            .andReturn().getRequest().getSession();

        assertNotNull(session.getAttribute("successMessages"));
    }
}
