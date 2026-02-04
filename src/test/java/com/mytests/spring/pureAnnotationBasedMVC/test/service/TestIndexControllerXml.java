package com.mytests.spring.pureAnnotationBasedMVC.test.service;

import com.mytests.spring.pureAnnotationBasedMVC.test.web.IndexController;
import jakarta.servlet.ServletContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringJUnitWebConfig(locations = "classpath:test-web-config.xml", resourcePath = "src/test/resources/static")
class TestIndexControllerXml {
    @Value("${prop1}") // not found
    String attrValue;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    protected MockServletContext mockServletContext;


    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testWac() {
        ServletContext servletContext = wac.getServletContext();
        Assertions.assertNotNull(servletContext);
        Assertions.assertInstanceOf(MockServletContext.class, servletContext);
        Assertions.assertNotNull(wac.getBean(IndexController.class));
    }

    @Test
    public void testModelAttrs() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/"));
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(view().name("index"));
        resultActions.andExpect(model().attribute("title",attrValue));
    }
}
